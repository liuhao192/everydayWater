package ren.kura.everydaywater.system.aspect;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import ren.kura.everydaywater.common.aspect.annotation.AutoLog;
import ren.kura.everydaywater.common.constant.CommonConstant;
import ren.kura.everydaywater.common.utils.IPUtils;
import ren.kura.everydaywater.common.utils.SpringContextUtils;
import ren.kura.everydaywater.system.entity.SystemLog;
import ren.kura.everydaywater.system.service.ISysLogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * <p>项目名称: everydaywater
 * <p>文件名称: AutoLogAspect.java
 * <p>描述: 系统日志，切面处理类
 * <p>HISTORY: 2020/10/11 liuha : Created
 *
 * @author <a href="https://www.kura.ren" target="_blank">liuha</a>
 * @version 1.0
 * @date: 2020/10/11 10:49
 */
@Aspect
@Component
public class AutoLogAspect {
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(ren.kura.everydaywater.common.aspect.annotation.AutoLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SystemLog sysLog = new SystemLog();
        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if(syslog != null){
            //注解上的描述,操作日志内容
            sysLog.setLogContent(syslog.value());
            sysLog.setLogType(syslog.logType());

        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //设置操作类型
        if (sysLog.getLogType() == CommonConstant.LOG_TYPE_2) {
            sysLog.setOperateType(getOperateType(methodName, syslog.operateType()));
        }
        //获取request
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //请求的参数
        sysLog.setRequestParam(getRequestParams(request,joinPoint));
        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        //耗时
        sysLog.setCostTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }
/**
 *  AutoLogAspect:: getOperateType
 *  <p>获取操作类型
 *  <p>HISTORY: 2020/10/15 liuha : Created.
 *  @param    methodName  方法名称
 *  @param    operateType  操作类型
 *  @return    具体查看see标签中CommonConstant.java类
 *  @see CommonConstant
 */
    private int getOperateType(String methodName,int operateType) {
        if (operateType > 0) {
            return operateType;
        }
        if (methodName.startsWith("list")) {
            return CommonConstant.OPERATE_TYPE_1;
        }
        if (methodName.startsWith("add")) {
            return CommonConstant.OPERATE_TYPE_2;
        }
        if (methodName.startsWith("edit")) {
            return CommonConstant.OPERATE_TYPE_3;
        }
        if (methodName.startsWith("delete")) {
            return CommonConstant.OPERATE_TYPE_4;
        }
        if (methodName.startsWith("import")) {
            return CommonConstant.OPERATE_TYPE_5;
        }
        if (methodName.startsWith("export")) {
            return CommonConstant.OPERATE_TYPE_6;
        }
        return CommonConstant.OPERATE_TYPE_1;
    }


    /**
     *  AutoLogAspect:: getRequestParams
     *  <p>获取请求参数
     *  <p>HISTORY: 2020/10/15 liuha : Created.
     *  @param    request  请求HttpServletRequest
     *  @param    joinPoint  切点
     *  @return   String   返回请求参数
     */
    private String getRequestParams(HttpServletRequest request, JoinPoint joinPoint) {
        String httpMethod = request.getMethod();
        String params = "";
        if ("POST".equals(httpMethod) || "PUT".equals(httpMethod) || "PATCH".equals(httpMethod)) {
            Object[] paramsArray = joinPoint.getArgs();
            params = new Gson().toJson(paramsArray);
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params += "  " + paramNames[i] + ": " + args[i];
                }
            }
        }
        return params;
    }
}
