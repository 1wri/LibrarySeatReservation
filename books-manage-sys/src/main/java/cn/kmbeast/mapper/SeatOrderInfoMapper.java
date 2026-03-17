package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.SeatOrderInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatOrderInfo;
import cn.kmbeast.pojo.vo.SeatOrderInfoVO;
import cn.kmbeast.pojo.vo.SeatOrderNoSignVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 座位预约持久化接口
 */
@Mapper
public interface SeatOrderInfoMapper {

    void save(@Param(value = "seatOrderInfos") List<SeatOrderInfo> seatOrderInfos);

    void update(SeatOrderInfo seatOrderInfo);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<SeatOrderInfoVO> query(SeatOrderInfoQueryDto seatOrderInfoQueryDto);

    Integer queryCount(SeatOrderInfoQueryDto seatOrderInfoQueryDto);

    List<SeatOrderNoSignVO> queryByUserIds(@Param(value = "userIds") List<Integer> userIds,
                                           @Param("orderStatus") Integer orderStatus);

}
