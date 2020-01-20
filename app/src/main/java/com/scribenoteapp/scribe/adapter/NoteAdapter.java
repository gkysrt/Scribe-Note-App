package com.scribenoteapp.scribe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.model.NoteModel;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;

import java.util.ArrayList;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private NoteModel model;
    private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex, View view);
    }

    public NoteAdapter(NoteModel model, ListItemClickListener listItemClickListener)
    {
        this.model= model;
        this.listItemClickListener = listItemClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.note_item_layout, parent, shouldAttachToParentImmediately);
        NoteViewHolder viewHolder = new NoteViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return model.rowCount();
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // TODO: BASENOTE A GÖRE İŞLEM YAPILMALI
        Note itemToBind = (Note) model.currentFolder().child(position);
        holder.bind(itemToBind.getTitle(), itemToBind.getBody(), itemToBind.getCreationDate());
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView noteTitle;
        private TextView noteBody;
        private TextView noteDate;

        public NoteViewHolder(View noteView)
        {
            super(noteView);
            this.noteTitle = noteView.findViewById(R.id.note_title_hint);
            this.noteBody = noteView.findViewById(R.id.note_body_hint);
            this.noteDate = noteView.findViewById(R.id.note_date);
            noteView.setOnClickListener(this);
        }

        public void bind(String noteTitle, String noteBody, String noteDate)
        {
            this.noteTitle.setText(noteTitle);
            this.noteDate.setText(noteDate);
            this.noteBody.setText(noteBody);
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(getAdapterPosition(), v);
        }
    }
}
