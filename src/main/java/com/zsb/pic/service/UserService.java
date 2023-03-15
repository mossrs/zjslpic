package com.zsb.pic.service;

import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.entity.User;

import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/29 12:51
 */
public interface UserService {

    /**
     * 登录或注册
     *
     * @param user 用户
     * @return 用户信息
     */
    User getUser(User user);


    /**
     * 返回所有用户信息
     *
     * @return 用户信息
     */
    List<User> getUsers();

    /**
     * 返回分页用户信息
     *
     * @param page 页码
     * @param size 每页数量
     * @return 用户信息
     */
    MyPageDTO<User> getPageUsers(Long page, Long size);


    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return 删除结果
     */
    Boolean deleteUser(Long userId);

    /**
     * 更新获取用户信息
     *
     * @param user 用户
     * @return 结果
     */
    Boolean updateUser(User user);


}
