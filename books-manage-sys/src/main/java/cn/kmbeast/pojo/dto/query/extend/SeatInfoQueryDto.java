package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 座位查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatInfoQueryDto extends QueryDto {
    /**
     * 自习室ID，外键
     */
    private Integer classroomId;
    /**
     * 座位号
     */
    private Integer number;
    /**
     * 座位状态 (可用、不可用 1,0)
     * 1：true；0：false
     */
    private Boolean status;
}
