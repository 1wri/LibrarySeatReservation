package cn.kmbeast.service;

import cn.kmbeast.pojo.api.Result;
import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.Notice;
import cn.kmbeast.pojo.vo.BlacklistVO;

import java.util.List;

/**
 * 黑名单业务逻辑接口
 */
public interface BlacklistService {

    Result<Void> save(Blacklist blacklist);

    Result<Void> batchDelete(List<Integer> ids);

    Result<Void> update(Blacklist blacklist);

    Result<List<BlacklistVO>> query(BlacklistQueryDto blacklistQueryDto);

}
