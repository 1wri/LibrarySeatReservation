package cn.kmbeast.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 未签到者VO出参类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatOrderNoSignVO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 未签到次数
     */
    private Integer countNumber;
}
