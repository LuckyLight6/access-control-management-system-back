package club.luckylight.mapper;

import club.luckylight.dto.UserDto;
import club.luckylight.model.User;
import com.github.pagehelper.Page;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    Page<UserDto> getUserList();

    int banUser(String id);

    int allowUser(String id);

    int updateUser(User user);
}