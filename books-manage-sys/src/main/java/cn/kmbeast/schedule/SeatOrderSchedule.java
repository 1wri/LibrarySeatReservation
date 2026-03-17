package cn.kmbeast.schedule;

import cn.kmbeast.mapper.BlacklistMapper;
import cn.kmbeast.mapper.SeatOrderInfoMapper;
import cn.kmbeast.pojo.dto.query.extend.SeatOrderInfoQueryDto;
import cn.kmbeast.pojo.em.OrderStatusEnum;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.SeatOrderInfo;
import cn.kmbeast.pojo.vo.SeatOrderInfoVO;
import cn.kmbeast.pojo.vo.SeatOrderNoSignVO;
import cn.kmbeast.utils.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 座位预约状况扫描处理
 */
@Component
public class SeatOrderSchedule {

    @Resource
    private SeatOrderInfoMapper seatOrderInfoMapper;
    @Resource
    private BlacklistMapper blacklistMapper;

    /**
     * 一个小时扫描一次
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void scan() {
        SeatOrderInfoQueryDto seatOrderInfoQueryDto = new SeatOrderInfoQueryDto();
        seatOrderInfoQueryDto.setOrderStatus(OrderStatusEnum.HAVE_ORDER.getStatus());
        List<SeatOrderInfoVO> seatOrderInfoVOS = seatOrderInfoMapper.query(seatOrderInfoQueryDto);
        List<SeatOrderInfoVO> orderInfoVOS = noSignIn(seatOrderInfoVOS);
        if (orderInfoVOS.isEmpty()){
            return;
        }
        // 这里是处理的未签到预约记录
        for (SeatOrderInfoVO orderInfoVO : orderInfoVOS) {
            SeatOrderInfo seatOrderInfo = new SeatOrderInfo();
            seatOrderInfo.setId(orderInfoVO.getId());
            seatOrderInfo.setOrderStatus(OrderStatusEnum.NO_SIGN_IN.getStatus());
            seatOrderInfoMapper.update(seatOrderInfo);
        }
        // 加入黑名单的处理 --- 恶意不签到3次，加入黑名单
        List<Integer> userIds = orderInfoVOS.stream()
                .map(SeatOrderInfoVO::getUserId)
                .distinct()
                .collect(Collectors.toList());
        List<SeatOrderNoSignVO> seatOrderNoSignVOS
                = seatOrderInfoMapper.queryByUserIds(userIds, OrderStatusEnum.NO_SIGN_IN.getStatus());
        if (!seatOrderNoSignVOS.isEmpty()){
            // 过滤出恶意未签到次数超过3次的用户
            List<SeatOrderNoSignVO> userList = seatOrderNoSignVOS.stream()
                    .filter(seatOrderNoSignVO -> seatOrderNoSignVO.getCountNumber() >= 3)
                    .collect(Collectors.toList());
            for (SeatOrderNoSignVO seatOrderNoSignVO : userList) {
                Blacklist blacklist = new Blacklist();
                blacklist.setCreateTime(LocalDateTime.now());
                blacklist.setDetail("多次恶意未签到");
                blacklist.setUserId(seatOrderNoSignVO.getUserId());
                blacklistMapper.save(blacklist);
            }
        }
    }

    /**
     * 从座位预约记录里边，查询出未在指定时间里面签到的预约信息
     *
     * @param seatOrderInfoVOS 预约信息
     * @return 未在指定时间里面签到的预约信息
     */
    private List<SeatOrderInfoVO> noSignIn(List<SeatOrderInfoVO> seatOrderInfoVOS) {
        LocalDateTime nowTime = LocalDateTime.now();
        return seatOrderInfoVOS.stream()
                .filter(seatOrderInfoVO -> DateUtil.createLocalDateTime(seatOrderInfoVO.getDate(), seatOrderInfoVO.getTimeSplit(),seatOrderInfoVO.getCreateTime()).isBefore(nowTime))
                .collect(Collectors.toList());
    }

}
