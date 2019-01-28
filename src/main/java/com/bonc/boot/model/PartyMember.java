package com.bonc.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.model
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/26 11:09
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartyMember {
	//主键id
	private String id;
	//用户id
	private String partyMemberId;
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
	//创建时间
	private Date createTime;
	//创建人员
	private String createBy;
	//更新时间
	private Date updateTime;
	//更新人员
	private String updateBy;
}
