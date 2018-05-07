package club.luckylight.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UseablePermissionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限 Id，主键
     */
    private Integer permissionId;

    /**
     * 权限名称
     */
    private String permissionName;
}
