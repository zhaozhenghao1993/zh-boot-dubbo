package net.zhenghao.zh.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.zhenghao.zh.provider.dao")
public class ZhDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhDubboProviderApplication.class, args);
    }
}
