package com.tiooooo.mynoteapps.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tiooooo.mynoteapps.R;
import com.tiooooo.mynoteapps.database.Note;
import com.tiooooo.mynoteapps.helper.NoteDiffCallback;
import com.tiooooo.mynoteapps.ui.insert.NoteAddUpdateActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final ArrayList<Note> listNotes = new ArrayList<>();
    private final Activity activity;

    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    void setListNotes(List<Note> list) {
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.listNotes,list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        listNotes.clear();
        listNotes.addAll(list);
        diffResult.dispatchUpdatesTo(this);

    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteAdapter.NoteViewHolder holder, int position) {
        holder.tvTitle.setText(listNotes.get(position).getTitle());
        holder.tvDate.setText(listNotes.get(position).getDate());
        holder.tvDescription.setText(listNotes.get(position).getDescription());
        holder.cvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, NoteAddUpdateActivity.class);
                intent.putExtra(NoteAddUpdateActivity.EXTRA_POSITION, holder.getAdapterPosition());
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, listNotes.get(holder.getAdapterPosition()));
                activity.startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_UPDATE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle, tvDescription, tvDate;
        final CardView cvNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
