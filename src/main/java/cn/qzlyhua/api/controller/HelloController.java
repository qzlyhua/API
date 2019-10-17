package cn.qzlyhua.api.controller;

import cn.qzlyhua.api.annotation.response.AppException;
import cn.qzlyhua.api.annotation.response.ResponseCode;
import cn.qzlyhua.api.annotation.response.ResponseResult;
import cn.qzlyhua.api.annotation.response.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * API 调用举例
 * <p>
 * 得益于 StandardResponse 注解，所有接口均能以code、message、data形式的标准 json 格式返回
 *
 * @author Yang Hua
 */
@Controller
@StandardResponse
@Slf4j
public class HelloController {
    /**
     * 返回一个 String
     *
     * @return
     */
    @RequestMapping("/hello1")
    @ResponseBody
    public String hello1() {
        return "hello String";
    }

    /**
     * 返回一个 Object
     *
     * @return
     */
    @RequestMapping("/hello2")
    @ResponseBody
    public Object hello2() {
        return "hello Object";
    }

    /**
     * 返回一个 ResponseResult
     *
     * @return
     */
    @RequestMapping("/hello3")
    @ResponseBody
    public ResponseResult hello3() {
        log.info("返回一个成功的 ResponseResult");
        return new ResponseResult("0", "调用成功", "hello ResponseResult");
    }

    /**
     * 断言错误测试
     *
     * @return
     */
    @RequestMapping("/hello4")
    @ResponseBody
    public ResponseResult hello4() {
        log.info("通过Assert进行业务数据校验");
        Assert.isTrue(false, "this is an error!");
        return new ResponseResult("0", "调用成功", "hello error");
    }

    /**
     * 业务处理异常测试
     *
     * @return
     */
    @RequestMapping("/hello5")
    @ResponseBody
    public ResponseResult hello5() {
        log.info("模拟运行时错误");
        throw new RuntimeException("this is a RuntimeException");
    }

    /**
     * 自定义异常（预知的异常）测试
     *
     * @return
     */
    @RequestMapping("/hello6")
    @ResponseBody
    public ResponseResult hello6() {
        log.info("发生了一个已知类型的错误");
        throw new AppException(ResponseCode.RPC_ERROR);
    }
}
