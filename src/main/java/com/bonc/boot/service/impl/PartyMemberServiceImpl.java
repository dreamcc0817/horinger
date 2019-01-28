package com.bonc.boot.service.impl;

import com.bonc.boot.mapper.PartyMemberMapper;
import com.bonc.boot.model.OAUserVo;
import com.bonc.boot.model.PartyMember;
import com.bonc.boot.model.PartyMemberVo;
import com.bonc.boot.service.IPartyMemberService;
import com.bonc.boot.util.AppReply;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.service.impl
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/26 11:18
 * @Version: V1.0
 */
@Service("partyMemberService")
public class PartyMemberServiceImpl implements IPartyMemberService {

	private PartyMemberMapper partyMemberMapper;

	@Autowired
	public PartyMemberServiceImpl(PartyMemberMapper partyMemberMapper) {
		this.partyMemberMapper = partyMemberMapper;
	}

	@Override
	public AppReply<List<OAUserVo>> getAllOAUserInfo(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<OAUserVo> oaUserVoList = partyMemberMapper.getOAUser();
		return AppReply.success(oaUserVoList);
	}

	@Override
	public AppReply<List<OAUserVo>> getOAMemberInfoByName(String memberName, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<OAUserVo> oaUserVoList = partyMemberMapper.getOAUser(memberName);
		return AppReply.success(oaUserVoList);
	}

	@Override
	public AppReply<List<PartyMemberVo>> getPartyMemberByOrg(String partyOrgCode, String displayname, String partyTypeCode, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PartyMemberVo> partyMemberList = partyMemberMapper.getPartyMemberByOrg(partyOrgCode,displayname,partyTypeCode);
		return AppReply.success(partyMemberList);
	}

	@Override
	public AppReply addPartyMember(PartyMember partyMember) {
		int result = partyMemberMapper.addPartyMember(partyMember);
		if (result >= 1) {
			return AppReply.success("添加成功");
		} else {
			return AppReply.error("添加失败");
		}
	}

	@Override
	public AppReply<PartyMemberVo> getCurrentUserInfo(String loginName) {
		PartyMemberVo partyMemberVo = partyMemberMapper.getCurrentUserInfo(loginName);
		return AppReply.success(partyMemberVo);
	}

	@Override
	public AppReply<PartyMember> updatePartyMember(PartyMember partyMember) {
		int result = partyMemberMapper.updatePartyMember(partyMember);
		if (result >= 1) {
			return AppReply.success("更新成功");
		} else {
			return AppReply.error("更新失败");
		}
	}

	@Override
	public AppReply<PartyMemberVo> getPartyMemberById(String partyMemberId) {
		PartyMemberVo partyMemberVo = partyMemberMapper.getPartyMemberById(partyMemberId);
		return AppReply.success(partyMemberVo);
	}

	@Override
	public AppReply deletePartyMember(String partyMemberId) {
		PartyMember partyMember = new PartyMember();
		partyMember.setPartyMemberId(partyMemberId);
		partyMember.setIsValid("0");
		int result = partyMemberMapper.updatePartyMember(partyMember);
		if (result >= 1) {
			return AppReply.success("删除成功");
		} else {
			return AppReply.error("删除失败");
		}
	}

	@Override
	public AppReply<List<PartyMemberVo>> searchPartyMember(String displayname, String partyTypeCode, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<PartyMemberVo> partyMemberVoList = partyMemberMapper.getPartyMemberBySearch(displayname, partyTypeCode);
		return AppReply.success(partyMemberVoList);
	}
}
