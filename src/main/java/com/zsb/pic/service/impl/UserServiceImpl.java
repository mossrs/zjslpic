package com.zsb.pic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.entity.User;
import com.zsb.pic.mapper.UserMapper;
import com.zsb.pic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/29 12:50
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser(User user) {
        QueryWrapper<User> registerQw = new QueryWrapper<>();
        registerQw.eq("username", user.getUsername());
        User isUser = userMapper.selectOne(registerQw);
        if (isUser == null) {
            user.setIsAdmin(false);
            user.setDeleted(false);
            user.setCreateTime(LocalDateTime.now());
            try {
                userMapper.insert(user);
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("用户名已存在");
            }
        } else {
            QueryWrapper<User> loginQw = new QueryWrapper<>();
            loginQw.eq("username", user.getUsername());
            loginQw.eq("password", user.getPassword());
            User loginUser = userMapper.selectOne(loginQw);
            if (loginUser == null) {
                throw new RuntimeException("用户名或密码错误，请重新输入");
            }
            return loginUser;
        }
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public MyPageDTO<User> getPageUsers(Long page, Long size) {
        Page<User> userPage = userMapper.selectPage(new Page<>(page, size), null);
        return new MyPageDTO<>(userPage);
    }

    @Override
    public Boolean deleteUser(Long userId) {
        try {
            return userMapper.deleteById(userId) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public Boolean updateUser(User user) {
        try {
            return userMapper.updateById(user) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("编辑失败");
        }
    }
}
