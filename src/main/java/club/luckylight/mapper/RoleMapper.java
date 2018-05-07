package club.luckylight.mapper;

import club.luckylight.dto.UseableRoleDto;
import club.luckylight.model.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface RoleMapper extends Mapper<Role> {

    List<UseableRoleDto> getUseableRole();

    @Override
    int insert(Role role);

    int banRole(String id);

    int allowRole(String id);

    int updateRole(Role role);
}