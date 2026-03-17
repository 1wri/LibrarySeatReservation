package cn.kmbeast.controller;

import cn.kmbeast.aop.Log;
import cn.kmbeast.aop.Pager;
import cn.kmbeast.context.LocalThreadHolder;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.SeatOrderInfoQueryDto;
import cn.kmbeast.pojo.entity.SeatOrderInfo;
import cn.kmbeast.pojo.vo.SeatOrderInfoVO;
import cn.kmbeast.pojo.vo.TimeSplitVO;
import cn.kmbeast.service.SeatOrderInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 座位预约的 Controller
 */
@RestController
@RequestMapping(value = "/seatOrderInfo")
public class SeatOrderInfoController {

    @Resource
    private SeatOrderInfoService seatOrderInfoService;
    /**
     * 座位预约新增
     *
     * @param seatOrderInfos 新增数据
     * @return Result<Void> 通用响应体
     */
    @Log(content = "预约座位")
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody List<SeatOrderInfo> seatOrderInfos) {
        return seatOrderInfoService.save(seatOrderInfos);
    }

    /**
     * 生成预约时间段
     * 1. 生成早上九点到晚上九点的时间，一个小时为间隔
     * 2. 除了生成时间间隔之外，还需要查询时间段的可预约状态
     *
     * @param seatOrderInfoQueryDto 查询数据
     * @return Result<List<TimeSplitVO>> 通用响应体
     */
    @PostMapping(value = "/timeSplitList")
    public Result<List<TimeSplitVO>> timeSplitList(@RequestBody SeatOrderInfoQueryDto seatOrderInfoQueryDto) {
        return seatOrderInfoService.timeSplitList(seatOrderInfoQueryDto);
    }

    /**
     * 座位预约删除
     *
     * @param ids 要删除的座位预约ID列表
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return seatOrderInfoService.batchDelete(ids);
    }

    /**
     * 座位预约签到
     *
     * @param id 座位预约记录ID
     * @return Result<Void> 响应
     */
    @Log(content = "预约签到")
    @PutMapping(value = "/signIn/{id}")
    public Result<Void> signIn(@PathVariable Integer id) {
        return seatOrderInfoService.signIn(id);
    }

    /**
     * 查询用户自己的座位预约历史
     *
     * @param seatOrderInfoQueryDto 查询参数
     * @return Result<List < SeatOrderInfoVO>> 通用响应
     */
    @Pager
    @PostMapping(value = "/queryUser")
    public Result<List<SeatOrderInfoVO>> queryUser(@RequestBody SeatOrderInfoQueryDto seatOrderInfoQueryDto) {
        seatOrderInfoQueryDto.setUserId(LocalThreadHolder.getUserId());
        return seatOrderInfoService.query(seatOrderInfoQueryDto);
    }

    /**
     * 座位预约查询
     *
     * @param seatOrderInfoQueryDto 查询参数
     * @return Result<List < SeatOrderInfoVO>> 通用响应
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<SeatOrderInfoVO>> query(@RequestBody SeatOrderInfoQueryDto seatOrderInfoQueryDto) {
        return seatOrderInfoService.query(seatOrderInfoQueryDto);
    }

}
