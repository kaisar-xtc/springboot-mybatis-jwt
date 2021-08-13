package com.hsrd.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hsrd.entity.User;
import com.hsrd.service.UserService;
import com.hsrd.util.JWTUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController{
    @Autowired
    private UserService userService;
    @PostMapping("/user/login")
    public Map<String,Object> login(User user){
        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String,String> payload = new HashMap<>();
            payload.put("id",userDB.getId());
            payload.put("username",userDB.getUsername());
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","登陆成功");
            map.put("token",token);
        }catch (Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }
    @GetMapping("/user/logout")
    public Map<String,Object> logout(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if(token!=null){
            token = "";
            map.put("state",false);
            map.put("msg","退出登录");
            map.put("token",token);
        }
        return map;
    }

    @GetMapping("/user/test")
    public Map<String,Object> test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //处理业务逻
        String token = request.getHeader("token");
        DecodedJWT verity = JWTUtils.verityToken(token);
        System.out.println(verity.getClaim("id").asString());
        System.out.println(verity.getClaim("username").asString());
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }
}
