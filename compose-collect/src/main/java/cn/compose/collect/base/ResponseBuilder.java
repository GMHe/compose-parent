package cn.compose.collect.base;


public class ResponseBuilder {
	private Object data;
	private String msg;
	
	private ResponseBuilder() {}
	
	public static ResponseBuilder create() {
		return new ResponseBuilder();
	}
	
	public ResponseBuilder data(Object data) {
		this.data = data;
		return this;
	}
	
	public ResponseBuilder msg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public Response<Object> getOK() {
		Response<Object> response = new Response<>();
		response.setCode(ApiCodes.OK);
		response.setData(data);
		response.setMsg(msg);
		return response; 
	}
	
	public Response<Object> getError(String code) {
		Response<Object> response = new Response<>();
		response.setCode(code);
		response.setData(data);
		response.setMsg(msg);
		return response; 
	}
	
	
	public static Response<Object> ok(Object data) {
		Response<Object> response = new Response<>();
		response.setCode(ApiCodes.OK);
		response.setData(data);
		return response;
	}

	public static Response<Object> ok(Object data,String msg) {
		Response<Object> response = new Response<>();
		response.setCode(ApiCodes.OK);
		response.setData(data);
		response.setMsg(msg);
		return response;
	}
	
	public static Response<Object> error(String code, String msg) {
		Response<Object> response = new Response<>();
		response.setCode(code);
		response.setMsg(msg);
		return response;
	}
	
}
