package me.urie.overseas_study.utils;

import me.urie.overseas_study.bean.User;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUname(HttpSession session) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContextImpl == null)
            return null;
        User user = (User) securityContextImpl.getAuthentication().getPrincipal();
        return user.getUname();
    }

    public static int getUid(HttpSession session) {
        SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        if (securityContextImpl == null)
            return 0;
        User user = (User) securityContextImpl.getAuthentication().getPrincipal();
        return user.getUid();
    }
}
