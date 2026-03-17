package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.dto.query.extend.SeatOrderInfoQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.SeatOrderInfo;
import cn.kmbeast.pojo.vo.BlacklistVO;
import cn.kmbeast.pojo.vo.SeatInfoVO;
import cn.kmbeast.pojo.vo.SeatOrderInfoVO;
import cn.kmbeast.pojo.vo.TimeSplitVO;

import java.util.List;

/**
 * 座位预约业务逻辑接口
 */
public interface SeatOrderInfoService {

    Result<Void> save(List<SeatOrderInfo> seatOrderInfo);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(SeatOrderInfo seatOrderInfo);

    Result<List<SeatOrderInfoVO>> query(SeatOrderInfoQueryDto seatOrderInfoQueryDto);

    Result<List<TimeSplitVO>> timeSplitList(SeatOrderInfoQueryDto seatOrderInfoQueryDto);

    Result<Void> signIn(Integer id);

}
