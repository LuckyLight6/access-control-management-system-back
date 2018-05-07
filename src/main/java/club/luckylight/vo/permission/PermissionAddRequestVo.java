package club.luckylight.vo.permission;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PermissionAddRequestVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String permissionName;

    private String permissionIp;

    private Integer permissionPort;

    private String remark;
}
