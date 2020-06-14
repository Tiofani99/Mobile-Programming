package com.tiooooo.academy.ui.detail;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.local.entity.CourseWithModule;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.vo.Resource;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class DetailCourseViewModel extends ViewModel {
    private MutableLiveData<String> courseId = new MutableLiveData<>();
    private AcademyRepository academyRepository;

    public DetailCourseViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<Resource<CourseWithModule>> courseModule = Transformations.switchMap(courseId,
            mCourseId -> academyRepository.getCourseWithModules(mCourseId));

    public String getCourseId() {
        return courseId.getValue();
    }

    public void setCourseId(String courseId) {
        this.courseId.setValue(courseId);
    }

    void setBookmark() {
        Resource<CourseWithModule> moduleResource = courseModule.getValue();
        if (moduleResource != null) {
            CourseWithModule courseWithModule = moduleResource.data;
            if (courseWithModule != null) {
                CourseEntity courseEntity = courseWithModule.mCourse;
                final boolean newState = !courseEntity.isBookmarked();
                academyRepository.setCourseBookmark(courseEntity, newState);
            }
        }
    }
}
