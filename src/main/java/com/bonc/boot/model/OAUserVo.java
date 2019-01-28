package com.bonc.boot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Title: horinger
 * @Package: com.bonc.boot.model
 * @Description:
 * @Author: dreamcc
 * @Date: 2019/1/26 10:57
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OAUserVo {
	//用户id
	private String userGuid;
	//展示姓名
	private String displayName;
	//部门ID
	private String ouGuid;
	//部门姓名
	private String ouName;
}
