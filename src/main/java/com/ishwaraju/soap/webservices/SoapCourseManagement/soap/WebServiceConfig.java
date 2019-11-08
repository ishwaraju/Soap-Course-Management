package com.ishwaraju.soap.webservices.SoapCourseManagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Service
//Spring Configuration
@Configuration
@EnableWs
public class WebServiceConfig {
	// Message Dispatcher Servlet
	// ApplicationContext
	// url -> /ws/*

	@Bean
	ServletRegistrationBean messageDispactherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet messageDispactherServlet = new MessageDispatcherServlet();
		messageDispactherServlet.setApplicationContext(applicationContext);
		messageDispactherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispactherServlet, "/ws/*");
	}

	// /ws/courses.wsdl

	@Bean
	XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

	@Bean(name = "courses")
	DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		// coursesPort
		// Namespace
		// course-details.xsd
		definition.setPortTypeName("CoursePort");
		definition.setTargetNamespace("http://ishwaraju.com/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		return definition;
	}

}
