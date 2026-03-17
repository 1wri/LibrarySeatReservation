package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.ClassroomQueryDto;
import cn.kmbeast.pojo.entity.Classroom;
import cn.kmbeast.pojo.vo.ChartVO;
import cn.kmbeast.service.ClassroomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自习室的 Controller
 */
@RestController
@RequestMapping(value = "/classroom")
public class ClassroomController {

    @Resource
    private ClassroomService classroomService;

    /**
     * 自习室新增
     *
     * @param classroom 新增数据
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Classroom classroom) {
        return classroomService.save(classroom);
    }

    /**
     * 自习室对应的座位数
     *
     * @return Result<Void> 通用响应体
     */
    @GetMapping(value = "seats")
    public Result<List<ChartVO>> seats() {
        return classroomService.seats();
    }


    /**
     * 自习室删除
     *
     * @param ids 要删除的自习室ID列表
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return classroomService.batchDelete(ids);
    }

    /**
     * 自习室修改
     *
     * @param classroom 参数
     * @return Result<Void> 响应
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Classroom classroom) {
        return classroomService.update(classroom);
    }

    /**
     * 自习室查询
     *
     * @param classroomQueryDto 查询参数
     * @return Result<List < Classroom>> 通用响应
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<Classroom>> query(@RequestBody ClassroomQueryDto classroomQueryDto) {
        return classroomService.query(classroomQueryDto);
    }

}
