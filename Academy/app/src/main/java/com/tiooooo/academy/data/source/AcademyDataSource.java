package com.tiooooo.academy.data.source;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;

import java.util.List;

public interface AcademyDataSource {

    List<CourseEntity> getAllCourses();

    CourseEntity getCourseWithModules(String courseID);

    List<ModuleEntity> getAllModulesByCourse(String courseId);

    List<CourseEntity> getBookmarkedCourses();

    ModuleEntity getContent(String courseId, String moduleId);

}
