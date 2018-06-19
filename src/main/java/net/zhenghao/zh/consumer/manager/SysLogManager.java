package net.zhenghao.zh.consumer.manager;

import java.util.List;

import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.consumer.entity.SysLogEntity;

/**
 * 系统日志(供service层调取)
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午2:24:05
 * SysLogManager.java
 */
public interface SysLogManager {
	
	void saveLog(SysLogEntity log);
	
	List<SysLogEntity> listLog(Page<SysLogEntity> page, Query query);

	List<SysLogEntity> listAll();
	
	int batchRemove(Long[] id);
	
	int batchRemoveAll();
}
