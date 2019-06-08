package com.myshop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 统一的rest风格api
 *
 * @Author yang
 * @Date 2019/5/29
 */
@Setter
@Getter
public class BaseResult implements Serializable {
    public static final String OK="ok";
    public static final String NOT="not_ok";
    public static final String SUCEESS="操作成功";
    private String message;
    private String code;
    private Object data;
    private Cursor cursor;
    private List<Error> errors;

    public static BaseResult ok(){
        return createResult(OK, SUCEESS, null, null, null);
    }
    public static BaseResult ok(Object data){
        return createResult(OK, SUCEESS, data, null, null);
    }
    public static BaseResult ok(String msg){return createResult(OK,msg,null,null,null);}
    public static BaseResult ok(Object data,Cursor cursor){
        return createResult(OK, SUCEESS, data, cursor, null);
    }
    public static BaseResult notok(List<Error> errors){
        return createResult(NOT,"",null,null,errors);

    }
    public static BaseResult createResult(String code,String message,Object data,Cursor cursor,List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setData(data);
        baseResult.setErrors(errors);
        baseResult.setCursor(cursor);
        baseResult.setMessage(message);
        return baseResult;
    }
    @Data
    public static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }
    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String msg;
    }


}
