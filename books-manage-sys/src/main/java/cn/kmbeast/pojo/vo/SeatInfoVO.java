package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.SeatInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 座位的出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SeatInfoVO extends SeatInfo {
    /**
     * 自习室名称
     */
    private String classroomName;
    /**
     * 自习室封面
     */
    private String classroomCover;
}
