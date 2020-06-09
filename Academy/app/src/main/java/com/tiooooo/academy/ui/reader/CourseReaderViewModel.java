package com.tiooooo.academy.ui.reader;

import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.data.source.AcademyRepository;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CourseReaderViewModel extends ViewModel {
    private String courseId;
    private String moduleId;
    private AcademyRepository academyRepository;

    public CourseReaderViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public void setSelectedModule(String moduleId) {
        this.moduleId = moduleId;
    }

    public LiveData<ArrayList<ModuleEntity>> getModules() {
        return academyRepository.getAllModulesByCourse(courseId);
    }

    public LiveData<ModuleEntity> getSelectedModule() {
        return academyRepository.getContent(courseId, moduleId);
    }
}

