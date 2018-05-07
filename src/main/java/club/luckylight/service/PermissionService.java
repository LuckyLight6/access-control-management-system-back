package club.luckylight.service;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.Permission;
import club.luckylight.vo.permission.PermissionAddRequestVo;
import club.luckylight.vo.permission.PermissionListRequestVo;
import club.luckylight.vo.permission.PermissionUpdateRequestVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface PermissionService {

    Boolean addPermission(PermissionAddRequestVo vo);

    Page<Permission> getPermissionList(PermissionListRequestVo vo);

    List<UseablePermissionDto> getUseablePermission();

    Boolean banPermission(Integer id);

    Boolean allowPermission(Integer id);

    void banSelectionPermission(List<Integer> ids);

    void allowSelectionPermission(List<Integer> ids);

    Boolean updatePermission(PermissionUpdateRequestVo vo);
}
