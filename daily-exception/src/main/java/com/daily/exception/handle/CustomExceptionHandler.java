package com.daily.exception.handle;

import com.daily.exception.constant.CustomExceptionConst;
import com.daily.exception.utils.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * 自定义全局异常
 */
@RestControllerAdvice
public class CustomExceptionHandler {

//    private Logger logger= LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * 处理ServletException
     *
     * @param ex 异常
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {ServletException.class})
    @ResponseBody
    protected final Response<?> handleServletException(ServletException ex) {
//        logger.error("{}", ex);

        return Response.buildFailResponse(CustomExceptionConst.FAILURE_SERVLET);
    }

    /**
     * 处理SQLException
     *
     * @param ex 异常
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {SQLException.class})
    @ResponseBody
    protected final Response<?> handleSQLException(SQLException ex) {
//        logger.error("{}", ex);

        return Response.buildFailResponse(CustomExceptionConst.FAILURE_DB);
    }

    /**
     * 处理ConstraintViolationException
     *
     * @param ex 异常
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    protected final Response<?> handleConstraintViolationException(ConstraintViolationException ex) {
//        logger.error("{}", ex);

        String message = ex.getMessage();
        if (ex.getConstraintViolations() != null && !ex.getConstraintViolations().isEmpty()) {
            message = ex.getConstraintViolations().stream().findFirst().isPresent()
                    ? ex.getConstraintViolations().stream().findFirst().get().getMessage() : null;
        }

        return Response.buildFailResponse(CustomExceptionConst.FAILURE_VALIDATION.code, message, null);
    }

    /**
     * 处理MorphedException
     *
     * @param ex 异常
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {CustomException.class})
    @ResponseBody
    protected final Response<?> handleMorphedException(CustomException ex) {
//        logger.info("{}", ex);

        return Response.buildFailResponse(ex.getCode(), ex.getMessage(), ex.getData());
    }

    /**
     * 处理RuntimeException
     *
     * @param ex 异常
     * @return 异常处理结果
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseBody
    protected final Response<?> handleRuntimeException(RuntimeException ex) {
//        logger.error("{}", ex);

        return Response.buildFailResponse(CustomExceptionConst.FAILURE_UNKNOWN);
    }


}
