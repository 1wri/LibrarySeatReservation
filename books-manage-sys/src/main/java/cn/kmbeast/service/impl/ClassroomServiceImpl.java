package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.ClassroomMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ClassroomQueryDto;
import cn.kmbeast.pojo.entity.Classroom;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.service.ClassroomService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 自习室业务逻辑实现
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Resource
    private ClassroomMapper classroomMapper;

    /**
     * 自习室新增
     *
     * @param classroom 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Classroom classroom) {
        if (!StringUtils.hasText(classroom.getName())) {
            return ApiResult.error("自习室名称不能为空");
        }
        if (!StringUtils.hasText(classroom.getCover())) {
            return ApiResult.error("封面不能为空");
        }
        if (!StringUtils.hasText(classroom.getDetail())) {
            return ApiResult.error("详情不能为空");
        }
        if (classroom.getStatus() == null) {
            return ApiResult.error("状态需要设置哦");
        }
        if (classroom.getDetail().length() > 255) {
            return ApiResult.error("详情不能多于255个字符");
        }
        classroom.setCreateTime(LocalDateTime.now());
        classroomMapper.save(classroom);
        return ApiResult.success();
    }

    /**
     * 自习室删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        classroomMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 自习室修改
     *
     * @param classroom 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Classroom classroom) {
        if (!StringUtils.hasText(classroom.getName())) {
            return ApiResult.error("自习室名称不能为空");
        }
        if (!StringUtils.hasText(classroom.getCover())) {
            return ApiResult.error("封面不能为空");
        }
        if (!StringUtils.hasText(classroom.getDetail())) {
            return ApiResult.error("详情不能为空");
        }
        if (classroom.getStatus() == null) {
            return ApiResult.error("状态需要设置哦");
        }
        if (classroom.getDetail().length() > 255) {
            return ApiResult.error("详情不能多于255个字符");
        }
        classroomMapper.update(classroom);
        return ApiResult.success();
    }

    /**
     * 自习室查询
     *
     * @param classroomQueryDto 查询参数
     * @return Result<List < Classroom>>
     */
    @Override
    public Result<List<Classroom>> query(ClassroomQueryDto classroomQueryDto) {
        List<Classroom> classroomList = classroomMapper.query(classroomQueryDto);
        Integer totalCount = classroomMapper.queryCount(classroomQueryDto);
        return PageResult.success(classroomList, totalCount);
    }


    @Override
    public Result<List<ChartVO>> seats() {
        return ApiResult.success(classroomMapper.seats());
    }
}
