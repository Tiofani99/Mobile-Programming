package com.tiooooo.academy.ui.bookmark;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tiooooo.academy.R;
import com.tiooooo.academy.data.source.local.entity.CourseEntity;
import com.tiooooo.academy.ui.detail.DetailCourseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkAdapter extends PagedListAdapter<CourseEntity,BookmarkAdapter.CourseViewHolder>{
    private final BookmarkFragmentCallback callback;

    BookmarkAdapter(BookmarkFragmentCallback callback){
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static DiffUtil.ItemCallback<CourseEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CourseEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
                    return oldItem.getCourseId().equals(newItem.getCourseId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull CourseEntity oldItem, @NonNull CourseEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };



    @NonNull
    @Override
    public BookmarkAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_bookmark,parent,false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.CourseViewHolder holder, int position) {
        CourseEntity courseEntity = getItem(position);
        if (courseEntity != null) {
            holder.bind(courseEntity);
        }
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder {

        final TextView tvTitle;
        final TextView tvDescription;
        final TextView tvDate;
        final ImageButton imgShare;
        final ImageView imgPoster;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            imgShare = itemView.findViewById(R.id.img_share);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }

        void bind(CourseEntity course){
            tvTitle.setText(course.getTitle());
            tvDescription.setText(course.getDescription());
            tvDate.setText(itemView.getResources().getString(R.string.deadline_date, course.getDeadline()));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailCourseActivity.class);
                intent.putExtra(DetailCourseActivity.EXTRA_COURSE, course.getCourseId());
                itemView.getContext().startActivity(intent);
            });
            imgShare.setOnClickListener(v -> callback.onShareClick(course));
            Glide.with(itemView.getContext())
                    .load(course.getImagePath())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(imgPoster);
        }
    }
}
