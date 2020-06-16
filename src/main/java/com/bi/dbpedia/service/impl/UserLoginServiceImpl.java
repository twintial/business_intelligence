package com.bi.dbpedia.service.impl;

import com.bi.dbpedia.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String login(String username, String password, String clientId, String clientSecret) {
        //1.申请令牌
        // 获取uri
        String uri = "http://localhost:9401 ";
        String url = uri + "/oauth/token";

        // 请求提交的数据封装
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);
        params.add("grant_type", "password");

        // 请求头封装
        //String Authorization = "Basic " + new String(Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes()), "UTF-8");
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", getHttpBasic(clientId, clientSecret));

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        // (xxx.class)是response数据的封装类型, Map.class是通用的

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        Map<String, String> responseBody = response.getBody();
        // 封装到authToken中
        return responseBody.get("access_token");
    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String value = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(value.getBytes());
        return "Basic " + new String(encode, StandardCharsets.UTF_8);
    }
}