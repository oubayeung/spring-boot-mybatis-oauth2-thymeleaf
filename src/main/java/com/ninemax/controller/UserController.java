package com.ninemax.controller;

import com.ninemax.entity.User;
import com.ninemax.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Pual on 2016/8/26.
 */
@Controller
public class UserController {
    @Resource
    private UserServiceImpl userService;
    @RequestMapping("/user")
    @ResponseBody
    public User getUserInfo() {
        User user = (User) userService.loadUserByUsername("pual");
        return user;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) userService.loadUserByUsername("pual");
        model.addAttribute("id", user.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("loginId", user.getLoginId());
        model.addAttribute("role", user.getRole());
        model.addAttribute("createTime", user.getCreateTime());

        return "index";
    }

    @RequestMapping("/delete")
    public void delete() {
        try {
            userService.deleteUserById("f041e5a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
