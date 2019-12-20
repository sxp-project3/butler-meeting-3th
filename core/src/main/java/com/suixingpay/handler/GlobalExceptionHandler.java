package com.suixingpay.handler;

import com.suixingpay.enumeration.CodeEnum;
import com.suixingpay.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author 詹文良
 * @program: butler-meeting-3th
 * @description: 全局异常处理类
 * <p>
 * Created by Jalr4ever on 2019/12/17.
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 默认异常处理
     *
     * @param e 祖先异常类
     * @return 其他类型的异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exceptionHandler(Exception e) {
        log.warn(e.getMessage());
        return Response.getInstance(CodeEnum.FAIL, e.getMessage());
    }

    /**
     * 方法参数异常处理器
     *
     * @param manve 方法参数异常类
     * @return 其他服务器异常信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manve) {
        BindingResult bindingResult = manve.getBindingResult();
        StringBuilder sbLog = new StringBuilder();
        StringBuilder sbResponse = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {

                // 响应信息
                sbResponse.append(fieldError.getDefaultMessage()).append("、");

                // 日志信息
                sbLog.append(fieldError.getDefaultMessage()).append(" : ");
                sbLog.append(fieldError.getField()).append(" is ");
                sbLog.append(fieldError.getRejectedValue()).append(" 、");

            }
        }

        sbResponse.deleteCharAt(sbResponse.length() - 1);
        sbLog.deleteCharAt(sbLog.length() - 1);

        log.error(sbLog.toString());
        return Response.getInstance(CodeEnum.FAIL, sbResponse.toString());
    }

    /**
     * 参数校验异常处理器
     *
     * @param cve 参数校验异常类
     * @return 参数校验异常信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Response constraintViolationExceptionHandler(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
        StringBuilder sbLog = new StringBuilder();
        StringBuilder sbResponse = new StringBuilder();
        for (ConstraintViolation violation : constraintViolations) {

            // 返回信息
            sbResponse.append(violation.getMessage()).append("、");

            // 日志信息
            sbLog.append(violation.getMessage()).append(" : ");
            sbLog.append(violation.getPropertyPath()).append(" is ");
            sbLog.append(violation.getInvalidValue()).append("、");

        }

        sbResponse.deleteCharAt(sbResponse.length() - 1);
        sbLog.deleteCharAt(sbLog.length() - 1);
        log.error(sbLog.toString());

        return Response.getInstance(CodeEnum.FAIL, sbResponse.toString());
    }

}
