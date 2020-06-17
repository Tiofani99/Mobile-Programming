package com.tiooooo.academy.ui.bookmark;

import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public LiveData<PagedList<CourseEntity>> getBookmarks(){
        return academyRepository.getBookmarkedCourses();
    }
}
