package club.luckylight.service.impl;

import club.luckylight.dto.UserDto;
import club.luckylight.mapper.RoleMapper;
import club.luckylight.mapper.UserMapper;
import club.luckylight.model.Role;
import club.luckylight.model.User;
import club.luckylight.service.UserService;
import club.luckylight.vo.user.UserAddRequestVo;
import club.luckylight.vo.user.UserListRequestVo;
import club.luckylight.vo.user.UserUpdateRequestVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Page<UserDto> getUserList(UserListRequestVo vo) {
        // 分页
        PageHelper.startPage(vo.getCurrentPage(), vo.getPageSize());

        return userMapper.getUserList();
    }

    @Override
    public Boolean addUser(UserAddRequestVo vo) {
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setPassword(vo.getPassword());
        user.setAvatarUrl(vo.getAvatarUrl());
        user.setEmail(vo.getEmail());
        user.setMobile(vo.getMobile());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setStatus((byte) 1);
        user.setRoleId(vo.getRoleId());

        Role role = roleMapper.selectByPrimaryKey(vo.getRoleId());
        user.setRoleName(role.getRoleName());

        return userMapper.insert(user) == 1;
    }

    @Override
    public Boolean banUser(String id) {
        return userMapper.banUser(id) == 1;
    }

    @Override
    public Boolean allowUser(String id) {
        return userMapper.allowUser(id) == 1;
    }

    @Override
    public void banSelectionUser(List<Integer> ids) {
        ids.forEach(id -> banUser(id.toString()));
    }

    @Override
    public void allowSelectionUser(List<Integer> ids) {
        ids.forEach(id -> allowUser(id.toString()));
    }

    @Override
    public Boolean updateUser(UserUpdateRequestVo vo) {
        User user = new User();
        user.setId(vo.getId());
        user.setUsername(vo.getUsername());
        user.setAvatarUrl(vo.getAvatarUrl());
        user.setEmail(vo.getEmail());
        user.setMobile(vo.getMobile());
        user.setUpdateTime(new Date());
        user.setRoleId(vo.getRoleId());

        Role role = roleMapper.selectByPrimaryKey(vo.getRoleId());
        user.setRoleName(role.getRoleName());

        return userMapper.updateUser(user) == 1;
    }
}
