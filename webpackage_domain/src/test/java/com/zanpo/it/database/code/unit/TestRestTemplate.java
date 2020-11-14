package com.zanpo.it.database.code.unit;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加类说明
 *
 * @author cg
 * @date 2020/11/11 00:16
 */
@SuppressWarnings("unchecked")
public class TestRestTemplate {
    private static final RestTemplate rest = new RestTemplate();


    @Test
    public void testPost(){
        HashMap<Object, Object> map = new HashMap();
        HashMap paramsMap = new HashMap();
        map.put("method","hello");
        map.put("params",paramsMap);

        ResponseEntity<Map> mapResponseEntity = rest.postForEntity("http://localhost:8080/test", map, Map.class);
        System.out.println(mapResponseEntity);
    }

}
