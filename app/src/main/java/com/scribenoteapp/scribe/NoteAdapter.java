package com.scribenoteapp.scribe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{

    private ArrayList<Note> notes;
    private ListItemClickListener listItemClickListener;

    public NoteAdapter(ArrayList<Note> notes, ListItemClickListener listItemClickListener)
    {
        this.notes = notes;
    }

    public interface ListItemClickListener{
        void onListItemClick(int clickedItemIndex, View view);
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
        return notes.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notes.get(position).getTitle(), notes.get(position).getBody(), notes.get(position).getDate());
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
        }

        public void bind(String noteTitle, String noteBody, String noteDate)
        {
            this.noteTitle.setText(noteTitle);
            this.noteDate.setText(noteDate);
            this.noteBody.setText(noteBody);
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(getAdapterPosition(), itemView);
        }
    }
}
