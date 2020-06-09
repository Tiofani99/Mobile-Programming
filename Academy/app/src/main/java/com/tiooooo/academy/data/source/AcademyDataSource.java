package com.tiooooo.academy.data.source;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public interface AcademyDataSource {

    LiveData<List<CourseEntity>> getAllCourses();

    LiveData<CourseEntity> getCourseWithModules(String courseID);

    LiveData<ArrayList<ModuleEntity>> getAllModulesByCourse(String courseId);

    LiveData<List<CourseEntity>> getBookmarkedCourses();

    LiveData<ModuleEntity> getContent(String courseId, String moduleId);

}
