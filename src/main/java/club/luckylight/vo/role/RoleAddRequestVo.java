package club.luckylight.vo.role;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RoleAddRequestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String roleName;

    private List<Integer> permissionList;
}
