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
import com.scribenoteapp.scribe.Static;
import com.scribenoteapp.scribe.framework.ModelIndex;
import com.scribenoteapp.scribe.framework.signals.Signal1;
import com.scribenoteapp.scribe.framework.signals.Signal2;
import com.scribenoteapp.scribe.framework.slots.Function;
import com.scribenoteapp.scribe.framework.slots.Function1;
import com.scribenoteapp.scribe.model.NoteModel;

import java.util.Arrays;

/**
 * Created by ALLDe on 13/01/2020.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private NoteModel model;
    private Signal1<ModelIndex> itemClickedSignal;
    private Boolean[] selectedIndexes;
    private Signal1<Boolean> itemSelectionChangedSignal;
    private Signal2<Integer, Boolean> itemSelectedSignal;

    public NoteAdapter(NoteModel model) {
        this.model = model;

        this.selectedIndexes = new Boolean[this.model.rowCount()];
        Arrays.fill(this.selectedIndexes, Boolean.FALSE);

        this.itemSelectionChangedSignal = new Signal1<>();
        this.itemSelectedSignal = new Signal2<>();
        this.itemClickedSignal = new Signal1<>();
        this.initSignalsAndSlots();
    }

    private void initSignalsAndSlots() {
        this.model.getModelResetSignal().connect("modelResetNoteAdapter", new Function<Void>() {
            @Override
            public Void function() {
                NoteAdapter.this.selectedIndexes = new Boolean[NoteAdapter.this.model.rowCount()];
                Arrays.fill(NoteAdapter.this.selectedIndexes, Boolean.FALSE);
                notifyDataSetChanged();
                return null;
            }
        });
    }

    public void setModel(NoteModel model) {
        this.model = model;
        NoteAdapter.this.selectedIndexes = new Boolean[NoteAdapter.this.model.rowCount()];
        Arrays.fill(NoteAdapter.this.selectedIndexes, Boolean.FALSE);
        this.notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public Signal1<Boolean> getItemSelectionChangedSignal() {
        return this.itemSelectionChangedSignal;
    }

    public Signal2<Integer, Boolean> getItemSelectedSignal() {
        return this.itemSelectedSignal;
    }

    public Signal1<ModelIndex> getItemClickedSignal() {
        return this.itemClickedSignal;
    }

    public Boolean[] getSelectedIndexes() {
        return this.selectedIndexes;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.note_item_layout, parent, shouldAttachToParentImmediately);
        return new NoteViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return model.rowCount();
    }


    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(this.model.index(position, 0));
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView noteTitle;
        private TextView noteBody;
        private TextView noteDate;
        private ImageView noteIcon;

        NoteViewHolder(final View noteView) {
            super(noteView);
            this.noteTitle = noteView.findViewById(R.id.note_title_hint);
            this.noteBody = noteView.findViewById(R.id.note_body_hint);
            this.noteDate = noteView.findViewById(R.id.note_date);
            this.noteIcon = noteView.findViewById(R.id.note_icon);
            this.initSignalsAndSlots();
        }

        private void initSignalsAndSlots() {
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ModelIndex index = NoteAdapter.this.model.index(getAdapterPosition(), 0);
                    NoteAdapter.this.getItemClickedSignal().emit(index);
                }
            });
            this.noteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    // check that have selection before selection
                    boolean beforExistsSelected = Static.exists(NoteAdapter.this.selectedIndexes, new Function1<Boolean, Boolean>() {
                        @Override
                        public Boolean function(Boolean item) {
                            return item;
                        }
                    });
                    // check that have selection after selection
                    Boolean result = !NoteAdapter.this.getSelectedIndexes()[position];
                    NoteAdapter.this.selectedIndexes[getAdapterPosition()] = result;
                    NoteAdapter.this.getItemSelectedSignal().emit(position, result);
                    boolean afterExistsSelected = Static.exists(NoteAdapter.this.selectedIndexes, new Function1<Boolean, Boolean>() {
                        @Override
                        public Boolean function(Boolean item) {
                            return item;
                        }
                    });
                    // if two of them are different that means, selection is changed. emit the signal
                    if (beforExistsSelected != afterExistsSelected) {
                        NoteAdapter.this.getItemSelectionChangedSignal().emit(afterExistsSelected);
                    }
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


    }
}
