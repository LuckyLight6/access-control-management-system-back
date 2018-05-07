package club.luckylight.service;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.dto.UseableRoleDto;
import club.luckylight.model.Role;
import club.luckylight.vo.role.RoleAddRequestVo;
import club.luckylight.vo.role.RoleUpdateRequestVo;

import java.util.List;

public interface RoleService {

    List<UseableRoleDto> getUseableRole();

    List<Role> getRoleList();

    Boolean addRole(RoleAddRequestVo vo);

    Boolean updateRole(RoleUpdateRequestVo vo);

    Boolean banRole(String id);

    Boolean allowRole(String id);

    void banSelectionRole(List<Integer> ids);

    void allowSelectionRole(List<Integer> ids);

    List<UseablePermissionDto> getRolePermissionByRoleId(String roleId);
}
