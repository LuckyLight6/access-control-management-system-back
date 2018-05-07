package club.luckylight.vo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserListRequestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;
}
