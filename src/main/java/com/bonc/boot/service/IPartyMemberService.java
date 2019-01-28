package com.bonc.boot.service;

import com.bonc.boot.model.OAUserVo;
import com.bonc.boot.model.PartyMember;
import com.bonc.boot.model.PartyMemberVo;
import com.bonc.boot.util.AppReply;

import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.service
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/26 11:17
 * @Version: V1.0
 */
public interface IPartyMemberService {
	/**
	 * 获取所有oa用户信息
	 *
	 * @return
	 */
	AppReply<List<OAUserVo>> getAllOAUserInfo(Integer pageNum, Integer pageSize);

	/**
	 * 根据姓名查找党员信息
	 *
	 * @param memberName
	 * @return
	 */
	AppReply<List<OAUserVo>> getOAMemberInfoByName(String memberName, Integer pageNum, Integer pageSize);

	/**
	 * 根据组织结构代码获取党员信息
	 *
	 * @param partyOrgCode
	 * @return
	 */
	AppReply<List<PartyMemberVo>> getPartyMemberByOrg(String partyOrgCode, String displayname, String partyTypeCode, Integer pageNum, Integer pageSize);

	/**
	 * 新增党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	AppReply addPartyMember(PartyMember partyMember);

	/**
	 * 根据登录名获取当前用户信息
	 *
	 * @param loginName
	 * @return
	 */
	AppReply<PartyMemberVo> getCurrentUserInfo(String loginName);

	/**
	 * 更新党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	AppReply<PartyMember> updatePartyMember(PartyMember partyMember);

	/**
	 * 根据党员id查看详细信息
	 *
	 * @param partyMemberId
	 * @return
	 */
	AppReply<PartyMemberVo> getPartyMemberById(String partyMemberId);

	/**
	 * 根据党员id删除记录
	 *
	 * @param partyMemberId
	 * @return
	 */
	AppReply deletePartyMember(String partyMemberId);

	/**
	 * 根据党员名称党员类型查询党员信息
	 *
	 * @param displayname
	 * @param partyTypeCode
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	AppReply<List<PartyMemberVo>> searchPartyMember(String displayname, String partyTypeCode, Integer pageNum, Integer pageSize);
}
