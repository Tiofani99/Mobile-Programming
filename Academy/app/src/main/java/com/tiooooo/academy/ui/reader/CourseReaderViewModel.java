package com.tiooooo.academy.ui.reader;

import com.tiooooo.academy.data.ContentEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.lifecycle.ViewModel;

public class CourseReaderViewModel extends ViewModel {
    private String courseId;
    private String moduleId;

    public void setSelectedCourse(String courseId) {
        this.courseId = courseId;
    }

    public void setSelectedModule(String moduleId) {
        this.moduleId = moduleId;
    }

    public List<ModuleEntity> getModules() {
        return DataDummy.generateDummyModules(courseId);
    }

    public ModuleEntity getSelectedModule() {
        ModuleEntity module = null;
        List<ModuleEntity> moduleEntities = getModules();
        for (ModuleEntity moduleEntity: moduleEntities) {
            if (moduleEntity.getmModuleId().equals(moduleId)) {
                module = moduleEntity;
                module.contentEntity = new ContentEntity("<h3 class=\\\"fr-text-bordered\\\">" + module.getmTitle() + "</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
                break;
            }
        }
        return module;
    }
}

