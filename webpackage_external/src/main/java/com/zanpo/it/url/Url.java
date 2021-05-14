package com.zanpo.it.url;

import lombok.Getter;
import org.springframework.http.HttpMethod;

/**
 * 外部接口访问地址定义
 *
 * @author cg
 * @date 2021/5/12 10:06
 */
@Getter
public enum Url {
    JD_3("https://coin.jd.com/m/gb/getBaseInfo.html", HttpMethod.GET);


    private String url;
    private HttpMethod method;

    private Url(String url, HttpMethod method) {
        this.url = url;
        this.method = method;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
