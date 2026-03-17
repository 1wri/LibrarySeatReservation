package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.SeatInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatInfo;
import cn.kmbeast.pojo.vo.SeatInfoVO;
import cn.kmbeast.service.SeatInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 座位的 Controller
 */
@RestController
@RequestMapping(value = "/seatInfo")
public class SeatInfoController {

    @Resource
    private SeatInfoService seatInfoService;

    /**
     * 座位新增
     *
     * @param seatInfo 新增数据
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody SeatInfo seatInfo) {
        return seatInfoService.save(seatInfo);
    }

    /**
     * 座位删除
     *
     * @param ids 要删除的座位ID列表
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return seatInfoService.batchDelete(ids);
    }

    /**
     * 座位修改
     *
     * @param seatInfo 参数
     * @return Result<Void> 响应
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody SeatInfo seatInfo) {
        return seatInfoService.update(seatInfo);
    }

    /**
     * 座位查询
     *
     * @param seatInfoQueryDto 查询参数
     * @return Result<List < SeatInfoVO>> 通用响应
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<SeatInfoVO>> query(@RequestBody SeatInfoQueryDto seatInfoQueryDto) {
        return seatInfoService.query(seatInfoQueryDto);
    }

}
