package com.ishwaraju.soap.webservices.SoapCourseManagement.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM, customFaultCode = "{http://ishwaraju.com/courses}001_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1276357730998355726L;

	public CourseNotFoundException(String message) {
		super(message);
	}
}
