package club.luckylight.vo.role;

import club.luckylight.dto.UseableRoleDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UseableRoleResponseVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<UseableRoleDto> roleList;
}
