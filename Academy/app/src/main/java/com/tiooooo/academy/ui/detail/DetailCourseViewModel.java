package com.tiooooo.academy.ui.detail;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class DetailCourseViewModel extends ViewModel {
    private String courseId;

    public void setSelectedCourse(String courseId){
        this.courseId = courseId;
    }

    public CourseEntity getCourse(){
        CourseEntity course = null;
        List<CourseEntity> courseEntities = DataDummy.generateDummyCourses();
        for (CourseEntity  courseEntity :courseEntities){
            if(courseEntity.getCourseId().equals(courseId)){
                course = courseEntity;
            }
        }

        return course;

    }

    public List<ModuleEntity> getModules(){
        return DataDummy.generateDummyModules(courseId);
    }
}
