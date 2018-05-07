package club.luckylight.controller;

import club.luckylight.dto.UserDto;
import club.luckylight.service.UserService;
import club.luckylight.vo.Result;
import club.luckylight.vo.user.UserAddRequestVo;
import club.luckylight.vo.user.UserListRequestVo;
import club.luckylight.vo.user.UserListResponseVo;
import club.luckylight.vo.user.UserUpdateRequestVo;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result getUserList(UserListRequestVo vo) {
        Page<UserDto> page = userService.getUserList(vo);
        UserListResponseVo userListResponseVo = new UserListResponseVo();
        userListResponseVo.setTotal(page.getTotal());
        userListResponseVo.setUserList(page.getResult());

        return Result.ok(userListResponseVo);
    }

    @PutMapping("/add")
    public Result addUser(@RequestBody UserAddRequestVo vo) {
        Boolean result = userService.addUser(vo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/ban/{id}")
    public Result banUser(@PathVariable String id) {
        Boolean result = userService.banUser(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @PostMapping("/allow/{id}")
    public Result allowUser(@PathVariable String id) {
        Boolean result = userService.allowUser(id);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }

    @DeleteMapping("/banSelection")
    public Result banSelectionUser(@RequestBody List<Integer> ids) {
        userService.banSelectionUser(ids);

        return Result.ok(null);
    }

    @PostMapping("/allowSelection")
    public Result allowSelectionUser(@RequestBody List<Integer> ids) {
        userService.allowSelectionUser(ids);

        return Result.ok(null);
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody UserUpdateRequestVo vo) {
        Boolean result = userService.updateUser(vo);

        if (!result) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(null);
    }
}
