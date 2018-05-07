package club.luckylight.mapper;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.Permission;
import com.github.pagehelper.Page;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface PermissionMapper extends Mapper<Permission> {

    Page<Permission> getPermissionList();

    List<UseablePermissionDto> getUseablePermission();

    List<UseablePermissionDto> getRolePermissionByRoleId(String roleId);

    int banPermission(Integer id);

    int allowPermission(Integer id);

    int updatePermission(Permission permission);
}