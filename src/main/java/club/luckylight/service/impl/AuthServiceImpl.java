package club.luckylight.service.impl;

import club.luckylight.mapper.UserMapper;
import club.luckylight.model.User;
import club.luckylight.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username).andEqualTo("password", password);
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }

        return null;
    }

    @Override
    public User info(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }

        return null;
    }
}
