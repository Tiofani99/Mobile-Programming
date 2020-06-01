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
import androidx.lifecycle.MutableLiveData;

public class FakeAcademyRepository implements AcademyDataSource {

    private volatile static FakeAcademyRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

     FakeAcademyRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<List<CourseEntity>> getAllCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();
        remoteDataSource.getAllCourses(courseResponses ->{
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
            courseResults.postValue(courseList);
        } );


        return courseResults;
    }

    @Override
    public LiveData<List<CourseEntity>> getBookmarkedCourses() {
        MutableLiveData<List<CourseEntity>> courseResults = new MutableLiveData<>();
        remoteDataSource.getAllCourses(courseResponses -> {
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
            courseResults.postValue(courseList);
        });
        return courseResults;
    }

    @Override
    public LiveData<CourseEntity> getCourseWithModules(final String courseId) {
        MutableLiveData<CourseEntity> courseResult = new MutableLiveData<>();
        remoteDataSource.getAllCourses(courseResponses -> {
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
            courseResult.postValue(course);
        });
        return courseResult;
    }

    @Override
    public LiveData<ArrayList<ModuleEntity>> getAllModulesByCourse(String courseId) {
        MutableLiveData<ArrayList<ModuleEntity>> moduleResults = new MutableLiveData<>();

        remoteDataSource.getModules(courseId, moduleResponses -> {
            ArrayList<ModuleEntity> moduleList = new ArrayList<>();
            for (ModuleResponse response : moduleResponses) {
                ModuleEntity course = new ModuleEntity(response.getModuleId(),
                        response.getCourseId(),
                        response.getTitle(),
                        response.getPosition(),
                        false);
                moduleList.add(course);
            }
            moduleResults.postValue(moduleList);
        });

        return moduleResults;
    }



    @Override
    public LiveData<ModuleEntity> getContent(String courseId, String moduleId) {
        MutableLiveData<ModuleEntity> moduleResult = new MutableLiveData<>();

        remoteDataSource.getModules(courseId,moduleResponses -> {
            ModuleEntity module ;
            for (ModuleResponse response : moduleResponses) {
                if (response.getModuleId().equals(moduleId)) {
                    module = new ModuleEntity(response.getModuleId(),
                            response.getCourseId(),
                            response.getTitle(),
                            response.getPosition(),
                            false);
                    remoteDataSource.getContent(moduleId,contentResponse -> {
                        module.contentEntity = new ContentEntity(contentResponse.getContent());
                        moduleResult.postValue(module);
                    });
                    break;
                }
            }
        });

        return moduleResult;
    }
}