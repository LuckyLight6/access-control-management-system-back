package club.luckylight.vo.permission;

import club.luckylight.dto.UseablePermissionDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UseablePermissionResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<UseablePermissionDto> permissionList;
}
