package com.app.golmania.service;

public class GoogleServicesException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6639431768187354281L;
	private int errorCode;
	private String errorMessage;
	public GoogleServicesException(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return " [errorCode=" + errorCode
				+ ", errorMessage=" + errorMessage + "]";
	}


}
