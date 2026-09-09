package me.kartikarora.icanhazstream.explore.legacy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Legacy RecyclerView adapter for displaying movie streaming providers.
 * This is intentionally kept as Java for the Step 05 conversion exercise.
 */
// TODO: Step 05 — Convert this legacy Java RecyclerView adapter to Compose using Gemini
public class MovieProviderAdapter extends RecyclerView.Adapter<MovieProviderAdapter.MovieViewHolder> {

    private List<String> movieTitles = new ArrayList<>();

    public void setMovieTitles(List<String> titles) {
        this.movieTitles = titles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.textView.setText(movieTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return movieTitles.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
