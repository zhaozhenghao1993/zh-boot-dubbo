# zh-boot-dubbo
springboot搭建dubbo，PageHelper分页，快速开发提供api

springboot 版本 2.0.3.RELEASE

dubbo-spring-boot 版本 0.2.0

dubbo 版本 2.6.2

jdk 版本 1.8

数据库 mysql

持久层 mybatis

注：dubbo-spring-boot以推出0.2.0  支持springboot 2.0.x版本


# 项目介绍

**zh-boot-dubbo-api**

    提供service接口 entity实体类供provider和consumer调用
    
**zh-boot-dubbo-common**

    公共工具类，公共常量

**zh-boot-dubbo-consumer**

    dubbo服务消费者模块
    
**zh-boot-dubbo-provider**
    
    dubbo服务提供者模块

**项目打包**

cmd进入zh-boot-dubbo路径

`mvn -pl zh-boot-dubbo-provider -am install`

`mvn -pl zh-boot-dubbo-consumer -am install`

打包后在项目的target目录下

**项目启动**

`java -jar provider.jar`

`java -jar consumer.jar`