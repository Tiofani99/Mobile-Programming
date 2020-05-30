package com.tiooooo.academy.ui.detail;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.data.source.AcademyRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailCourseViewModel extends ViewModel {
    private String courseId;
    private AcademyRepository academyRepository;

    public DetailCourseViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public LiveData<CourseEntity> getCourse() {
        return academyRepository.getCourseWithModules(courseId);

    }

    public LiveData<ArrayList<ModuleEntity>> getModules() {
        return academyRepository.getAllModulesByCourse(courseId);
    }
}
