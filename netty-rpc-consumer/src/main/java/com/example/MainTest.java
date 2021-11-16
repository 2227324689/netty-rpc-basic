package com.example;

import com.example.api.IUserService;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class MainTest {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy=new RpcClientProxy();
        IUserService userService=rpcClientProxy.clientProxy(IUserService.class,"localhost",8080);
        System.out.println(userService.saveUser("Mic"));
    }
}
