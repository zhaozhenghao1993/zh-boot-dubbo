package net.zhenghao.zh.consumer.manager.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.zhenghao.zh.consumer.dao.SysLogMapper;
import net.zhenghao.zh.common.entity.Page;
import net.zhenghao.zh.common.entity.Query;
import net.zhenghao.zh.consumer.entity.SysLogEntity;
import net.zhenghao.zh.consumer.manager.SysLogManager;

/**
 * 系统日志
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午2:27:24
 * SysLogManagerImpl.java
 */
@Component("SysLogManager")
public class SysLogManagerImpl implements SysLogManager {
	
	@Autowired
	private SysLogMapper sysLogMapper;

	@Override
	public void saveLog(SysLogEntity log) {
		sysLogMapper.save(log);
	}

	@Override
	public List<SysLogEntity> listLog(Page<SysLogEntity> page, Query query) {
		return sysLogMapper.listForPage(page, query);
	}

	@Override
	public List<SysLogEntity> listAll() {
		PageHelper.startPage(1, 10);
		return sysLogMapper.list();
	}

	@Override
	public int batchRemove(Long[] id) {
		return sysLogMapper.batchRemove(id);
	}

	@Override
	public int batchRemoveAll() {
		return sysLogMapper.batchRemoveAll();
	}

}
