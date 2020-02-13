package com.scribenoteapp.scribe.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scribenoteapp.scribe.R;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.model.NoteModel;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private NoteModel model;
    private ListItemClickListener listItemClickListener;
    private boolean[] selectedIndexes;

    public interface ListItemClickListener {
        void onListItemClick(ModelIndex clickedItemIndex, View view);
    }

    public NoteAdapter(NoteModel model) {
        this.model = model;
        this.selectedIndexes = new boolean[this.model.rowCount()];
    }

    public boolean[] getSelectedIndexes(){
        return this.selectedIndexes;
    }


    public void setOnListItemClickListener(ListItemClickListener listItemClickListener) {
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

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView noteTitle;
        private TextView noteBody;
        private TextView noteDate;
        private ImageView noteIcon;

        public NoteViewHolder(final View noteView) {
            super(noteView);
            this.noteTitle = noteView.findViewById(R.id.note_title_hint);
            this.noteBody = noteView.findViewById(R.id.note_body_hint);
            this.noteDate = noteView.findViewById(R.id.note_date);
            this.noteIcon = noteView.findViewById(R.id.note_icon);

            noteView.setOnClickListener(this);
            this.noteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    NoteAdapter.this.selectedIndexes[getAdapterPosition()] = !NoteAdapter.this.getSelectedIndexes()[getAdapterPosition()];
                    notifyItemChanged(position);
                }
            });
        }

        public void bind(ModelIndex index) {
            this.noteTitle.setText((String) index.siblingAtColumn(0).data());
            this.noteBody.setText((String) index.siblingAtColumn(1).data());
            this.noteDate.setText((String) index.siblingAtColumn(2).data());

            if (NoteAdapter.this.selectedIndexes[getAdapterPosition()]) {
                this.itemView.setBackgroundColor(this.itemView.getResources().getColor(R.color.noteItemBackgroundSelectedColor));
                this.noteIcon.setImageResource(R.drawable.ic_check_circle_black_24dp);
            } else {
                this.itemView.setBackgroundColor(this.itemView.getResources().getColor(R.color.noteItemBackgroundColor));
                this.noteIcon.setImageResource((Integer) index.siblingAtColumn(3).data());
            }
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(model.index(getAdapterPosition(), 0), v);
        }
    }
}
