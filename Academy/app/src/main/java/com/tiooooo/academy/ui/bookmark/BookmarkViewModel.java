package com.tiooooo.academy.ui.bookmark;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class BookmarkViewModel extends ViewModel {
    private AcademyRepository academyRepository;

    public BookmarkViewModel(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    public List<CourseEntity> getBookmarks(){
        return academyRepository.getBookmarkedCourses();
    }
}
