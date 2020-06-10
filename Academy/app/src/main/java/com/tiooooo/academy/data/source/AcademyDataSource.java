package com.tiooooo.academy.data.source;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.local.entity.CourseWithModule;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.vo.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;

public interface AcademyDataSource {

    LiveData<Resource<List<CourseEntity>>> getAllCourses();

    LiveData<Resource<CourseWithModule>> getCourseWithModules(String courseId);

    LiveData<Resource<List<ModuleEntity>>> getAllModulesByCourse(String courseId);

    LiveData<Resource<ModuleEntity>> getContent(String moduleId);

    LiveData<List<CourseEntity>> getBookmarkedCourses();

    void setCourseBookmark(CourseEntity course, boolean state);

    void setReadModule(ModuleEntity module);

}
