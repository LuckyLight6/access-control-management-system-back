package club.luckylight.service;

import club.luckylight.dto.UserDto;
import club.luckylight.vo.user.UserAddRequestVo;
import club.luckylight.vo.user.UserListRequestVo;
import club.luckylight.vo.user.UserUpdateRequestVo;
import com.github.pagehelper.Page;

import java.util.List;

public interface UserService {

    Page<UserDto> getUserList(UserListRequestVo vo);

    Boolean addUser(UserAddRequestVo vo);

    Boolean banUser(String id);

    Boolean allowUser(String id);

    void banSelectionUser(List<Integer> ids);

    void allowSelectionUser(List<Integer> ids);

    Boolean updateUser(UserUpdateRequestVo vo);
}
