package com.tiooooo.academy.data.source.local.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class CourseWithModule {
    @Embedded
    public CourseEntity mCourse;

    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    public List<ModuleEntity> mModules;
}
