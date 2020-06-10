package com.tiooooo.academy.data.source.remote;

import android.os.Handler;

import com.tiooooo.academy.data.source.remote.response.ContentResponse;
import com.tiooooo.academy.data.source.remote.response.CourseResponse;
import com.tiooooo.academy.data.source.remote.response.ModuleResponse;
import com.tiooooo.academy.utils.EspressoIdlingResource;
import com.tiooooo.academy.utils.JsonHelper;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler();
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<CourseResponse>>> getAllCourses() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<CourseResponse>>> resultCourse = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.success(jsonHelper.loadCourses()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultCourse;
    }

    public LiveData<ApiResponse<List<ModuleResponse>>> getModules(String courseId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<ModuleResponse>>> resultModules = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultModules.setValue(ApiResponse.success(jsonHelper.loadModule(courseId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultModules;
    }

    public LiveData<ApiResponse<ContentResponse>> getContent(String moduleId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<ContentResponse>> resultContent = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultContent.setValue(ApiResponse.success(jsonHelper.loadContent(moduleId)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultContent;
    }

    public interface LoadCoursesCallback {
        void onAllCoursesReceived(List<CourseResponse> courseResponses);
    }

    public interface LoadModulesCallback {
        void onAllModulesReceived(List<ModuleResponse> moduleResponses);
    }

    public interface LoadContentCallback {
        void onContentReceived(ContentResponse contentResponse);
    }
}
