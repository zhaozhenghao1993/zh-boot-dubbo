package net.zhenghao.zh.consumer.dao;

import net.zhenghao.zh.consumer.entity.SysLogEntity;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午1:29:17
 * SysLogMapper.java
 */
public interface SysLogMapper extends BaseMapper<SysLogEntity>{
	
	int batchRemoveAll();
}
