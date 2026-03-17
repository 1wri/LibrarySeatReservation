package cn.kmbeast.pojo.dto.query.extend;

import cn.kmbeast.pojo.dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 座位预约查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatOrderInfoQueryDto extends QueryDto {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 座位ID
     */
    private Integer seatInfoId;
    /**
     * 预约日期
     */
    private String date;
    /**
     * 预约时间段
     */
    private String timeSplit;
    /**
     * 签到开始时间
     */
    private LocalDateTime signInStartTime;
    /**
     * 签到结束时间
     */
    private LocalDateTime signInEndTime;
    /**
     * 预约状态
     * 1:  已预约
     * 2： 已签到
     * 3： 未签到
     */
    private Integer orderStatus;
}
