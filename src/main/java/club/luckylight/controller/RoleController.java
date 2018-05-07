package club.luckylight.controller;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.dto.UseableRoleDto;
import club.luckylight.model.Role;
import club.luckylight.service.RoleService;
import club.luckylight.vo.Result;
import club.luckylight.vo.role.RoleAddRequestVo;
import club.luckylight.vo.role.RoleUpdateRequestVo;
import club.luckylight.vo.role.UseableRoleResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/usableList")
    public Result getUsableRole() {
        List<UseableRoleDto> roleList = roleService.getUseableRole();

        UseableRoleResponseVo useableRoleResponseVo = new UseableRoleResponseVo();
        useableRoleResponseVo.setRoleList(roleList);

        return Result.ok(useableRoleResponseVo);
    }

    @GetMapping("/list")
    public Result getRoleList() {
        List<Role> roleList = roleService.getRoleList();

        return Result.ok(roleList);
    }

    @PutMapping("/add")
    public Result addRole(@RequestBody RoleAddRequestVo requestVo) {
        Boolean result = roleService.addRole(requestVo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @PostMapping("/update")
    public Result updateRole(@RequestBody RoleUpdateRequestVo requestVo) {
        Boolean result = roleService.updateRole(requestVo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/ban/{id}")
    public Result banRole(@PathVariable String id) {
        Boolean result = roleService.banRole(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
}

    @PostMapping("/allow/{id}")
    public Result allowRole(@PathVariable String id) {
        Boolean result = roleService.allowRole(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/banSelection")
    public Result banSelectionRole(@RequestBody List<Integer> ids) {
        roleService.banSelectionRole(ids);

        return Result.ok(null);
    }

    @PostMapping("/allowSelection")
    public Result allowSelectionRole(@RequestBody List<Integer> ids) {
        roleService.allowSelectionRole(ids);

        return Result.ok(null);
    }

    @GetMapping("/rolePermission/{roleId}")
    public Result getRolePermission(@PathVariable String roleId) {
        List<UseablePermissionDto> rolePermission = roleService.getRolePermissionByRoleId(roleId);

        return Result.ok(rolePermission);
    }
}
