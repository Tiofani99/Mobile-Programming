package com.tiooooo.academy.data.source;

import com.tiooooo.academy.data.ContentEntity;
import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.data.source.remote.RemoteDataSource;
import com.tiooooo.academy.data.source.remote.response.CourseResponse;
import com.tiooooo.academy.data.source.remote.response.ModuleResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class FakeAcademyRepository implements AcademyDataSource {

    private volatile static FakeAcademyRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

     FakeAcademyRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }


    @Override
    public ArrayList<CourseEntity> getAllCourses() {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        ArrayList<CourseEntity> courseList = new ArrayList<>();
        for (CourseResponse response : courseResponses) {
            CourseEntity course = new CourseEntity(response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        ArrayList<CourseEntity> courseList = new ArrayList<>();
        for (CourseResponse response : courseResponses) {
            CourseEntity course = new CourseEntity(response.getId(),
                    response.getTitle(),
                    response.getDescription(),
                    response.getDate(),
                    false,
                    response.getImagePath());
            courseList.add(course);
        }
        return courseList;
    }

    @Override
    public LiveData<CourseEntity> getCourseWithModules(final String courseId) {
        List<CourseResponse> courseResponses = remoteDataSource.getAllCourses();
        CourseEntity course = null;
        for (CourseResponse response : courseResponses) {
            if (response.getId().equals(courseId)) {
                course = new CourseEntity(response.getId(),
                        response.getTitle(),
                        response.getDescription(),
                        response.getDate(),
                        false,
                        response.getImagePath());
            }
        }
        return course;
    }

    @Override
    public LiveData<ArrayList<ModuleEntity>> getAllModulesByCourse(String courseId) {
        List<ModuleResponse> moduleResponses = remoteDataSource.getModules(courseId);
        ArrayList<ModuleEntity> moduleList = new ArrayList<>();
        for (ModuleResponse response : moduleResponses) {
            ModuleEntity course = new ModuleEntity(response.getModuleId(),
                    response.getCourseId(),
                    response.getTitle(),
                    response.getPosition(),
                    false);
            moduleList.add(course);
        }
        return moduleList;
    }


    @Override
    public ModuleEntity getContent(String courseId, String moduleId) {
        ModuleEntity module = null;
        List<ModuleResponse> moduleResponses = remoteDataSource.getModules(courseId);
        for (ModuleResponse response : moduleResponses) {
            if (response.getModuleId().equals(moduleId)) {
                module = new ModuleEntity(response.getModuleId(),
                        response.getCourseId(),
                        response.getTitle(),
                        response.getPosition(),
                        false);
                module.contentEntity = new ContentEntity(remoteDataSource.getContent(moduleId).getContent());
                break;
            }
        }
        return module;
    }
}