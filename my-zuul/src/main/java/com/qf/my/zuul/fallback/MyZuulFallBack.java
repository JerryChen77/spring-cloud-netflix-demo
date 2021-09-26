package com.qf.my.zuul.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author Cjl
 * @date 2021/8/18 20:35
 */
@Component
public class MyZuulFallBack implements FallbackProvider {
    /**
     * 指明对哪个服务进行熔断
     * @return
     */
    @Override
    public String getRoute() {
            return null; //对所有服务进行熔断配置
        //return "WORKER-CONSUMER";//对WORKER-CONSUMER服务进行熔断配置
    }


    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper objectMapper = new ObjectMapper();
                HashMap<String,Object> map = new HashMap<>();
                map.put("code",1000);
                map.put("message","success");
                map.put("data","Please Check Your Network");
                String jsonString = objectMapper.writeValueAsString(map);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
                return byteArrayInputStream;
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }

}
