package com.tiooooo.academy.ui.academy;

import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.vo.Resource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class AcademyViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public AcademyViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<Resource<PagedList<CourseEntity>>> getCourses(){
        return academyRepository.getAllCourses();
    }
}
