package club.luckylight.controller;

import club.luckylight.model.User;
import club.luckylight.service.AuthService;
import club.luckylight.util.JWTUtils;
import club.luckylight.vo.Result;
import club.luckylight.vo.auth.InfoResponseVo;
import club.luckylight.vo.auth.LoginResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * 登录、注册、获取 token、注销
 *
 * @author liuruiming
 * @date 2018/4/29
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        User user = authService.login(username, password);

        if (user == null) {
            return new Result(50001, "用户名或密码有误", null);
        }

        if (user.getRoleId() != 1) {
            return new Result(50002, "对不起，您没有权限访问！", null);
        }

        if (user.getStatus() != 1) {
            return new Result(50003, "账户已被禁用", null);
        }

        LoginResponseVo loginResponseVo = null;
        try {
            loginResponseVo = new LoginResponseVo();
            loginResponseVo.setToken(JWTUtils.createToken(user.getUsername()));
        } catch (Exception e) {
            return new Result(50000, "服务异常", null);
        }

        return Result.ok(loginResponseVo);
    }

    @GetMapping("/info")
    public Result info(String token) {
        User user = null;
        try {
            user = authService.info(JWTUtils.verifyToken(token));
        } catch (UnsupportedEncodingException e) {
            return new Result(50000, "服务异常", null);
        }

        if (user == null) {
            return new Result(50004, "非法请求", null);
        }

        if (user.getStatus() != 1) {
            return new Result(50003, "账户已被禁用", null);
        }

        // 封装返回结果
        InfoResponseVo infoResponseVo = new InfoResponseVo();
        infoResponseVo.setRoles(user.getRoleName());
        infoResponseVo.setName(user.getUsername());
        infoResponseVo.setAvatar(user.getAvatarUrl());

        return Result.ok(infoResponseVo);
    }

    @PostMapping("/logout")
    public Result logout() {
        return Result.ok(null);
    }
}
