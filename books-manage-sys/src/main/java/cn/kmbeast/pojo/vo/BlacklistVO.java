package cn.kmbeast.pojo.vo;

import cn.kmbeast.pojo.entity.Blacklist;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 黑名单的出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlacklistVO extends Blacklist {
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
}
