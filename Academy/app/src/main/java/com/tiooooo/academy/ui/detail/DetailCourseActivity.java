package com.tiooooo.academy.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiooooo.academy.R;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.ui.reader.CourseReaderActivity;
import com.tiooooo.academy.viewmodel.ViewModelFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailCourseActivity extends AppCompatActivity {

    public static final String EXTRA_COURSE = "extra course";
    DetailCourseViewModel viewModel;
    private Menu menu;
    private Button btnStart;
    private TextView textTitle;
    private TextView textDesc;
    private TextView textDate;
    private ImageView imagePoster;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        btnStart = findViewById(R.id.btn_start);
        textTitle = findViewById(R.id.text_title);
        textDesc = findViewById(R.id.text_description);
        textDate = findViewById(R.id.text_date);
        RecyclerView rvModule = findViewById(R.id.rv_module);
        imagePoster = findViewById(R.id.image_poster);
        progressBar = findViewById(R.id.progress_bar);

        DetailCourseAdapter adapter = new DetailCourseAdapter();

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(this, factory).get(DetailCourseViewModel.class);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Log.d("Woe", extras.getString(EXTRA_COURSE));
            String courseId = extras.getString(EXTRA_COURSE);
            if (courseId != null) {
                viewModel.setCourseId(courseId);

                viewModel.courseModule.observe(this,courseWithModuleResource -> {
                    if(courseWithModuleResource != null){
                        switch (courseWithModuleResource.status){
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;

                            case SUCCESS:
                                if(courseWithModuleResource.data != null){
                                    progressBar.setVisibility(View.GONE);
                                    adapter.setModules(courseWithModuleResource.data.mModules);
                                    adapter.notifyDataSetChanged();
                                    populateCourse(courseWithModuleResource.data.mCourse);
                                }
                                break;

                            case ERROR:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });



            }
        }


        rvModule.setNestedScrollingEnabled(false);
        rvModule.setLayoutManager(new LinearLayoutManager(this));
        rvModule.setHasFixedSize(true);
        rvModule.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvModule.getContext(), DividerItemDecoration.VERTICAL);
        rvModule.addItemDecoration(dividerItemDecoration);

    }

    private void populateCourse(CourseEntity courseEntity) {
        textTitle.setText(courseEntity.getTitle());
        textDesc.setText(courseEntity.getDescription());
        textDate.setText(getResources().getString(R.string.deadline_date, courseEntity.getDeadline()));

        Glide.with(this)
                .load(courseEntity.getImagePath())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(imagePoster);

        btnStart.setOnClickListener(view -> {
            Intent intent = new Intent(DetailCourseActivity.this, CourseReaderActivity.class);
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.getCourseId());
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        this.menu = menu;

        viewModel.courseModule.observe(this,courseWithModule ->{
            if(courseWithModule != null){
                switch (courseWithModule.status){
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;

                    case SUCCESS:
                        if(courseWithModule.data != null){
                            progressBar.setVisibility(View.GONE);
                            boolean state = courseWithModule.data.mCourse.isBookmarked();
                            setBookmarkState(state);
                        }
                        break;

                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_bookmark){
            viewModel.setBookmark();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setBookmarkState(Boolean state){
        if(menu == null) return;
        MenuItem menuItem = menu.findItem(R.id.action_bookmark);
        if(state){
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmarked_white));
        }else{
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white));
        }
    }
}
