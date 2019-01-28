package com.bonc.boot.controller;

import cn.hutool.core.util.IdUtil;
import com.bonc.boot.model.OAUserVo;
import com.bonc.boot.model.PartyMember;
import com.bonc.boot.model.PartyMemberVo;
import com.bonc.boot.service.IPartyMemberService;
import com.bonc.boot.util.AppReply;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/26 11:15
 * @Version: V1.0
 */
@RestController
@RequestMapping("/partyMember")
public class PartyMemberController {

	private IPartyMemberService partyMemberService;

	@Autowired
	public PartyMemberController(IPartyMemberService partyMemberService) {
		this.partyMemberService = partyMemberService;
	}

	/**
	 * 根据姓名获取党员信息
	 *
	 * @param memberName
	 * @return
	 */
	@GetMapping("/getMemberInfo")
	public AppReply<List<OAUserVo>> getOAMemberInfoByName(@RequestParam(value = "memberName") String memberName,
	                                                      @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
	                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		if (StringUtils.isEmpty(memberName)) {
			return partyMemberService.getAllOAUserInfo(pageNum, pageSize);
		}
		return partyMemberService.getOAMemberInfoByName(memberName, pageNum, pageSize);
	}

	/**
	 * 根据组织结构代码获取党员信息
	 *
	 * @param partyOrgCode
	 * @return
	 */
	@GetMapping("/getPartyMemberByOrg/{partyOrgCode}")
	public AppReply<List<PartyMemberVo>> getPartyMemberByOrg(@PathVariable String partyOrgCode,
	                                                         @RequestParam(required = false) String displayname, @RequestParam(required = false) String partyTypeCode,
	                                                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
	                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		return partyMemberService.getPartyMemberByOrg(partyOrgCode,displayname,partyTypeCode,pageNum, pageSize);
	}

	/**
	 * 根据党员id查看详细信息
	 *
	 * @param partyMemberId
	 * @return
	 */
	@GetMapping("/getPartyMemberById/{partyMemberId}")
	public AppReply<PartyMemberVo> getPartyMemberById(@PathVariable String partyMemberId) {
		return partyMemberService.getPartyMemberById(partyMemberId);
	}

	/**
	 * 搜索党员信息
	 *
	 * @param displayname
	 * @param partyTypeCode
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/searchPartyMember")
	public AppReply<List<PartyMemberVo>> searchPartyMember(@RequestParam(required = false) String displayname, @RequestParam(required = false) String partyTypeCode,
	                                                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
	                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
		return partyMemberService.searchPartyMember(displayname, partyTypeCode, pageNum, pageSize);
	}

	/**
	 * 新增党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	@PostMapping("/addPartyMember")
	public AppReply addPartyMember(PartyMember partyMember) {
		String id = IdUtil.simpleUUID();
		partyMember.setId(id);
		return partyMemberService.addPartyMember(partyMember);
	}

	/**
	 * 根据OA登录名获取党员信息
	 *
	 * @param loginName
	 * @return
	 */
	@GetMapping("/getCurrentUserInfo/{loginName}")
	public AppReply<PartyMemberVo> getCurrentUserInfo(@PathVariable String loginName, HttpSession session) {
		session.setMaxInactiveInterval(3000000);
		session.setAttribute("loginUser",loginName);
		return partyMemberService.getCurrentUserInfo(loginName);
	}

	/**
	 * 更新党员信息
	 *
	 * @param partyMember
	 * @return
	 */
	@PutMapping("/updatePartyMember")
	public AppReply updatePartyMember(PartyMember partyMember) {
		return partyMemberService.updatePartyMember(partyMember);
	}

	/**
	 * 删除党员信息
	 *
	 * @param partyMemberId
	 * @return
	 */
	@DeleteMapping("/deletePartyMemberById/{partyMemberId}")
	public AppReply deletePartyMember(@PathVariable String partyMemberId) {
		return partyMemberService.deletePartyMember(partyMemberId);
	}
}
