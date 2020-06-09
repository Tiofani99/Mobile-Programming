package com.tiooooo.academy.ui.bookmark;

import com.tiooooo.academy.data.source.local.entity.CourseEntity;

interface BookmarkFragmentCallback {
    void onShareClick(CourseEntity courseEntity);
}
