package com.example.spotified;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    SpotifyResult mDataset;
    SpotifyActivity spa;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.playListName);
        }
    }
    public MyAdapter(SpotifyResult myDataset, SpotifyActivity spotifyActivity) {
        mDataset = myDataset;
        spa=spotifyActivity;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycfile, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mDataset.getItems().get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (spa,songsActivity.class);
                i.putExtra("playListId",mDataset.getItems().get(position).getId());
               spa.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
//        return mDataset.length;
        return mDataset.getItems().size();
    }
}
