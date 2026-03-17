package cn.kmbeast.pojo.em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 预约状态枚举
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    HAVE_ORDER(1, "已预约"),
    HAVE_SIGN_IN(2, "已签到"),
    NO_SIGN_IN(3, "未签到");

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 状态解释
     */
    private final String detail;

}
