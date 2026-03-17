package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.SeatInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatInfo;
import cn.kmbeast.pojo.vo.SeatInfoVO;

import java.util.List;

/**
 * 座位业务逻辑接口
 */
public interface SeatInfoService {

    Result<Void> save(SeatInfo seatInfo);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(SeatInfo seatInfo);

    Result<List<SeatInfoVO>> query(SeatInfoQueryDto seatInfoQueryDto);

}
