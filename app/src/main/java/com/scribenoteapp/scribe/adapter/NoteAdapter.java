package com.scribenoteapp.scribe.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.model.NoteModel;
import com.scribenoteapp.scribe.model.note.BaseNote;
import com.scribenoteapp.scribe.model.note.Note;
import com.scribenoteapp.scribe.model.note.NoteFolder;

import java.util.ArrayList;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private NoteModel model;
    private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener{
        void onListItemClick(ModelIndex clickedItemIndex, View view);
    }

    public NoteAdapter(NoteModel model)
    {
        this.model= model;
    }

    public void setOnListItemClickListener(ListItemClickListener listItemClickListener)
    {
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
        holder.bind(this.model.index(position, 0));
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView noteTitle;
        private TextView noteBody;
        private TextView noteDate;
        private ImageView noteIcon;

        public NoteViewHolder(View noteView)
        {
            super(noteView);
            this.noteTitle = noteView.findViewById(R.id.note_title_hint);
            this.noteBody = noteView.findViewById(R.id.note_body_hint);
            this.noteDate = noteView.findViewById(R.id.note_date);
            this.noteIcon = noteView.findViewById(R.id.note_icon);
            noteView.setOnClickListener(this);
        }

        public void bind(ModelIndex index)
        {
            BaseNote note = model.getItem(index);
            this.noteTitle.setText(note.getTitle());
            this.noteDate.setText(note.getCreationDate());

            if (note instanceof NoteFolder)
            {
                this.noteBody.setText(null);
                this.noteIcon.setImageResource(R.drawable.ic_folder_icon);
            }
            else if (note instanceof Note)
            {
                this.noteBody.setText(index.data());
                this.noteIcon.setImageResource(R.drawable.ic_note_icon);
            }
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(model.index(getAdapterPosition(), 0), v);
        }
    }
}
