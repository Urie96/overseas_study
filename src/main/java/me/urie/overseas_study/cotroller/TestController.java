package me.urie.overseas_study.cotroller;

import me.urie.overseas_study.bean.User;
import me.urie.overseas_study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @GetMapping("/query/{id}")
    public Map<String, Object> map(@PathVariable("id") int id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from user");
        return list.get(id);
    }

    @ResponseBody
    @RequestMapping("/test11")
    public String test11(HttpSession session) {
        System.out.println("开始");
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) securityContextImpl.getAuthentication().getPrincipal();
        System.out.println(user.getUsername());
        return "over";
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userMapper.getUserById(id);
    }
}
