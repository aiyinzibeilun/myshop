package com.myshop.common.constants;

/**
 * http状态码常量
 *
 * @Author yang
 * @Date 2019/6/3
 */
public enum HttpStatusConstants {
    BAD_BATEWAY("502","无法从上游服务器路由");
    private String code;
    private String content;
    private HttpStatusConstants(String code,String content){
        this.code=code;
        this.content=content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
