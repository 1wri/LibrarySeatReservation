package cn.kmbeast.service.impl;

import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.mapper.BlacklistMapper;
import cn.kmbeast.mapper.SeatOrderInfoMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.dto.query.extend.SeatOrderInfoQueryDto;
import cn.kmbeast.pojo.em.OrderStatusEnum;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.SeatOrderInfo;
import cn.kmbeast.pojo.vo.SeatOrderInfoVO;
import cn.kmbeast.pojo.vo.TimeSplitVO;
import cn.kmbeast.service.SeatOrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 座位预约业务逻辑实现
 */
@Service
public class SeatOrderInfoServiceImpl implements SeatOrderInfoService {

    @Resource
    private SeatOrderInfoMapper seatOrderInfoMapper;
    @Resource
    BlacklistMapper blacklistMapper;

    /**
     * 座位预约新增
     *
     * @param seatOrderInfos 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(List<SeatOrderInfo> seatOrderInfos) {
        if (seatOrderInfos.isEmpty()) {
            return ApiResult.error("请选择预约时间段哦!");
        }
        // 黑名单判断
        BlacklistQueryDto blacklistQueryDto = new BlacklistQueryDto();
        blacklistQueryDto.setUserId(LocalThreadHolder.getUserId());
        Integer queryCount = blacklistMapper.queryCount(blacklistQueryDto);
        if (queryCount != 0){
            return ApiResult.error("您因多次恶意未签到，已被加入黑名单！请联系管理员处理");
        }
        // 创建时间
        LocalDateTime nowDateTime = LocalDateTime.now();
        // 预约者用户ID
        Integer userId = LocalThreadHolder.getUserId();
        for (SeatOrderInfo seatOrderInfo : seatOrderInfos) {
            seatOrderInfo.setCreateTime(nowDateTime);
            seatOrderInfo.setUserId(userId);
            seatOrderInfo.setOrderStatus(OrderStatusEnum.HAVE_ORDER.getStatus());
            // 判断同一时间段，自己是不是已经预约过了。
            SeatOrderInfoQueryDto seatOrderInfoQueryDto = new SeatOrderInfoQueryDto();
            seatOrderInfoQueryDto.setUserId(LocalThreadHolder.getUserId());
            seatOrderInfoQueryDto.setDate(seatOrderInfo.getDate());
            seatOrderInfo.setTimeSplit(seatOrderInfo.getTimeSplit());
            Integer count = seatOrderInfoMapper.queryCount(seatOrderInfoQueryDto);
            if (count != 0){
                return ApiResult.error("同时间段："+seatOrderInfo.getTimeSplit()+ "不可用");
            }
        }
        seatOrderInfoMapper.save(seatOrderInfos);
        return ApiResult.success();
    }

    /**
     * 座位预约删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        seatOrderInfoMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 座位预约修改
     *
     * @param seatOrderInfo 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(SeatOrderInfo seatOrderInfo) {
        seatOrderInfoMapper.update(seatOrderInfo);
        return ApiResult.success();
    }

    /**
     * 座位预约查询
     *
     * @param seatOrderInfoQueryDto 查询参数
     * @return Result<List < SeatOrderInfoVO>>
     */
    @Override
    public Result<List<SeatOrderInfoVO>> query(SeatOrderInfoQueryDto seatOrderInfoQueryDto) {
        List<SeatOrderInfoVO> blacklistVOS = seatOrderInfoMapper.query(seatOrderInfoQueryDto);
        Integer totalCount = seatOrderInfoMapper.queryCount(seatOrderInfoQueryDto);
        return PageResult.success(blacklistVOS, totalCount);
    }

    /**
     * 座位预约签到
     *
     * @param id 座位预约记录ID
     * @return Result<Void> 响应
     */
    @Override
    public Result<Void> signIn(Integer id) {
        SeatOrderInfo seatOrderInfo = new SeatOrderInfo();
        seatOrderInfo.setId(id);
        seatOrderInfo.setSignInTime(LocalDateTime.now());
        seatOrderInfo.setOrderStatus(OrderStatusEnum.HAVE_SIGN_IN.getStatus());
        return update(seatOrderInfo);
    }

    /**
     * 生成预约时间段
     * 1. 生成早上九点到晚上九点的时间，一个小时为间隔
     * 2. 除了生成时间间隔之外，还需要查询时间段的可预约状态
     *
     * @param seatOrderInfoQueryDto 查询数据
     * @return Result<List < TimeSplitVO>> 通用响应体
     */
    @Override
    public Result<List<TimeSplitVO>> timeSplitList(SeatOrderInfoQueryDto seatOrderInfoQueryDto) {
        // 座位指定日期下的时间段预约情况
        List<SeatOrderInfoVO> seatOrderInfoVOS = seatOrderInfoMapper.query(seatOrderInfoQueryDto);
        // 生成早上九点到晚上九点的时间段
        List<TimeSplitVO> timeSplitVOS = generateTimeSplit(9, 21);
        // 预约时间段可用状态处理
        useStatusDeal(seatOrderInfoVOS, timeSplitVOS);
        return ApiResult.success(timeSplitVOS);
    }

    /**
     * 预约时间段可用状态处理
     *
     * @param seatOrderInfoVOS 座位指定日期下的时间段预约情况
     * @param timeSplitVOS     时间段
     */
    private void useStatusDeal(List<SeatOrderInfoVO> seatOrderInfoVOS,
                               List<TimeSplitVO> timeSplitVOS) {
        for (TimeSplitVO timeSplitVO : timeSplitVOS) {
            // 初始时可用
            timeSplitVO.setIsUse(true);
            for (SeatOrderInfoVO seatOrderInfoVO : seatOrderInfoVOS) {
                if (Objects.equals(timeSplitVO.getTime(), seatOrderInfoVO.getTimeSplit())) {
                    timeSplitVO.setIsUse(false);
                }
            }
        }
    }

    /**
     * 生成指定范围时间段，一个小时为基准
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return List<TimeSplitVO>
     */
    private List<TimeSplitVO> generateTimeSplit(Integer startTime, Integer endTime) {
        List<TimeSplitVO> timeSplitVOS = new ArrayList<>();
        for (int i = startTime; i < endTime; i++) {
            TimeSplitVO timeSplitVO = new TimeSplitVO();
            timeSplitVO.setTime(i + ":00" + "-" + (i + 1) + ":00");
            timeSplitVOS.add(timeSplitVO);
        }
        return timeSplitVOS;
    }
}
