package com.bonc.boot.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bonc.boot.util.AppReply;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @version V0.1
 * @project_name：zhlq-my
 * @package_name：com.bonc.ioc.common.aop
 * @describe：***
 * @creater lyh
 * @creat_time 2018/11/2 16:44
 * @changer ***
 * @change_time 2018/11/2 16:44
 * @remark ***
 */
@Aspect
@Component
public class AppAuthAnnotationAspect {

//    @Autowired
//    RedisTemplate redisTemplate;
    @Autowired
    private HttpServletRequest request;

    public AppAuthAnnotationAspect() {
//        log.info("初始化接口参数非空判断切面类...");
    }

    @Pointcut("@annotation(com.bonc.boot.aop.AppAuthAnnotation)")
    public void controllerInteceptor() {
    }

    @Around("controllerInteceptor()")
    public Object before(ProceedingJoinPoint pjp) throws Throwable {
//        //获取被注解的方法
//        MethodInvocationProceedingJoinPoint mjp = (MethodInvocationProceedingJoinPoint) pjp;
//        MethodSignature signature = (MethodSignature) mjp.getSignature();
//        Method method = signature.getMethod();
//        String tokenId = null;
////        HttpServletRequest request = HttpKit.getRequest();
//        AppAuthAnnotation systemLogAnnotation = method.getAnnotation(AppAuthAnnotation.class);
//        if (systemLogAnnotation != null) {
//            tokenId = request.getHeader("tokenId");
////            tokenId = request.getHeader("token");
//            if (StrUtil.isEmpty(tokenId)) {
//                return AppReply.error("请登录后使用");
//            }
//        }
//        PutMapping putMapping=method.getAnnotation(PutMapping.class);
//        if (null == redisTemplate) {
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            redisTemplate = (RedisTemplate) factory.getBean("redisTemplate");
//        }
//        Object s = redisTemplate.opsForValue().get(tokenId);//从redis中获取userId
//        if (ObjectUtil.isNull(s)) {
//            return AppReply.relogin();
//        }
//        //判断是否在别处登录
//        Object sessionId = redisTemplate.opsForValue().get(s);
//        if(sessionId==null||!sessionId.equals(tokenId)){
//            return AppReply.relogin_2();
//        }
//
//        //判断注解中的userId是否为true，不为true时判断userId和tokenId是否是同一用户
//        if (systemLogAnnotation.userId()) {
//            String userId = request.getParameter("userId");//获取参数中userId的值
//            if (!(s.toString()).equals(userId)) {//如果根据tokenId获取的userId与接口中传入的userId不同，那么证明不是同一用户
//                return AppReply.error("userId传入错误");
//            }
//
//        }
////        //保持登录
////        redisTemplate.expire(tokenId,30*60, TimeUnit.SECONDS);
////        redisTemplate.expire(s,30*60, TimeUnit.SECONDS);
//        //如果没有报错,放行
//        return pjp.proceed();
        return pjp.proceed();
    }
}
