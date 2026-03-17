package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.SeatInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatInfo;
import cn.kmbeast.pojo.vo.SeatInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 座位持久化接口
 */
@Mapper
public interface SeatInfoMapper {

    void save(SeatInfo seatInfo);

    void update(SeatInfo seatInfo);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<SeatInfoVO> query(SeatInfoQueryDto seatInfoQueryDto);

    Integer queryCount(SeatInfoQueryDto seatInfoQueryDto);

}
