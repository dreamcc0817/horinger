package com.bonc.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.model
 * @Description: 党员信息
 * @Author: dreamcc
 * @Date: 2019/1/26 13:43
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartyMemberVo {

	//主键id
	private String id;
	//用户id
	private String partyMemberId;
	//党员姓名
	private String displayname;
	//党内职务id
	private String partyJobCode;
	//党内成员类型id
	private String partyTypeCode;
	//所属机构代码
	private String partyOrgCode;
	//负责其他组织
	private String authOtherOrg;
	//负责组织内职务
	private String authOtherOrgJob;
	//管理员权限
	private String authAdmin;
	//删除标志：0为删除1为可用
	private String isValid;

}
