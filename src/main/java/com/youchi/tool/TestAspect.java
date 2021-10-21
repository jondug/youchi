package com.youchi.tool;

import com.youchi.mapper.AdminMapper;
import com.youchi.model.Admin;
import com.youchi.responseMessage.ResponseMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class TestAspect {

    @Autowired
    private AdminMapper adminMapper;

    @Pointcut("execution(public * com.youchi.controller.*.NL*(..))")

    public void doOperation() {}

    @Before("doOperation()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("------------doBefore----------");
    }

    @After("doOperation()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("------------doAfter----------");
    }



    @Around("doOperation()")
    public ResponseMessage doAround(ProceedingJoinPoint joinPoint) throws Throwable {

//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        //从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        String token = request.getHeader("token");
//        if(token==null&&"".equals(token)){
//            return ResponseMessage.error("请先登录");
//        }
//        System.out.println(token);
//        final Admin token1 = adminMapper.findToken(token);
//        if(token1==null){
//            return ResponseMessage.error("请先登录");
//        }
        //执行方法
        Object proceed = joinPoint.proceed();
        System.out.println(proceed);

        return (ResponseMessage) proceed;
    }


}
