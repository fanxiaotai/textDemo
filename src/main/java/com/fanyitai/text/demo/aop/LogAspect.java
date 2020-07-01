package com.fanyitai.text.demo.aop;

import com.fanyitai.text.demo.util.ExceptionLogUtils;
import com.fanyitai.text.demo.util.ResultEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * 日志和登陆校验切面
 */
@Aspect
@Component
public class LogAspect {


    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.fanyitai.text.demo.controller..*(..))")
    public void webLog(){}

    @Pointcut("execution(@com.fanyitai.text.demo.annotation.Login * *(..))")
    public void webLogin(){}


    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        return baseAOP(pjp,request);
    }

    //@before代表在目标方法执行前切入, 并指定在哪个方法前切入
    @Before("webLogin()")
    public void loginCheck() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes!=null){
            HttpSession session = attributes.getRequest().getSession();
            Object userInfo = session.getAttribute("userInfo");
            if (userInfo==null){
                assert attributes.getResponse() != null;
                attributes.getResponse().sendRedirect("/toLogin");
            }
        }
    }

    @AfterThrowing(value = "webLog()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        logger.info(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            logger.info("发生了空指针异常!!!!!");
        }
    }

    /*//环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLogin()")
    public Object webLogin(ProceedingJoinPoint pjp) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        if (request.getSession().getAttribute("userInfo")==null){
            try {
                response.sendRedirect("/toLogin");
            } catch (IOException e) {
                LoggerFactory.getLogger(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()).error(e.getMessage(),e);
            }
        }
        return baseAOP(pjp,request);
    }*/

    private Object baseAOP(ProceedingJoinPoint pjp,HttpServletRequest request){
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(pjp.getArgs()));
        try {
            Object o =  pjp.proceed();
            return o;
        } catch (Throwable e) {
            //LoggerFactory.getLogger(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()).error(ExceptionLogUtils.E2String(e));
            LoggerFactory.getLogger(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()).error(e.getMessage(),e);
            return ResultEntity.failed("服务器繁忙");
        }
    }
}