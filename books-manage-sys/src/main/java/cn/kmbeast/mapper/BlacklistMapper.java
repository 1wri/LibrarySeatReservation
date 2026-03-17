package cn.kmbeast.mapper;

import cn.kmbeast.pojo.dto.query.extend.BlacklistQueryDto;
import cn.kmbeast.pojo.dto.query.extend.NoticeQueryDto;
import cn.kmbeast.pojo.entity.Blacklist;
import cn.kmbeast.pojo.entity.Notice;
import cn.kmbeast.pojo.vo.BlacklistVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 黑名单持久化接口
 */
@Mapper
public interface BlacklistMapper {

    void save(Blacklist blacklist);

    void update(Blacklist blacklist);

    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<BlacklistVO> query(BlacklistQueryDto blacklistQueryDto);

    Integer queryCount(BlacklistQueryDto blacklistQueryDto);

}
