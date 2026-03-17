package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 黑名单查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlacklistQueryDto extends QueryDto {
    /**
     * 用户ID
     */
    private Integer userId;
}
