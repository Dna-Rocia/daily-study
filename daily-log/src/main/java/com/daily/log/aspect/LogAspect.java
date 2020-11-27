package com.daily.log.aspect;


import com.daily.log.annotation.LogOperate;
import com.daily.log.model.ExceptionLog;
import com.daily.log.model.OperationLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Author ROCIA
 * @Date 2020/7/16
 * 对请求进入前后动作日志记录
 */
@Component
@Aspect    //通常是一个类，里面可以定义切入点和通知
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //region

    /**
     * 设置操作日志切入点 记录操作日志 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.daily.log.annotation.LogOperate)")
    public void operaLogPointCut() {
    }
    //endregion


    //region

    /**
     * 设置操作异常切入点记录异常日志 扫描所有controller包下操作
     */
    @Pointcut("execution(public * com.daily.*.controller.*(..))")
    public void operaExceptionLogPointCut() {
    }
    //endregion


    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     *
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "operaLogPointCut()", returning = "keys")
    public void saveOperaLog(JoinPoint joinPoint, Object keys) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        OperationLog operlog = new OperationLog();
        try {
            operlog.setOperaId(UUID.randomUUID().toString()); // 主键ID
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            LogOperate opLog = method.getAnnotation(LogOperate.class);
            if (opLog != null) {
                operlog.setOperaModel(opLog.operaModel()); // 操作模块
                operlog.setOperaType(opLog.operaType()); // 操作类型
                operlog.setOperaDesc(opLog.operaDesc()); // 操作描述
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            operlog.setOperaMethod(methodName); // 请求方法
            logger.info("operaLog: " + operlog.toString());

            // 请求的参数
//            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);
//            operlog.setOperaReqParam(params); // 请求参数
//            operlog.setOperaRespParam(JSON.toJSONString(keys)); // 返回结果
//            operlog.setOperaUserId(UserShiroUtil.getCurrentUserLoginName()); // 请求用户ID
//            operlog.setOperaUserName(UserShiroUtil.getCurrentUserName()); // 请求用户名称
//            operlog.setOperaIP(IPUtil.getRemortIP(request)); // 请求IP
//            operlog.setOperaURI(request.getRequestURI()); // 请求URI
//            operlog.setOperaCreateTime(new Date()); // 创建时间
//            operlog.setOperaVer(operVer); // 操作版本

// todo           operationLogService.insert(operlog);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息 连接点抛出异常后执行
     *
     * @param joinPoint 切入点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "operaExceptionLogPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        ExceptionLog excepLog = new ExceptionLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            excepLog.setExcId(UUID.randomUUID().toString());
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
//            String params = JSON.toJSONString(rtnMap);
//            excepLog.setExcReqParam(params); // 请求参数
//            excepLog.setOperaMethod(methodName); // 请求方法名
//            excepLog.setExcName(e.getClass().getName()); // 异常名称
//            excepLog.setExcMessage(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace())); // 异常信息
//            excepLog.setOperaUserId(UserShiroUtil.getCurrentUserLoginName()); // 操作员ID
//            excepLog.setOperaUserName(UserShiroUtil.getCurrentUserName()); // 操作员名称
//            excepLog.setOperaURI(request.getRequestURI()); // 操作URI
//            excepLog.setOperaIP(IPUtil.getRemortIP(request)); // 操作员IP
//            excepLog.setOperaVer(operVer); // 操作版本号
//            excepLog.setOperaCreateTime(new Date()); // 发生异常时间

//todo            exceptionLogService.insert(excepLog);

        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

    /**
     * 转换异常信息为字符串
     *
     * @param exceptionName    异常名称
     * @param exceptionMessage 异常信息
     * @param elements         堆栈信息
     */
    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        for (StackTraceElement stet : elements) {
            strbuff.append(stet + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        return message;
    }


}
