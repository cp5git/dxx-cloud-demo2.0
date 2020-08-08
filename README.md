# dxx-cloud-demo

## 一、模块说明

### 1.register-server
#### 模块名称：注册中心
#### 实现方式：eureka
#### 模块说明: 提供服务注册和发现功能

### 2.zuul
#### 模块名称：网关
#### 实现方式：zuul、eureka
#### 模块说明: 将微服务接口暴露给外部应用，起到统一对外服务端口的作用，同时屏蔽一些敏感接口的访问;记录jwt和skywalking traceId，可作为服务追踪入口

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
#### 模块说明: 引入auth-client，可用于测试鉴权功能

### 8.consumer
#### 模块名称：消费者服务
#### 实现方式： fegin、eureka、hystrix
#### 模块说明: 调用producer1，用于测试restful api、负载均衡、熔断

### 9.gateway
#### 模块名称：另一个网关
#### 实现方式：gateway、eureka
#### 模块说明: 使用springcloud gateway实现的网关，作用同zuul


## 二、鉴权功能演示

### 1.mysql中执行rbac.sql脚本

### 2.启动注册中心 register-server

### 3.启动以下服务
#### zuul(或者gateway)、auth-server、rbac、producer1

### 4.使用dxx-cloud-front访问(见上级目录)


## 三、feign负载均衡、熔断演示

### 1.启动注册中心 register-server

### 2.启动以下服务
#### zuul(或者gateway)、auth-server、consumer、producer1、producer2

### 3.浏览器轮询访问http://127.0.0.1:8888/consumer/call/producer


## 四、skywalking服务追踪演示

### 1.docker安装elasticsearch
#### docker pull docker.elastic.co/elasticsearch/elasticsearch:7.1.1
#### docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:7.1.1

### 2.安装skywalking服务端
#### 安装环境：linux
#### 访问http://skywalking.apache.org/downloads/ 下载tar包,此项目使用的是“Binary Distribution for ElasticSearch 7”版本
#### 解压tar包，编辑apache-skywalking-apm-bin-es7/config/application.yml文件，找到
##### storage:
#####    selector: ${SW_STORAGE:}
#### 修改为
##### storage:
#####    selector: ${SW_STORAGE:elasticsearch7}
#### 进入apache-skywalking-apm-bin-es7/bin目录，启动skywalking
##### ./startup.sh

### 3.拷贝apache-skywalking-apm-bin-es7/agent到本机项目根目录(如D:\workspace_git\dxx-cloud-demo\)

### 4.在IDEA打开Edit Configurations,在zuul、auth-server、rbac、producer1模块上添加vm options
#### -javaagent:D:\workspace_git\dxx-cloud-demo\agent\skywalking-agent.jar
#### -Dskywalking.agent.service_name=模块名称
#### -Dskywalking.collector.backend_service=skywalking服务IP:11800

### 5.启动register-server、zuul、auth-server、rbac、producer1

### 6.访问skywalking
#### http://skywalking服务IP:8080/