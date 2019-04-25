package me.urie.overseas_study.cotroller;

import me.urie.overseas_study.bean.Msg;
import me.urie.overseas_study.bean.User;
import me.urie.overseas_study.mapper.UserMapper;
import me.urie.overseas_study.utils.SessionUtils;
import me.urie.overseas_study.utils.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/success")
    public Msg success() {
        return Msg.success();
    }

    @RequestMapping("/fail")
    public Msg fail() {
        return Msg.fail();
    }

    @RequestMapping("/signup")
    public Msg signUp(User user, @RequestParam("passcode") String passcode, HttpSession session) {
        String code = (String) session.getAttribute("verifyCodeValue");
        if (!passcode.equalsIgnoreCase(code)) {
            return Msg.fail().add("tip", "验证码错误");
        }
        String bcrypt = new BCryptPasswordEncoder().encode(user.getUpassword());
        user.setUpassword(bcrypt);
        return userMapper.insert(user) == 1 ? Msg.success() : Msg.fail();
    }

    @RequestMapping("/checkCode")
    public void generate(HttpServletResponse response, HttpSession session) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = VerifyCode.drawImg(output);
        session.setAttribute("verifyCodeValue", verifyCodeValue);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/checkuname")
    public Msg checkUname(@RequestParam("uname") String uname) {
        return userMapper.getUserByName(uname) == null ? Msg.fail() : Msg.success();
    }

    @RequestMapping("/checkemail")
    public Msg checkemail(@RequestParam("email") String email) {
        return userMapper.selectEmail(email) == null ? Msg.fail() : Msg.success();
    }

    @RequestMapping("/uname.json")
    public Msg sendUname(HttpSession session) {
        String uname = SessionUtils.getUname(session);
        return uname == null ? Msg.fail() : Msg.success().add("name", uname);
    }

    @RequestMapping("/passwordUpdate")
    public Msg passwordUpdate(String oldPassword, String newPassword, HttpSession session) {
        String uname = SessionUtils.getUname(session);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String dbpwd = userMapper.getUserByName(uname).getUpassword();
        if (!encoder.matches(oldPassword, dbpwd))
            return Msg.fail();
        newPassword = encoder.encode(newPassword);
        return userMapper.passwordUpdate(uname, newPassword) == 1 ? Msg.success() : Msg.fail();
    }
}
