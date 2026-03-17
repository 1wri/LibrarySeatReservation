package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自习室查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassroomQueryDto extends QueryDto {
    /**
     * 自习室名称
     */
    private String name;
    /**
     * 自习室状态 --- 可用或者不可用
     */
    private Boolean status;
}
