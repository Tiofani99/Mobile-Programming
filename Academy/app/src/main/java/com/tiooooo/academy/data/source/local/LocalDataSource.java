package com.tiooooo.academy.data.source.local;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.local.entity.CourseWithModule;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.data.source.local.room.AcademyDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final AcademyDao mAcademyDao;

    private  LocalDataSource(AcademyDao mAcademyDao) {
        this.mAcademyDao = mAcademyDao;
    }

    public static LocalDataSource getInstance(AcademyDao academyDao){
        if(INSTANCE == null){
            INSTANCE = new LocalDataSource(academyDao);
        }

        return INSTANCE;
    }

    public LiveData<List<CourseEntity>> getAllCourses(){
        return mAcademyDao.getCourses();
    }

    public LiveData<List<CourseEntity>> getBookmarkedCourses(){
        return mAcademyDao.getBookmarkedCourse();
    }

    public LiveData<CourseWithModule> getCourseWithModules(String courseId){
        return mAcademyDao.getCourseWithModuleById(courseId);
    }

    public void insertCourses(List<CourseEntity> courses){
        mAcademyDao.insertCourses(courses);;
    }

    public void insertModules(List<ModuleEntity> modules){
        mAcademyDao.insertModules(modules);
    }

    public void setCourseBookmark(CourseEntity courses, boolean newState){
        courses.setBookmarked(newState);
        mAcademyDao.updateCourse(courses);
    }

    public LiveData<ModuleEntity>getModuleWithContent(String moduleId){
        return mAcademyDao.getModuleById(moduleId);
    }

    public void updateContent(String content,String moduleId){
        mAcademyDao.updateModuleByContent(content,moduleId);
    }

    public void setReadModule(final ModuleEntity module){
        module.setmRead(true);
        mAcademyDao.updateModule(module);
    }




}
