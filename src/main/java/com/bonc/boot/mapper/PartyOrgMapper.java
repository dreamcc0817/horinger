package com.bonc.boot.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.mapper
 * @Description: 党组织结构
 * @Author: dreamcc
 * @Date: 2019/1/24 16:56
 * @Version: V1.0
 */
@Mapper
public interface PartyOrgMapper{
	/**
	 * 获取组织结构
	 * @return
	 */
	List<HashMap<String, Object>> getChildrenParallelCategory();

	List<HashMap<String, Object>> getTreeForAct();
}
