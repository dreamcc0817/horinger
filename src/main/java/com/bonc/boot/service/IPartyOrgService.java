package com.bonc.boot.service;

import com.bonc.boot.util.AppReply;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.service
 * @Description: 党组织结构
 * @Author: dreamcc
 * @Date: 2019/1/24 17:18
 * @Version: V1.0
 */
public interface IPartyOrgService {
	/**
	 * 获取所有组织类目信息
	 *
	 * @return
	 */
	AppReply<List<HashMap<String, Object>>> getChildrenParallelCategory();

	AppReply getTreeForAct();
}
