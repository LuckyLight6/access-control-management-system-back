package club.luckylight.vo.permission;

import club.luckylight.model.Permission;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PermissionListResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long total;

    private List<Permission> permissionList;
}
