package com.tiooooo.academy.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiooooo.academy.CourseReaderActivity;
import com.tiooooo.academy.R;
import com.tiooooo.academy.data.CourseEntity;
import com.tiooooo.academy.data.ModuleEntity;
import com.tiooooo.academy.utils.DataDummy;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE = "extra course";
    private Button btnStart;
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        btnStart = findViewById(R.id.btn_start);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        RecyclerView rvModule = findViewById(R.id.rv_module);
        imagePoster = findViewById(R.id.image_poster);

        DetailCourseAdapter adapter = new DetailCourseAdapter();

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Log.d("Woe",extras.getString(EXTRA_COURSE));
            String courseId = extras.getString(EXTRA_COURSE);
            if (courseId != null) {
                List<ModuleEntity> modules = DataDummy.generateDummyModules(courseId);
                adapter.setModules(modules);

                for (int i = 0; i < DataDummy.generateDummyCourses().size(); i++) {
                    CourseEntity courseEntity = DataDummy.generateDummyCourses().get(i);
                    if (courseEntity.getCourseId().equals(courseId)) {
                        populateCourse(courseEntity);
                    }
                }
            }
        }


        rvModule.setNestedScrollingEnabled(false);
        rvModule.setLayoutManager(new LinearLayoutManager(this));
        rvModule.setHasFixedSize(true);
        rvModule.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModule.getContext(),DividerItemDecoration.VERTICAL);
        rvModule.addItemDecoration(dividerItemDecoration);

    }

    private void populateCourse(CourseEntity courseEntity){
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(getResources().getString(R.string.deadline_date,courseEntity.getDeadline()));

        Glide.with(this)
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID,courseEntity.getCourseId());
            startActivity(intent);
        });

    }

}
