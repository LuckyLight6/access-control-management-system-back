package club.luckylight.controller;

import club.luckylight.dto.UseablePermissionDto;
import club.luckylight.model.Permission;
import club.luckylight.service.PermissionService;
import club.luckylight.vo.Result;
import club.luckylight.vo.permission.*;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@CrossOrigin(origins = "*")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/usableList")
    public Result getUsablePermission() {
        List<UseablePermissionDto> permissionList = permissionService.getUseablePermission();

        UseablePermissionResponseVo useablePermissionResponseVo = new UseablePermissionResponseVo();
        useablePermissionResponseVo.setPermissionList(permissionList);

        return Result.ok(useablePermissionResponseVo);
    }

    @GetMapping("/list")
    public Result getPermissionList(PermissionListRequestVo vo) {
        Page<Permission> page = permissionService.getPermissionList(vo);
        PermissionListResponseVo permissionListResponseVo = new PermissionListResponseVo();
        permissionListResponseVo.setTotal(page.getTotal());
        permissionListResponseVo.setPermissionList(page.getResult());

        return Result.ok(permissionListResponseVo);
    }

    @PutMapping("/add")
    public Result addPermission(@RequestBody PermissionAddRequestVo requestVo) {
        Boolean result = permissionService.addPermission(requestVo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/ban/{id}")
    public Result banPermission(@PathVariable Integer id) {
        Boolean result = permissionService.banPermission(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @PostMapping("/allow/{id}")
    public Result allowPermission(@PathVariable Integer id) {
        Boolean result = permissionService.allowPermission(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/banSelection")
    public Result banSelectionPermission(@RequestBody List<Integer> ids) {
        permissionService.banSelectionPermission(ids);

        return Result.ok(null);
    }

    @PostMapping("/allowSelection")
    public Result allowSelectionPermission(@RequestBody List<Integer> ids) {
        permissionService.allowSelectionPermission(ids);

        return Result.ok(null);
    }

    @PostMapping("/update")
    public Result updatePermission(@RequestBody PermissionUpdateRequestVo requestVo) {
        Boolean result = permissionService.updatePermission(requestVo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }
}
