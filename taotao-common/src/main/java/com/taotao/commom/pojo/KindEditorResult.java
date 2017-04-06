package com.taotao.commom.pojo;

public class KindEditorResult {
	private int error;
	
	private String url;
	
	private String message;

	public KindEditorResult(int error, String url, String message) {
		super();
		this.error = error;
		this.url = url;
		this.message = message;
	}
	
	//成功
	public static KindEditorResult ok(String url){
		return new KindEditorResult(0, url, null);
	}
	
	//失败
	public static KindEditorResult error(){
		return new KindEditorResult(1, null, "图片上传失败");
	}
	
	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
