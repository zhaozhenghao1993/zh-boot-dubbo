package net.zhenghao.zh.provider.dao;


import net.zhenghao.zh.api.entity.DubboDemo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午1:29:17
 * DubboDemoMapper.java
 */
@Component
public interface DubboDemoMapper {

	List<DubboDemo> listDemo();
}
