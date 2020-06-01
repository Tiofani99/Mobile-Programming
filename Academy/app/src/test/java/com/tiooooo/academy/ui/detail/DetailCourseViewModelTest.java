package com.tiooooo.academy.ui.detail;

import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.utils.DataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailCourseViewModelTest {

    private DetailCourseViewModel viewModel;
    private CourseEntity dummyCourse = DataDummy.generateDummyCourses().get(0);
    private String courseID = dummyCourse.getCourseId();
    private ArrayList<ModuleEntity> dummyModules = (ArrayList<ModuleEntity>) DataDummy.generateDummyModules(courseID);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule;

    @Mock
    AcademyRepository academyRepository;

    @Mock
    private Observer<CourseEntity> courseObserver;

    @Mock
    private Observer<List<ModuleEntity>> modulesObserver;

    @Before
    public void setUp() {
        viewModel = new DetailCourseViewModel(academyRepository);
        viewModel.setSelectedCourse(courseID);
    }

    @Test
    public void getCourse() {
        MutableLiveData<CourseEntity> course = new MutableLiveData();
        course.setValue(dummyCourse);
        when(academyRepository.getCourseWithModules(courseID)).thenReturn(course);
        CourseEntity courseEntity = viewModel.getCourse().getValue();
        verify(academyRepository).getCourseWithModules(courseID);
        assertNotNull(courseEntity);
        assertEquals(dummyCourse.getCourseId(), courseEntity.getCourseId());
        assertEquals(dummyCourse.getDeadline(), courseEntity.getDeadline());
        assertEquals(dummyCourse.getDescription(), courseEntity.getDescription());
        assertEquals(dummyCourse.getImagePath(), courseEntity.getImagePath());
        assertEquals(dummyCourse.getTitle(), courseEntity.getTitle());

        viewModel.getCourse().observeForever(courseObserver);
        courseObserver.onChanged(dummyCourse);
    }

    @Test
    public void getModules() {
        MutableLiveData<ArrayList<ModuleEntity>> module = new MutableLiveData<>();
        module.setValue(dummyModules);
        when(academyRepository.getAllModulesByCourse(courseID)).thenReturn(module);
        List<ModuleEntity> moduleEntities = viewModel.getModules().getValue();
        verify(academyRepository).getAllModulesByCourse(courseID);
        assertNotNull(moduleEntities);
        assertEquals(7, moduleEntities.size());

        viewModel.getModules().observeForever(modulesObserver);
        verify(modulesObserver).onChanged(dummyModules);
    }


}