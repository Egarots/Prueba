package com.buildapp.testapp.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildapp.testapp.Data.Song;
import com.buildapp.testapp.R;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SongAdapter extends
        RecyclerView.Adapter<SongAdapter.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View songItemView = inflater.inflate(R.layout.songlayoutgeneral, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(songItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Song song = mSongs.get(position);

        TextView nameTextView = holder.nameTextView;
        TextView artistTextView = holder.artistTextView;
        ImageView imageView = holder.albumImage;

        nameTextView.setText(song.getTrackName());
        artistTextView.setText(song.getArtistName());
        Glide.with(holder.itemView.getContext()).load(song.getArtworkUrl100()).into(imageView);
        
    }

    @Override
    public int getItemCount()
    {
        return mSongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView artistTextView;
        public ImageView albumImage;


        public ViewHolder(View itemView) {

            super(itemView);

            albumImage = (ImageView) itemView.findViewById(R.id.albumImage);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            artistTextView = (TextView) itemView.findViewById(R.id.artistTextView);

        }

    }

    private List<Song> mSongs;

    public SongAdapter(List<Song> songs) {
        mSongs = songs;
    }
}
