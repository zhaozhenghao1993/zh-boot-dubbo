package net.zhenghao.zh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.zhenghao.zh.consumer.dao")
public class ZHDubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZHDubboApplication.class, args);
    }
}
