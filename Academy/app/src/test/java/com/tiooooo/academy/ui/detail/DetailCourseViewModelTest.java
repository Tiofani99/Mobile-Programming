package com.tiooooo.academy.ui.detail;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.utils.DataDummy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailCourseViewModelTest {

    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseID = dummyCourse.getCourseId();

    @Mock
    AcademyRepository academyRepository;

    @Before
    public void setUp(){
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setSelectedCourse(courseID);
    }

    @Test
    public void getCourse(){
        when(academyRepository.getCourseWithModules(courseID)).thenReturn(dummyCourse);
        CourseEntity courseEntity = viewModel.getCourse();
        verify(academyRepository).getCourseWithModules(courseID);
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());
    }

    @Test
    public void getModules(){
        when(academyRepository.getAllModulesByCourse(courseID)).thenReturn((ArrayList<ModuleEntity>) DataDummy.generateDummyModules(courseID));
        List<ModuleEntity> moduleEntities = viewModel.getModules();
        verify(academyRepository).getAllModulesByCourse(courseID);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());
    }




}