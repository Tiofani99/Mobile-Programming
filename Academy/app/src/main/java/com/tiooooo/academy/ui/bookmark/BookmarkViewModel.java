package com.tiooooo.academy.ui.bookmark;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.AcademyRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<List<CourseEntity>> getBookmarks(){
        return academyRepository.getBookmarkedCourses();
    }
}
