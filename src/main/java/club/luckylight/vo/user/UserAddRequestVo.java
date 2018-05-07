package club.luckylight.vo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAddRequestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String avatarUrl;

    private Integer roleId;

    private String email;

    private String mobile;
}
