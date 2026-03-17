package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.SeatInfoMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.SeatInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatInfo;
import cn.kmbeast.pojo.vo.SeatInfoVO;
import cn.kmbeast.service.SeatInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 座位业务逻辑实现
 */
@Service
public class SeatInfoServiceImpl implements SeatInfoService {

    @Resource
    private SeatInfoMapper seatInfoMapper;

    /**
     * 座位新增
     *
     * @param seatInfo 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(SeatInfo seatInfo) {
        if (seatInfo.getClassroomId() == null) {
            return ApiResult.error("自习室ID不能为空");
        }
        if (seatInfo.getNumber() == null) {
            return ApiResult.error("座位号不能为空");
        }
        if (seatInfo.getStatus() == null) {
            return ApiResult.error("状态不能为空");
        }
        // 如果新增时发现自习室下面已经存在相同座位，不能再新增
        SeatInfoQueryDto seatInfoQueryDto = new SeatInfoQueryDto();
        seatInfoQueryDto.setNumber(seatInfo.getNumber());
        seatInfoQueryDto.setClassroomId(seatInfo.getClassroomId());
        Integer seatInfoCount = seatInfoMapper.queryCount(seatInfoQueryDto);
        if (seatInfoCount != 0) {
            return ApiResult.error("座位已存在");
        }
        seatInfo.setCreateTime(LocalDateTime.now());
        seatInfoMapper.save(seatInfo);
        return ApiResult.success();
    }

    /**
     * 座位删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        seatInfoMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 座位修改
     *
     * @param seatInfo 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(SeatInfo seatInfo) {
        if (seatInfo.getClassroomId() == null) {
            return ApiResult.error("自习室ID不能为空");
        }
        if (seatInfo.getStatus() == null) {
            return ApiResult.error("状态不能为空");
        }
        if (seatInfo.getNumber() == null) {
            return ApiResult.error("座位号不能为空");
        } else {
            // 如果新增时发现自习室下面已经存在相同座位，不能再新增
            SeatInfoQueryDto seatInfoQueryDto = new SeatInfoQueryDto();
            seatInfoQueryDto.setNumber(seatInfo.getNumber());
            seatInfoQueryDto.setClassroomId(seatInfo.getClassroomId());
            Integer seatInfoCount = seatInfoMapper.queryCount(seatInfoQueryDto);
            if (seatInfoCount != 0) {
                return ApiResult.error("座位已存在");
            }
        }
        seatInfoMapper.update(seatInfo);
        return ApiResult.success();
    }

    /**
     * 座位查询
     *
     * @param seatInfoQueryDto 查询参数
     * @return Result<List < SeatInfoVO>>
     */
    @Override
    public Result<List<SeatInfoVO>> query(SeatInfoQueryDto seatInfoQueryDto) {
        List<SeatInfoVO> seatInfoVOS = seatInfoMapper.query(seatInfoQueryDto);
        Integer totalCount = seatInfoMapper.queryCount(seatInfoQueryDto);
        return PageResult.success(seatInfoVOS, totalCount);
    }


}
