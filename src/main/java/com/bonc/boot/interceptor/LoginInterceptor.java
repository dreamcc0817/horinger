package com.bonc.boot.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.bonc.boot.util.AppReply;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Log log = LogFactory.get(LoginInterceptor.class);

    /*@Autowired
    UserService userService;*/

    /*@Value("${IGNORE_LOGIN}")
    Boolean IGNORE_LOGIN;*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String basePath = request.getContextPath();
        String path = request.getRequestURI();

        if (!doLoginInterceptor(path, basePath)) {//是否进行登陆拦截
            return true;
        }
//      HttpSession session = request.getSession();
//      int userID = 2;
//      UserInfo userInfo = sysUserService.getUserInfoByUserID(userID);
//      System.out.println(JsonUtil.toJson(userInfo));
//      session.setAttribute(Constants.SessionKey.USER, userInfo);

        //如果登录了，会把用户信息存进session
        HttpSession session = request.getSession();
        Object user = session.getAttribute("loginUser");
        if (ObjectUtil.isNull(user)) {
            AppReply appReply = AppReply.relogin_2();

            //TODO 此处暂时锁住
//            response.getWriter().write(JSONUtil.toJsonStr(appReply));
//            return false;
        }
        return true;
    }

    private boolean doLoginInterceptor(String path, String basePath) {
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        //notLoginPaths.add("/");
        notLoginPaths.add("/index");
        notLoginPaths.add("/signin");
        notLoginPaths.add("/login");
        notLoginPaths.add("/register");
        notLoginPaths.add("/kaptcha.jpg");
        notLoginPaths.add("/kaptcha");
        //notLoginPaths.add("/sys/logout");
        //notLoginPaths.add("/loginTimeout");

        if (notLoginPaths.contains(path)) {
            return false;
        }
        return true;
    }
}
