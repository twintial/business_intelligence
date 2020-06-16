package com.bi.dbpedia.controller;

import com.bi.dbpedia.common.api.CommonResult;
import com.bi.dbpedia.dto.LoginParam;
import com.bi.dbpedia.service.UserLoginService;
import com.bi.dbpedia.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public CommonResult<String> login(@RequestBody LoginParam loginParam) {
        String token = userLoginService.login(loginParam.getUsername(), loginParam.getPassword(), "admin", "admin123456");
        if (token != null) {
            return CommonResult.success(token, "login success");
        } else {
            return CommonResult.failed("user name or password incorrect");
        }
    }

    @GetMapping("/info")
    public CommonResult<String> getInfo(@RequestParam("token") String token) {
        return CommonResult.success(jwtTokenUtil.getUsernameFromToken(token), "get username success");
    }
}
