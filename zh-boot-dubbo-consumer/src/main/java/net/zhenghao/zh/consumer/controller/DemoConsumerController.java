package net.zhenghao.zh.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import net.zhenghao.zh.api.entity.DubboDemo;
import net.zhenghao.zh.api.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/20 16:20
 * DemoConsumerController.java
 */
@RestController
public class DemoConsumerController {

    @Reference(version = "${demo.service.version}",
            application = "${dubbo.application.id}",
            url = "dubbo://localhost:12345")
    private DemoService demoService;

    @RequestMapping("/sayHello")
    public String sayHello(@RequestParam String name) {
        return demoService.sayHello(name);
    }

    @RequestMapping("/listDemo")
    public List<DubboDemo> listDemo() {
        return demoService.listDemo();
    }

    @RequestMapping("/testRedis")
    public String testRedis(@RequestParam String key, @RequestParam String value) {
        return demoService.testRedis(key, value);
    }
}
