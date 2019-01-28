package com.bonc.boot.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpSession;

/**
 * 作用：〈〉
 *
 * @author TanRW
 * @create 2019/1/25
 * @since 1.0.0
 */
public class SessionUtil {

    public static String getUserId(HttpSession session){
        Object user = session.getAttribute("loginUser");
        if (ObjectUtil.isNull(user)|| StrUtil.isEmpty(user.toString())){
            return null;
        }
        return user.toString();
    }

}