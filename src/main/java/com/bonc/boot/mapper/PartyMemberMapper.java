package com.bonc.boot.mapper;

import com.bonc.boot.model.OAUserVo;
import com.bonc.boot.model.PartyMember;
import com.bonc.boot.model.PartyMemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.mapper
 * @Description: 党员信息
 * @Author: dreamcc
 * @Date: 2019/1/26 11:07
 * @Version: V1.0
 */
@Mapper
public interface PartyMemberMapper {

	/**
	 * 新增党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	int addPartyMember(PartyMember partyMember);

	/**
	 * 获取所有OA用户信息
	 *
	 * @return
	 */
	List<OAUserVo> getOAUser();

	/**
	 * 根据用户姓名查找
	 *
	 * @param memberName
	 * @return
	 */
	List<OAUserVo> getOAUser(@Param("memberName") String memberName);

	/**
	 * 根据组织代码获取党员信息
	 *
	 * @param partyOrgCode
	 * @return
	 */
	List<PartyMemberVo> getPartyMemberByOrg(@Param("partyOrgCode") String partyOrgCode, @Param("displayname") String displayname, @Param("partyTypeCode") String partyTypeCode);

	/**
	 * 根据当前登录名获取党员信息
	 *
	 * @param loginName
	 * @return
	 */
	PartyMemberVo getCurrentUserInfo(String loginName);

	/**
	 * 更新党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	int updatePartyMember(PartyMember partyMember);

	/**
	 * 根据党员id查询详细信息
	 *
	 * @param partyMemberId
	 * @return
	 */
	PartyMemberVo getPartyMemberById(@Param("partyMemberId") String partyMemberId);

	/**
	 * 通过搜索条件查询党员信息
	 *
	 * @param displayname
	 * @param partyTypeCode
	 * @return
	 */
	List<PartyMemberVo> getPartyMemberBySearch(@Param("displayname") String displayname, @Param("partyTypeCode") String partyTypeCode);
}
