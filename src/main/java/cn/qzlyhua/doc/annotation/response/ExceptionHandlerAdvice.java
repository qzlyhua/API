package cn.qzlyhua.doc.annotation.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理器
 *
 * @author Yang Hua
 */
@ControllerAdvice(annotations = StandardResponse.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {
    /**
     * 处理未捕获的 Exception
     *
     * @param e 异常
     * @return 统一响应体
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMessage(), null);
    }

    /**
     * 处理未捕获的 RuntimeException
     *
     * @param e 运行时异常
     * @return 统一响应体
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR.getCode(), ResponseCode.SERVICE_ERROR.getMessage(), null);
    }

    /**
     * 数据校验错误（Assert方法抛出的异常）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.ASSERT_ERROR.getCode(),
                StringUtils.isEmpty(e.getMessage()) ? ResponseCode.ASSERT_ERROR.getMessage() : e.getMessage(), null);
    }

    /**
     * 处理自定义的业务异常
     *
     * @param e 业务异常
     * @return 统一响应体
     */
    @ExceptionHandler(AppException.class)
    public ResponseResult handleAppException(AppException e) {
        log.error(e.getMessage(), e);
        ResponseCode code = e.getResponseCode();
        return new ResponseResult(code.getCode(), code.getMessage(), null);
    }
}
