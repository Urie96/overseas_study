package me.urie.overseas_study.cotroller;

import me.urie.overseas_study.mapper.UserMapper;
import me.urie.overseas_study.utils.RandomStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class MailController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    JavaMailSenderImpl mailSender;

    @RequestMapping("/forget_pwd")
    public String sendEmail(String email, String password, HttpServletResponse response, HttpSession session) throws IOException, MessagingException {
        String uname = userMapper.selectEmail(email);
        if (uname == null)
            return "此邮箱未注册";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String random = RandomStr.getRandomString(10);
        session.setAttribute("password", password);
        session.setAttribute("random", random);
        session.setAttribute("uname", uname);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("留学信息网密码修改确认");
        helper.setText("亲爱的<span style='color:gold'>" + uname + "</span>用户," +
                "您的密码将被修改为" + password.substring(0, 2) + "****," +
                "如果是您本人的操作,请" +
                "<a href='129.211.25.132/confirmUpdate/" + random +
                "'><strong>点击此处</strong></a>完成修改(2天内有效),如果没出现修改成功请刷新一下", true);

        helper.setTo(email);
        helper.setFrom("1043629668@qq.com");

        mailSender.send(mimeMessage);
        response.sendRedirect("/turnEmail.html?" + email);
        return null;
    }

    @RequestMapping("/confirmUpdate/{random}")
    public String confirmUpdate(@PathVariable("random") String random, HttpSession session) {
        String password = (String) session.getAttribute("password");
        String uname = (String) session.getAttribute("uname");
        String realRandom = (String) session.getAttribute("random");
        if (password == null || uname == null || !random.equals(realRandom))
            return "此链接已过期";
        session.removeAttribute("password");
        session.removeAttribute("uname");
        session.removeAttribute("random");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        return userMapper.passwordUpdate(uname, password) == 1 ? "修改成功" : "修改失败";
    }
}
