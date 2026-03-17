package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.SeatOrderInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 座位预约出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatOrderInfoVO extends SeatOrderInfo {
    // 用户的额外数据
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户账号
     */
    private String userAccount;
    // 座位的额外数据
    /**
     * 座位号
     */
    private Integer seatNumber;
    // 自习室额外数据
    /**
     * 自习室的ID
     */
    private String classroomId;
    /**
     * 自习室的封面
     */
    private String classroomCover;
    /**
     * 自习室名称
     */
    private String classroomName;
}
