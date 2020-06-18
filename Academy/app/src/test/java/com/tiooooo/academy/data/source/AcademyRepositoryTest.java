package com.tiooooo.academy.data.source;

import com.tiooooo.academy.data.source.local.LocalDataSource;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.data.source.local.entity.CourseWithModule;
import com.tiooooo.academy.data.source.local.entity.ModuleEntity;
import com.tiooooo.academy.data.source.remote.RemoteDataSource;
import com.tiooooo.academy.data.source.remote.response.ContentResponse;
import com.tiooooo.academy.data.source.remote.response.CourseResponse;
import com.tiooooo.academy.data.source.remote.response.ModuleResponse;
import com.tiooooo.academy.utils.AppExecutors;
import com.tiooooo.academy.utils.DataDummy;
import com.tiooooo.academy.utils.LiveDataTestUtil;
import com.tiooooo.academy.utils.PagedListUtil;
import com.tiooooo.academy.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AcademyRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = mock(RemoteDataSource.class);
    private LocalDataSource local = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);

    private FakeAcademyRepository academyRepository = new FakeAcademyRepository(remote, local, appExecutors);

    private ArrayList<CourseResponse> courseResponses = (ArrayList<CourseResponse>) DataDummy.generateRemoteDummyCourses();
    private String courseId = courseResponses.get(0).getId();
    private ArrayList<ModuleResponse> moduleResponses = (ArrayList<ModuleResponse>) DataDummy.generateRemoteDummyModules(courseId);
    private String moduleId = moduleResponses.get(0).getModuleId();
    private ContentResponse content = DataDummy.generateRemoteDummyContent(moduleId);

    public void getAllCourses() {
        DataSource.Factory<Integer, CourseEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getAllCourses()).thenReturn(dataSourceFactory);
        academyRepository.getAllCourses();
        Resource<PagedList<CourseEntity>> courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourses()));
        verify(local).getAllCourses();
        assertNotNull(courseEntities.data);
        assertEquals(courseResponses.size(), courseEntities.data.size());
    }

    @Test
    public void getAllModulesByCourse() {
        MutableLiveData<List<ModuleEntity>> dummyModules = new MutableLiveData<>();
        dummyModules.setValue(DataDummy.generateDummyModules(courseId));
        when(local.getAllModulesByCourse(courseId)).thenReturn(dummyModules);

        Resource<List<ModuleEntity>> courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId));
        verify(local).getAllModulesByCourse(courseId);
        assertNotNull(courseEntities.data);
        assertEquals(moduleResponses.size(), courseEntities.data.size());
    }

    @Test
    public void getBookmarkedCourses() {
        DataSource.Factory<Integer, CourseEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getBookmarkedCourses()).thenReturn(dataSourceFactory);
        academyRepository.getBookmarkedCourses();

        Resource<PagedList<CourseEntity>> courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyCourses()));
        verify(local).getBookmarkedCourses();
        assertNotNull(courseEntities);
        assertEquals(courseResponses.size(), courseEntities.data.size());
    }

    @Test
    public void getContent() {
        MutableLiveData<ModuleEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyModuleWithContent(moduleId));
        when(local.getModuleWithContent(courseId)).thenReturn(dummyEntity);

        Resource<ModuleEntity> courseEntitiesContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId));
        verify(local).getModuleWithContent(courseId);
        assertNotNull(courseEntitiesContent);
        assertNotNull(courseEntitiesContent.data.contentEntity);
        assertNotNull(courseEntitiesContent.data.contentEntity.getContent());
        assertEquals(content.getContent(), courseEntitiesContent.data.contentEntity.getContent());
    }


    @Test
    public void getCourseWithModules() {
        MutableLiveData<CourseWithModule> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyCourseWithModules(DataDummy.generateDummyCourses().get(0), false));
        when(local.getCourseWithModules(courseId)).thenReturn(dummyEntity);

        Resource<CourseWithModule> courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId));
        verify(local).getCourseWithModules(courseId);
        assertNotNull(courseEntities.data);
        assertNotNull(courseEntities.data.mCourse.getTitle());
        assertEquals(courseResponses.get(0).getTitle(), courseEntities.data.mCourse.getTitle());
    }
}