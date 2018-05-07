package club.luckylight.vo.user;

import club.luckylight.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserListResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long total;

    private List<UserDto> userList;
}
