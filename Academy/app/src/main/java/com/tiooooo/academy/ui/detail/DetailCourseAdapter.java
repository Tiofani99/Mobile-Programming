package com.tiooooo.academy.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiooooo.academy.R;
import com.tiooooo.academy.data.ModuleEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder> {

    private List<ModuleEntity> listModules = new ArrayList<>();

    void setModules(List<ModuleEntity> modules){
        if(modules == null) return;
        listModules.clear();
        listModules.addAll(modules);
    }

    @NonNull
    @Override
    public DetailCourseAdapter.ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_module_list,parent,false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCourseAdapter.ModuleViewHolder holder, int position) {
        ModuleEntity moduleEntity = listModules.get(position);
        holder.bind(moduleEntity);
    }

    @Override
    public int getItemCount() {
        return listModules.size();
    }

    public class ModuleViewHolder extends RecyclerView.ViewHolder {

        final TextView textTitle;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_module_title);
        }

        void bind(ModuleEntity moduleEntity){
            textTitle.setText(moduleEntity.getmTitle());
        }
    }
}
