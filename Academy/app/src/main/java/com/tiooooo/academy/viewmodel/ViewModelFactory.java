package com.tiooooo.academy.viewmodel;

import android.content.Context;

import com.tiooooo.academy.data.source.AcademyRepository;
import com.tiooooo.academy.di.Injection;
import com.tiooooo.academy.ui.academy.AcademyViewModel;
import com.tiooooo.academy.ui.bookmark.BookmarkViewModel;
import com.tiooooo.academy.ui.detail.DetailCourseViewModel;
import com.tiooooo.academy.ui.reader.CourseReaderViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final AcademyRepository academyRepository;

    private ViewModelFactory(AcademyRepository academyRepository){
        this.academyRepository = academyRepository;
    }

    public static ViewModelFactory getInstance(Context context){
        if(INSTANCE == null){
            synchronized (ViewModelFactory.class){
                if(INSTANCE == null){
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AcademyViewModel.class)) {
            return (T) new AcademyViewModel(academyRepository);
        } else if (modelClass.isAssignableFrom(DetailCourseViewModel.class)) {
            return (T) new DetailCourseViewModel(academyRepository);
        } else if (modelClass.isAssignableFrom(BookmarkViewModel.class)) {
            return (T) new BookmarkViewModel(academyRepository);
        } else if (modelClass.isAssignableFrom(CourseReaderViewModel.class)) {
            return (T) new CourseReaderViewModel(academyRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
