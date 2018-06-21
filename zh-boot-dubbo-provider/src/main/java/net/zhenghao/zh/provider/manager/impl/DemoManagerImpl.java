package net.zhenghao.zh.provider.manager.impl;

import net.zhenghao.zh.api.entity.DubboDemo;
import net.zhenghao.zh.provider.dao.DubboDemoMapper;
import net.zhenghao.zh.provider.manager.DemoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 🙃
 * 🙃
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018/6/21 17:42
 * DemoManagerImpl.java
 */
@Component
public class DemoManagerImpl implements DemoManager {

    @Autowired
    private DubboDemoMapper dubboDemoMapper;

    @Override
    public List<DubboDemo> listDemo() {
        return dubboDemoMapper.listDemo();
    }
}
