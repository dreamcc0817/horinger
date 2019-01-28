package com.bonc.boot.aop;

import com.bonc.boot.util.AppReply;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.io.Serializable;


/**
 * @project_name：zhlq-my
 * @package_name：com.bonc.ioc.common.utils
 * @describe：***
 * @creater lyh
 * @creat_time 2018/11/5 10:28 
 * @changer   ***  
 * @change_time 2018/11/5 10:28 
 * @remark   ***
 * @version V0.1
 */
@RestControllerAdvice
@Order(-1)
public class ValidParamExceptionHandler implements Serializable{
	
	/**
	 * 用来处理bean validation异常
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public AppReply resolveConstraintViolationException(ConstraintViolationException ex){
		String str = ex.getMessage();
		String msg = str.substring(str.indexOf(":")+1);
		return AppReply.error(msg.trim());
	}
}
