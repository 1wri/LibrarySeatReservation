package cn.kmbeast.pojo.vo;

import lombok.Data;

/**
 * 时间间隔VO类
 */
@Data
public class TimeSplitVO {
    /**
     * 时间间隔
     */
    private String time;
    /**
     * 时间段是否可用 true:可用 false：不可用
     */
    private Boolean isUse;
}
