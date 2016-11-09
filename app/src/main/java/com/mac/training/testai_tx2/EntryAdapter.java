package com.mac.training.testai_tx2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Fernando on 11/7/2016.
 */

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.MyViewHolder> {

    private List<FTEntry> FTEntrysList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView eName;
        //public TextView eContent;

        public MyViewHolder(View view) {
            super(view);
            eName = (TextView) view.findViewById(R.id.eName);
            //eContent = (TextView) view.findViewById(R.id.eContent);
        }
    }


    public EntryAdapter(List<FTEntry> FTEntrysList) {
        this.FTEntrysList = FTEntrysList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.entry_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FTEntry FTEntry = FTEntrysList.get(position);
        holder.eName.setText(FTEntry.getName());
//        holder.eContent.setText(FTEntry.getContent());
        holder.eName.setContentDescription(FTEntry.getKey().toString());
    }

    @Override
    public int getItemCount() {
        return FTEntrysList.size();
    }

}
