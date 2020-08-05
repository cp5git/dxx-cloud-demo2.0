# dxx-cloud-demo

## 一、启动顺序

### 1.mysql中执行rbac.sql脚本

### 2.启动注册中心 register-server

### 3.启动以下服务
#### gateway、auth-server、rbac、producer1

### 4.使用dxx-cloud-front访问(见上级目录)

## 二、模块说明

### 1.register-server
#### 模块名称：注册中心
#### 实现方式：eureka
#### 模块说明: 提供服务注册和发现功能

### 2.zuul
#### 模块名称：网关
#### 实现方式：zuul、eureka
#### 模块说明: 将微服务接口暴露给外部应用，起到统一对外服务端口的作用，同时屏蔽一些敏感接口的访问

### 3.common
#### 模块名称：通用模块
#### 模块说明: 封装各微服务之间公用的消息实体类、通用的工具类，通过引用的方式嵌入各个微服务模块

### 4.auth-client
#### 模块名称：鉴权客户端
#### 实现方式：fegin、eureka
#### 模块说明: 为业务服务模块提供鉴权拦截功能，与鉴权中心交互，对业务透明，通过引用的方式嵌入各个微服务模块

### 5.auth-server
#### 模块名称：鉴权中心
#### 实现方式：fegin、eureka
#### 模块说明: 提供登录功能，提供token生成、刷新、校验功能，提供鉴权服务功能，登录信息、权限控制信息通过调用rbac服务获取

### 6.rbac
#### 模块名称：用户角色资源管理服务
#### 实现方式：eureka、mybatis、mysql
#### 模块说明: 提供基于用户角色的资源管理功能，数据初始化脚本参见rbac.sql

### 7.producer1
#### 模块名称：生产者服务
#### 实现方式： fegin、eureka
#### 模块说明: 引入auth-client，用于测试鉴权功能

### 8.consumer
#### 模块名称：消费者服务
#### 实现方式： fegin、eureka、hystrix
#### 模块说明: 用于测试restful api、负载均衡、熔断

### 9.gateway
#### 模块名称：另一个网关
#### 实现方式：gateway、eureka
#### 模块说明: 使用springcloud gateway实现的网关，作用同zuul
