package com.bonc.boot.service.impl;

import com.bonc.boot.mapper.PartyOrgMapper;
import com.bonc.boot.service.IPartyOrgService;
import com.bonc.boot.util.AppReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.service.impl
 * @Description: 党组织结构
 * @Author: dreamcc
 * @Date: 2019/1/24 17:18
 * @Version: V1.0
 */
@Slf4j
@Service("partyOrgService")
public class PartyOrgServiceImpl implements IPartyOrgService {

	private PartyOrgMapper partyOrgMapper;

	@Autowired
	public PartyOrgServiceImpl(PartyOrgMapper partyOrgMapper) {
		this.partyOrgMapper = partyOrgMapper;
	}

	//递归一次性返回所有类目信息
	@Override
	public AppReply<List<HashMap<String, Object>>> getChildrenParallelCategory() {
		List<HashMap<String, Object>> list = partyOrgMapper.getChildrenParallelCategory();
		if (list.isEmpty()) {
			log.error("查询类目为空");
			return AppReply.error("查询类目为空");
		}
		TreeSet categoryPidSet = new TreeSet();
		for (int i = 0; i < list.size(); i++) {
			categoryPidSet.add(Integer.parseInt(list.get(i).get("partyPid").toString()));
		}
		return AppReply.success(this.getChildCategory(list, categoryPidSet, -1));
	}

	public List<HashMap<String, Object>> getChildCategory(List<HashMap<String, Object>> list, TreeSet categoryPidSet, Integer categoryId) {
		List<HashMap<String, Object>> returnList = new ArrayList<>();
		if (categoryPidSet.contains(categoryId)) {
			for (int i = 0; i < list.size(); i++) {
				if (Integer.parseInt(list.get(i).get("partyPid").toString()) == categoryId) {
					HashMap<String, Object> map = list.get(i);
					map.put("children", this.getChildCategory(list, categoryPidSet, Integer.parseInt(list.get(i).get("partyId").toString())));
					returnList.add(map);
				}
			}
			return returnList;
		} else {
			return returnList;
		}
	}

	@Override
	public AppReply<List<HashMap<String, Object>>> getTreeForAct() {
		List<HashMap<String, Object>> list = partyOrgMapper.getTreeForAct();
		if (list.isEmpty()) {
			log.error("查询类目为空");
			return AppReply.error("查询类目为空");
		}
		TreeSet categoryPidSet = new TreeSet();
		for (int i = 0; i < list.size(); i++) {
			categoryPidSet.add(Integer.parseInt(list.get(i).get("partyPid").toString()));
		}
		return AppReply.success(this.getChildCategory(list, categoryPidSet, -1));
	}
}
