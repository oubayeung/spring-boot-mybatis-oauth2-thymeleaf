package com.ninemax.comtroller;

import com.ninemax.entity.User;
import com.ninemax.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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
        //String uuid = UUID.randomUUID().toString().replace("-", "");
        //System.out.println(uuid);
        User user = (User) userService.loadUserByUsername("pual");

        //SimpleDateFormat format = new SimpleDateFormat();
        //System.out.print(format.format(user.getCreateTime()));
        //user.setCreateTime();
        return user;
    }

    @RequestMapping("/index")
    public String index(Model model) {
        User user = (User) userService.loadUserByUsername("pual");
        model.addAttribute("id", user.getId());
        model.addAttribute("createTime", user.getCreateTime());
        model.addAttribute("loginId", user.getLoginId());
        model.addAttribute("role", user.getRole());

        return "index";
    }



}
