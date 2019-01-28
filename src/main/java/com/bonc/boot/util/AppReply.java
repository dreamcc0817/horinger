package com.bonc.boot.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * app接口返回数据实体
 * @author shy
 * @date   18210175120
 * @param <T>
 */
public class AppReply<T> {

    
	public static final String EORRO_CODE ="0";              //请求失败
	public static final String SUCCESS_CODE ="1";            //请求成功
	public static final String LOGIN_CODE ="2";            //请求成功
	public static final String MULTILOGIN_CODE ="3";            //请求成功
	public static final String TYPE_ERROR_CODE ="100";       //类型错误
	public static final String LACK_PARAMENT_CODE ="103";    //缺少参数
	public static final String SERVICE_ERROR_CODE ="200";    //服务器错误
	public static final String SERVICE_DISABLED_CODE ="201"; //服务器不可用
	public static final String SERVICE_RESET_CODE ="202";    //服务器正在重启
	public static final String SUCCESS_MSG = "成功"; 			// 请求成功
	public static final String ERROR_MSG = "失败"; 				// 请求失败
	public static final String RE_LOGIN_MSG = "失败"; 				// 请求失败
	private String code = SUCCESS_CODE;  //状态编号

	private Object data;   //单条对象
	
//	private List<T> data; //数组对象
	
	private String msg;  //附加消息
	
//	private String url;  //附加地址
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}
	public AppReply() {

	}
	public AppReply(String retCode, String retMsg, Object data) {
		super();
		this.code = retCode;
		this.msg = retMsg;
		setData(data);
	}
	public void setData(Object data) {
//		if( data instanceof PageInfo) {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("total", ((PageInfo) data).getTotal());
//			map.put("rows", ((PageInfo) data).get());
//			HttpServletRequest request = HttpKit.getRequest();
//			map.put("pageNumber", request.getParameter("pageNumber"));
//			map.put("pageSize", request.getParameter("pageSize"));
//			this.data = map;
//		} else {
			this.data = data;
//		}
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

//	public String getUrl() {
//		return url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
	
    @Override
	public String toString() {

    	 String datePattern = "yyyy-MM-dd HH-mm-ss";

    	 String json= JSONObject.toJSONStringWithDateFormat(this,datePattern, SerializerFeature.WriteMapNullValue);

    	 json=json.replaceAll("null", "\"\"");

		return json;
	}

	public static AppReply success(Object obj){
		return new AppReply(AppReply.SUCCESS_CODE, AppReply.SUCCESS_MSG,obj);
	}

	public static AppReply success(String msg){
		return new AppReply(AppReply.SUCCESS_CODE, msg,null);
	}

	public static AppReply success(){
		return success(AppReply.SUCCESS_MSG);
	}

	public static AppReply error(Object obj){
		return new AppReply(AppReply.EORRO_CODE, AppReply.ERROR_MSG,obj);
	}



	public static AppReply error(String msg){
		return new AppReply(AppReply.EORRO_CODE, msg,null);
	}

	public static AppReply error(String msg,String code){
		return new AppReply(code, msg,null);
	}

	public static AppReply error(Exception businessException){
		return new AppReply(AppReply.EORRO_CODE,"500",null);
	}


	public static AppReply relogin(){
		return new AppReply(AppReply.LOGIN_CODE, "请重新登录",null);
	}
	
	public static AppReply relogin_2(){
		return new AppReply(AppReply.MULTILOGIN_CODE, "please login",null);
	}



}
