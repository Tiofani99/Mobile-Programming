package com.tiooooo.academy.ui.academy;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcademyViewModelTest {

    private AcademyViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule;

    @Mock
    private AcademyRepository academyRepository;

    @Mock
    private Observer<List<CourseEntity>> observer;

    @Before
    public void setUp(){
        viewModel = new AcademyViewModel(academyRepository);
    }

    @Test
    public void getCourses(){
        ArrayList<CourseEntity> dummyCourse = DataDummy.generateDummyCourses();
        MutableLiveData<List<CourseEntity>> courses = new MutableLiveData<>();
        courses.setValue(dummyCourse);

        when(academyRepository.getAllCourses()).thenReturn(courses);
        List<CourseEntity> courseEntities = viewModel.getCourses().getValue();
        verify(academyRepository).getAllCourses();
        assertNotNull(courseEntities);
        assertEquals(5, courseEntities.size());

        viewModel.getCourses().observeForever(observer);
        verify(observer).onChanged(dummyCourse);
    }

}