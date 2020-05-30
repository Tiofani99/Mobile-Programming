package com.tiooooo.academy.ui.academy;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.source.AcademyRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<List<CourseEntity>> getCourses(){
        return academyRepository.getAllCourses();
    }
}
