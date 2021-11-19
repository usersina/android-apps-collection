package com.example.lists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lists.R;
import com.example.lists.models.Movie;

import java.net.URI;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) { this.movieList = movieList; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.text_view.setText(movie.getTitle());
        holder.image_view.setImageResource(movie.getImgUrl());
    }

    @Override
    public int getItemCount() { return movieList.size(); }

    // ----- Custom
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView text_view;
        public ImageView image_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_view = itemView.findViewById(R.id.title);
            image_view = itemView.findViewById(R.id.image);
        }
    }
}
