package cn.kmbeast.controller;

import cn.kmbeast.aop.Pager;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.vo.BlacklistVO;
import cn.kmbeast.schedule.SeatOrderSchedule;
import cn.kmbeast.service.BlacklistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 黑名单的 Controller
 */
@RestController
@RequestMapping(value = "/blacklist")
public class BlacklistController {

    @Resource
    private BlacklistService blacklistService;
    @Resource
    private SeatOrderSchedule schedule;


    /**
     * 黑名单新增
     *
     * @param blacklist 新增数据
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/save")
    public Result<Void> save(@RequestBody Blacklist blacklist) {
        return blacklistService.save(blacklist);
    }

    /**
     * 黑名单删除
     *
     * @param ids 要删除的黑名单ID列表
     * @return Result<Void> 通用响应体
     */
    @PostMapping(value = "/batchDelete")
    public Result<Void> batchDelete(@RequestBody List<Integer> ids) {
        return blacklistService.batchDelete(ids);
    }

    /**
     * 黑名单修改
     *
     * @param blacklist 参数
     * @return Result<Void> 响应
     */
    @PutMapping(value = "/update")
    public Result<Void> update(@RequestBody Blacklist blacklist) {
        return blacklistService.update(blacklist);
    }

    /**
     * 黑名单查询
     *
     * @param blacklistQueryDto 查询参数
     * @return Result<List < BlacklistVO>> 通用响应
     */
    @Pager
    @PostMapping(value = "/query")
    public Result<List<BlacklistVO>> query(@RequestBody BlacklistQueryDto blacklistQueryDto) {
        return blacklistService.query(blacklistQueryDto);
    }

}
