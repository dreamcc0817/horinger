package com.bonc.boot.aop;

import com.bonc.boot.util.AppReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局的的异常拦截器（拦截所有的控制器）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());


//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public AppReply defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//         if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
//             logSend.sendException(e);
//            return  AppReply.error("接口路径不存在","404");
//         }else{
//             return  AppReply.error("内部错误","500");
//         }
//     }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public AppReply notFount(RuntimeException e) {
        e.printStackTrace();
        log.error("业务异常:", e.getMessage());
        return AppReply.error("业务异常:500");
    }


    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseBody
    public AppReply requestMissingServletRequest(DataIntegrityViolationException ex) {
        String str = ex.getMessage();
        String param = str.substring(str.lastIndexOf("column '") + 8, str.lastIndexOf("'"));
        log.error("参数" + param + "超出长度限制");
        return AppReply.error("参数" + param + "超出长度限制");
    }

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
    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    public AppReply resolveTransactionSystemException(TransactionSystemException e){
        log.error("运行时异常:", e.getMessage());
        return AppReply.error("数据库连接断开");
    }




 }
