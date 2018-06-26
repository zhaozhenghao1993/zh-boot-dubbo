package net.zhenghao.zh.api.service;

import net.zhenghao.zh.api.entity.DubboDemo;

import java.util.List;

/**
 * 🙃
 * 🙃 api提供接口
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/20 16:15
 * DemoService.java
 */
public interface DemoService {
    String sayHello(String name);

    List<DubboDemo> listDemo();

    String testRedis(String key, String value);
}
