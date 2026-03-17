package cn.kmbeast.service.impl;

import cn.kmbeast.mapper.BlacklistMapper;
import cn.kmbeast.pojo.api.ApiResult;
import cn.kmbeast.pojo.api.PageResult;
import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.vo.BlacklistVO;
import cn.kmbeast.service.BlacklistService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 黑名单业务逻辑实现
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Resource
    private BlacklistMapper blacklistMapper;

    /**
     * 黑名单新增
     *
     * @param blacklist 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> save(Blacklist blacklist) {
        if (blacklist.getUserId() == null) {
            return ApiResult.error("用户ID不能为空");
        }
        if (!StringUtils.hasText(blacklist.getDetail())) {
            return ApiResult.error("事由不能为空");
        }
        BlacklistQueryDto blacklistQueryDto = new BlacklistQueryDto();
        blacklistQueryDto.setUserId(blacklist.getUserId());
        List<BlacklistVO> blacklistVOS = blacklistMapper.query(blacklistQueryDto);
        if (!blacklistVOS.isEmpty()) {
            return ApiResult.error("名单已添加至黑名单");
        }
        blacklist.setCreateTime(LocalDateTime.now());
        blacklistMapper.save(blacklist);
        return ApiResult.success();
    }

    /**
     * 黑名单删除
     *
     * @param ids 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> batchDelete(List<Integer> ids) {
        blacklistMapper.batchDelete(ids);
        return ApiResult.success();
    }

    /**
     * 黑名单修改
     *
     * @param blacklist 参数
     * @return Result<Void>
     */
    @Override
    public Result<Void> update(Blacklist blacklist) {
        blacklistMapper.update(blacklist);
        return ApiResult.success();
    }

    /**
     * 黑名单查询
     *
     * @param blacklistQueryDto 查询参数
     * @return Result<List < BlacklistVO>>
     */
    @Override
    public Result<List<BlacklistVO>> query(BlacklistQueryDto blacklistQueryDto) {
        List<BlacklistVO> blacklistVOS = blacklistMapper.query(blacklistQueryDto);
        Integer totalCount = blacklistMapper.queryCount(blacklistQueryDto);
        return PageResult.success(blacklistVOS, totalCount);
    }


}
