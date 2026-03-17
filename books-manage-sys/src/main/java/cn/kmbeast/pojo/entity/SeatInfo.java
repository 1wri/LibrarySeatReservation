package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 座位实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatInfo {
    /**
     * 主键
     */
    private Integer id;
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
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
