package club.luckylight.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class UseableRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色 Id，主键
     */
    private String roleId;

    /**
     * 角色名称
     */
    private String roleName;
}
