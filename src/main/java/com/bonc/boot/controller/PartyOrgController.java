package com.bonc.boot.controller;

import com.bonc.boot.service.IPartyOrgService;
import com.bonc.boot.util.AppReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.controller
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/25 8:56
 * @Version: V1.0
 */
@RestController
@RequestMapping("/partyOrg")
public class PartyOrgController {

	private IPartyOrgService partyOrgService;

	@Autowired
	public PartyOrgController(IPartyOrgService partyOrgService) {
		this.partyOrgService = partyOrgService;
	}

	/**
	 * 树形结构组织
	 *
	 * @return
	 */
	@GetMapping("/getChildrenParallelCategory")
	public AppReply getChildrenParallelCategory() {
		return partyOrgService.getChildrenParallelCategory();
	}

	@GetMapping("/getTreeForAct")
	public AppReply getTreeForAct() {
		return partyOrgService.getTreeForAct();
	}

}
