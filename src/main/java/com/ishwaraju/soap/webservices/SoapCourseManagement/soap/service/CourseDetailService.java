package com.ishwaraju.soap.webservices.SoapCourseManagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ishwaraju.soap.webservices.SoapCourseManagement.soap.bean.Course;

@Component
public class CourseDetailService {
	public enum Status {
		SUCESS, FAILURE
	}

	private static List<Course> courses = new ArrayList<>();
	static {
		Course course1 = new Course(1, "JAVA", "This is Java Course");
		courses.add(course1);
		Course course2 = new Course(25, "Spring", "This Course is good");
		courses.add(course2);
		Course course3 = new Course(11, "Spring MVC", "MVC Course");
		courses.add(course3);
		Course course4 = new Course(88, "Rabbit MQ", "Messaging Course");
		courses.add(course4);
		Course course5 = new Course(19, "Spring Boot", "Most AdvancedCourse");
		courses.add(course5);

	}

	// course-1
	// findById
	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id)
				return course;
		}
		return null;
	}

	// List of all courses
	public List<Course> findAll() {
		return courses;
	}

	// delete a course
	public Status deleteCourse(int id) {
		Iterator<Course> iterator = courses.iterator();
		while (iterator.hasNext()) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
				return Status.SUCESS;
			}
		}
		return Status.FAILURE;
	}

	// update course and new course

}
