package com.bi.dbpedia.service;


public interface UserLoginService {

    String login(String username, String password, String clientId, String clientSecret);
}
