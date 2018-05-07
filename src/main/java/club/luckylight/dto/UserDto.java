package club.luckylight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Id，主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像的地址
     */
    private String avatarUrl;

    /**
     * 用户对应角色的 Id
     */
    private String roleId;

    /**
     * 用户对应角色的名称
     */
    private String roleName;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 账号的状态，默认为可用。1-可用，0-不可用
     */
    private Byte status;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;
}
