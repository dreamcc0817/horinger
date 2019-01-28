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
 * @Date: 2019/1/24 17:02
 * @Version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PartyOrg {
	private int partyId;
	private String partyCode;
	private String partyName;
	private int partyPid;
	private String partyType;
	private String creator;
	private Date creatrTime;
	private String updator;
	private Date updateTime;
	private int valid;
}
