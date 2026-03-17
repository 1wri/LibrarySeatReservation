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
public class SeatOrderInfo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 座位ID
     */
    private Integer seatInfoId;
    /**
     * 日期
     */
    private String date;
    /**
     * 预约时间间隔
     */
    private String timeSplit;
    /**
     * 签到时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signInTime;
    /**
     * 预约状态
     * 1:  已预约
     * 2： 已签到
     * 3： 未签到
     */
    private Integer orderStatus;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
