package org.MessengerApp.resources;

import javax.ws.rs.CookieParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	private @MatrixParam("keyName") String matrixParam;
	private @HeaderParam("name1") String headerParam;
	private @CookieParam("JSESSIONID") String cookieParam;
	
	public String getMatrixParam() {
		return matrixParam;
	}
	public void setMatrixParam(String matrixParam) {
		this.matrixParam = matrixParam;
	}
	public String getHeaderParam() {
		return headerParam;
	}
	public void setHeaderParam(String headerParam) {
		this.headerParam = headerParam;
	}
	public String getCookieParam() {
		return cookieParam;
	}
	public void setCookieParam(String cookieParam) {
		this.cookieParam = cookieParam;
	}
	
	
	
}
