package cn.compose.admin.config;

import cn.compose.admin.base.ApiCodes;
import cn.compose.admin.base.RespCodes;
import cn.compose.admin.base.Response;
import cn.compose.admin.base.ResponseBuilder;
import cn.compose.admin.exception.BizException;
import cn.compose.admin.exception.RateLimiterException;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * 全局捕获异常
 */
@RestControllerAdvice
@Slf4j
@ResponseBody
public class ErrorHandleConfig {

    /**
     * 运行时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Response runtimeExceptionHandler(RuntimeException ex) {
        return ResponseBuilder.error(500, ex.getMessage());
    }


    /**
     * 接口限流抛出异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(RateLimiterException.class)
    public Response rateLimiterExceptionHandler(RateLimiterException ex) {
        return ResponseBuilder.error(ApiCodes.RATE_LIMITER_ERROR, ex.getMessage());
    }

    /**
     * 空指针异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public Response<Object> nullPointerExceptionHandler(NullPointerException ex) {
        return ResponseBuilder.error(503, ex.getMessage());
    }


    /**
     * 类型转换异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    public Response<Object> classCastExceptionHandler(ClassCastException ex) {
        return ResponseBuilder.error(504, ex.getMessage());
    }

    /**
     * IO异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IOException.class)
    public Response<Object> iOExceptionHandler(IOException ex) {
        return ResponseBuilder.error(505, ex.getMessage());
    }

    /**
     * 未知方法异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public Response<Object> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return ResponseBuilder.error(506, ex.getMessage());
    }

    /**
     * 数组越界异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Response<Object> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return ResponseBuilder.error(507, ex.getMessage());
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Response<Object> requestNotReadable(HttpMessageNotReadableException ex) {
        return ResponseBuilder.error(508, ex.getMessage());
    }

    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    public Response<Object> requestTypeMismatch(TypeMismatchException ex) {
        return ResponseBuilder.error(509, ex.getMessage());
    }


    /**
     * 400错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public Response<Object> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return ResponseBuilder.error(10, ex.getMessage());
    }


    /**
     * 405错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Response<Object> request405(HttpRequestMethodNotSupportedException ex) {
        return ResponseBuilder.error(511, ex.getMessage());
    }


    /**
     * 406错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public Response<Object> request406(HttpMediaTypeNotAcceptableException ex) {
        return ResponseBuilder.error(512, ex.getMessage());
    }

    /**
     * 500错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public Response<Object> server500(RuntimeException ex) {
        return ResponseBuilder.error(513, ex.getMessage());
    }

    /**
     * 栈溢出
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({StackOverflowError.class})
    public Response<Object> requestStackOverflow(StackOverflowError ex) {
        return ResponseBuilder.error(514, ex.getMessage());
    }

    /**
     * 除数不能为0
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({ArithmeticException.class})
    public Response<Object> arithmeticException(ArithmeticException ex) {
        return ResponseBuilder.error(515, ex.getMessage());
    }

    /**
     * 拦截捕捉自定义异常 BizException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Response<Object> bizErrorHandler(BizException ex) {
        log.info(ex.getMessage(), ex);
        return ResponseBuilder.error(RespCodes.BUSINESS_ERROR, ex.getMessage());
    }

    /**
     * 其他错误
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public Response<Object> exception(Exception ex) {
        return ResponseBuilder.error(17, ex.getMessage());
    }
}