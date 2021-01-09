package spring.course.controller;

public class RestResponse<T> {

	private String returnCode;
	private String errorMessage;
	private T t;

	public RestResponse(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public RestResponse(String returnCode, String errorMessage) {
		this.errorMessage = errorMessage;
		this.returnCode = returnCode;
	}

	public RestResponse(String returnCode, T t) {
		this.t = t;
		this.returnCode = returnCode;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "RestResponse [returnCode=" + returnCode + ", errorMessage=" + errorMessage + ", t=" + t + "]";
	}

}
