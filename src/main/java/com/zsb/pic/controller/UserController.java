package com.zsb.pic.controller;

import com.zsb.pic.common.R;
import com.zsb.pic.dto.MyPageDTO;
import com.zsb.pic.entity.User;
import com.zsb.pic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author z's'b
 * @version 1.0
 * @date 2022/10/30 15:05
 */
@Slf4j
@RestController
@RequestMapping("/user")
//@Validated 给控制器加 后续传的参数即可加上对用的注解生效
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody User user) {
        User data = userService.getUser(user);
        return R.ok(data);
    }

    @DeleteMapping("/delete/{userId}")
    public R<Boolean> delete(@PathVariable Long userId) {
        Boolean flag = userService.deleteUser(userId);
        return flag ? R.ok(true) : R.err(false);
    }

    @PutMapping("/update")
    public R<Boolean> update(@RequestBody User user) {
        Boolean flag = userService.updateUser(user);
        return flag ? R.ok(true) : R.err(false);
    }

    @GetMapping("/getUsers")
    public R<List<User>> getUsers() {
        return R.ok(userService.getUsers());
    }

    @GetMapping("/getPageUsers/{page}/{size}")
    public R<MyPageDTO<User>> getPageUsers(@PathVariable Long page, @PathVariable Long size) {
        return R.ok(userService.getPageUsers(page, size));
    }

}
