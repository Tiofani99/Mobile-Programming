package com.tiooooo.academy.bookmark;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class BookmarkViewModel extends ViewModel {
    public List<CourseEntity> getBookmarks(){
        return DataDummy.generateDummyCourses();
    }
}
