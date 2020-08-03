package com.dxx.common.bean;

import com.dxx.common.contants.ResponseCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;

/**
 * 微服务之间调用的统一返回实体类
 * @author cp5
 * @date 2020年 07月30日 11:50:39
 */
public class CommonResponse implements Serializable {

    private Log log= LogFactory.getLog(this.getClass());

    private int code;
    private String msg;
    private String body;

    private static final String TO_JSON_ERROR_MSG="CommonResponse转json失败";

    public CommonResponse(){

    }

    public CommonResponse(ResponseCode responseCode){
        this.code=responseCode.getCode();
        this.msg=responseCode.getMsg();
    }

    public CommonResponse(ResponseCode responseCode,String body){
        this.code=responseCode.getCode();
        this.msg=responseCode.getMsg();
        this.body=body;
    }

    @Override
    public String toString() {

        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            //对象转json错误，理论上不会出现~
            log.error(TO_JSON_ERROR_MSG,e);
            return TO_JSON_ERROR_MSG;
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
