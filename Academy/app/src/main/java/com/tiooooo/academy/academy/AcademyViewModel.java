package com.tiooooo.academy.academy;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class AcademyViewModel extends ViewModel {
    public List<CourseEntity> getCourses(){
        return DataDummy.generateDummyCourses();
    }
}
