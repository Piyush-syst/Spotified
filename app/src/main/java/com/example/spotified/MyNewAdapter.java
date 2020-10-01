package com.example.spotified;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyNewAdapter extends RecyclerView.Adapter<MyNewAdapter.MyViewHolder> {
    TrackListActivity tla;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.songName);
        }
    }
    public MyNewAdapter(TrackListActivity myDataset, songsActivity songsActivity) {
        tla=myDataset;

    }
    @NonNull
    @Override
    public MyNewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycl2, parent, false);

       MyViewHolder vh1 = new MyViewHolder(v);
        return vh1;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(tla.getItems().get(position).getTrack().getName());
    }

    @Override
    public int getItemCount() {
        return tla.getItems().size();
    }
}