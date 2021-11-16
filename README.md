# 设计思想

基于Dubbo+Zookeeper的设计理念，实现一个简单版本的RPC框架

![NettyRpc-design](https://user-images.githubusercontent.com/52684867/142019353-23ac808d-ff82-4820-b6db-2fb16cff6989.png)


# 使用技术


基于Netty4.x版本实现RPC框架，主要包含的功能点

* RPC通信
* 负载均衡
* 反射
* Spring 扩展

本案例为基础版本，仅基于Netty实现了远程方法调用，进阶版本中提供了注解驱动、服务注册与发现等丰富的功能，详见：[Netty进阶版本](https://github.com/2227324689/netty-rpc).

# 为什么要写?

平时我们在使用Dubbo或者一些其他的RPC框架时，并没有关心RPC框架底层的实现，仅仅停留在使用层面。
但是作为一个Java高级开发，必须要对底层实现有一定的了解，所以基于Netty实现了一个自己的RPC框架。
希望能够帮助大家更好的理解Netty以及RPC的原理。

# 项目结构说明

> 下面这两个项目，表示服务提供者，用来演示服务提供者和服务消费者通信的一个演示案例。

1. netty-rpc-api  ， 对外提供的公共契约

2. netty-rpc-provider ， 服务提供者接口的实现

3. netty-rpc-consumer， 服务消费者

provider发布服务，consumer通过自定义rpc协议实现和provider的通信。

> 下面这个项目模块是RPC的实现，netty-rpc-provider和netty-rpc-consumer分别依赖它来实现远程通信

netty-rpc-protocol ， Netty实现RPC框架的核心库


# 使用方法

## 发布远程服务

这个版本的实现比较原始，是通过`new NettyServer`手动发布一个Netty远程服务。

```java
@ComponentScan(basePackages = {"com.example.spring","com.example.service"})
@SpringBootApplication
public class NettyRpcProviderMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NettyRpcProviderMain.class, args);
        new NettyServer("127.0.0.1",8080).startNettyServer();
    }

}
```
## 消费远程服务

服务消费，是基于`RpcClientProxy`实现的动态代理，完成远程通信机制。

```java
public class MainTest {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy=new RpcClientProxy();
        IUserService userService=rpcClientProxy.clientProxy(IUserService.class,"localhost",8080);
        System.out.println(userService.saveUser("Mic"));
    }
}


```

# 实现教程


# 联系方式

* 微信： mic4096

* 个人博客： https://istio.tech

* 微信公众号：

<img src="https://user-images.githubusercontent.com/52684867/142021673-5de16ad4-50f4-479b-8905-08f436cccebd.jpg" style="width:200px"/>


# 个人作品

1. 2020年出版[《Spring Cloud Alibaba微服务原理与实战》](https://item.jd.com/12848452.html)

![](https://mic-blob-bucket.oss-cn-beijing.aliyuncs.com/c11d945cd9351817.jpg)

2. 2021年出版[《Java并发编程深度解析与实战》](https://item.jd.com/12971665.html)

![](https://mic-blob-bucket.oss-cn-beijing.aliyuncs.com/5c9303318a52c860.jpg)
