package com.ishwaraju.soap.webservices.SoapCourseManagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ishwaraju.courses.CourseDetails;
import com.ishwaraju.courses.DeleteCourseDetailsRequest;
import com.ishwaraju.courses.DeleteCourseDetailsResponse;
import com.ishwaraju.courses.GetAllCourseDetailsRequest;
import com.ishwaraju.courses.GetAllCourseDetailsResponse;
import com.ishwaraju.courses.GetCourseDetailsRequest;
import com.ishwaraju.courses.GetCourseDetailsResponse;
import com.ishwaraju.soap.webservices.SoapCourseManagement.soap.bean.Course;
import com.ishwaraju.soap.webservices.SoapCourseManagement.soap.exception.CourseNotFoundException;
import com.ishwaraju.soap.webservices.SoapCourseManagement.soap.service.CourseDetailService;
import com.ishwaraju.soap.webservices.SoapCourseManagement.soap.service.CourseDetailService.Status;

@Endpoint
public class CourseDetailsEndPoint {

	@Autowired
	CourseDetailService service;
	// method
	// input : request GetCourseDetailsRequest
	// output : response GetCourseDetailsResponse

	@PayloadRoot(namespace = "http://ishwaraju.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
		Course course = service.findById(request.getId());
		if (course == null)
			throw new CourseNotFoundException("Invalid Course id: " + request.getId());
		return mapCourseDetails(course);
	}

	@PayloadRoot(namespace = "http://ishwaraju.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {
		List<Course> courses = service.findAll();
		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://ishwaraju.com/courses", localPart = "DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
		Status status = service.deleteCourse(request.getId());
		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));
		return response;
	}

	private com.ishwaraju.courses.Status mapStatus(Status status) {
		if (status == Status.SUCESS)
			return com.ishwaraju.courses.Status.SUCESS;
		else
			return com.ishwaraju.courses.Status.FAILURE;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course));
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails courseDetails = mapCourse(course);
			response.getCourseDetails().add(courseDetails);
		}
		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setDescription(course.getDescription());
		courseDetails.setName(course.getName());
		return courseDetails;
	}

}
