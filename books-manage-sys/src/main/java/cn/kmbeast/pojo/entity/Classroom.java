package cn.kmbeast.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 自习室实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 自习室名称
     */
    private String name;
    /**
     * 封面
     */
    private String cover;
    /**
     * 自习室详情
     */
    private String detail;
    /**
     * 自习室状态 --- 可用或者不可用
     */
    private Boolean status;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
