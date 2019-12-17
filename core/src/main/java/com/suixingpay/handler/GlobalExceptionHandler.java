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
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(fieldError.getDefaultMessage()).append(" : ");
                sb.append(fieldError.getField()).append(" is ");
                sb.append(fieldError.getRejectedValue()).append(" 、");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        log.error(sb.toString());
        return Response.getInstance(CodeEnum.FAIL, sb.toString());
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
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation violation : constraintViolations) {
            sb.append(violation.getMessage()).append(" : ");
            sb.append(violation.getPropertyPath()).append(" is ");
            sb.append(violation.getInvalidValue()).append("、");
        }
        sb.deleteCharAt(sb.length() - 1);
        log.error(sb.toString());
        return Response.getInstance(CodeEnum.FAIL, sb.toString());
    }

}
