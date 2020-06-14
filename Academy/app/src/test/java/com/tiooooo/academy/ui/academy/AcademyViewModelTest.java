package com.tiooooo.academy.ui.academy;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.utils.DataDummy;
import com.tiooooo.academy.vo.Resource;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcademyViewModelTest {
    private AcademyViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private AcademyRepository academyRepository;

    @Mock
    private Observer<Resource<List<CourseEntity>>> observer;

    @Before
    public void setUp() {
        viewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses() {
        Resource<List<CourseEntity>> dummyCourses = Resource.success(DataDummy.generateDummyCourses());
        MutableLiveData<Resource<List<CourseEntity>>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourses);

        when(academyRepository.getAllCourses()).thenReturn(courses);
        List<CourseEntity> courseEntities = viewModel.getCourses().getValue().data;
        verify(academyRepository).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());

        viewModel.getCourses().observeForever(observer);
        verify(observer).onChanged(dummyCourses);
    }
}