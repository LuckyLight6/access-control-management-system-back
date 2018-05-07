package club.luckylight.service.impl;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.dto.UseableRoleDto;
import club.luckylight.mapper.PermissionMapper;
import club.luckylight.mapper.RoleMapper;
import club.luckylight.mapper.RolePermissionMapper;
import club.luckylight.model.Role;
import club.luckylight.model.RolePermission;
import club.luckylight.service.RoleService;
import club.luckylight.vo.role.RoleAddRequestVo;
import club.luckylight.vo.role.RoleUpdateRequestVo;
import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<UseableRoleDto> getUseableRole() {
        return roleMapper.getUseableRole();
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.selectAll();
    }

    @Override
    public Boolean addRole(RoleAddRequestVo vo) {
        Role role = new Role();
        role.setRoleName(vo.getRoleName());
        role.setStatus((byte) 1);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());

        int i = roleMapper.insert(role);

        addRolePermission(role, vo.getPermissionList());

        return i == 1;
    }

    @Override
    public Boolean updateRole(RoleUpdateRequestVo vo) {
        Role role = new Role();
        role.setRoleId(vo.getRoleId());
        role.setRoleName(vo.getRoleName());
        role.setUpdateTime(new Date());

        int i = roleMapper.updateRole(role);

        Example example = new Example(RolePermission.class);
        example.createCriteria().andEqualTo("roleId", vo.getRoleId());
        rolePermissionMapper.deleteByExample(example);

        addRolePermission(role, vo.getPermissionList());

        return i == 1;
    }

    @Override
    public Boolean banRole(String id) {
        return roleMapper.banRole(id) == 1;
    }

    @Override
    public Boolean allowRole(String id) {
        return roleMapper.allowRole(id) == 1;
    }

    @Override
    public void banSelectionRole(List<Integer> ids) {
        ids.forEach(id -> banRole(id.toString()));
    }

    @Override
    public void allowSelectionRole(List<Integer> ids) {
        ids.forEach(id -> allowRole(id.toString()));
    }

    @Override
    public List<UseablePermissionDto> getRolePermissionByRoleId(String roleId) {
        return permissionMapper.getRolePermissionByRoleId(roleId);
    }

    private void addRolePermission(Role role, List<Integer> permissionList) {
        if (CollUtil.isNotEmpty(permissionList)) {
            for (Integer permissionId : permissionList) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setRoleId(role.getRoleId());
                rolePermission.setPermissionId(permissionId);
                rolePermission.setCreateTime(new Date());
                rolePermission.setUpdateTime(new Date());

                rolePermissionMapper.insert(rolePermission);
            }
        }
    }
}
