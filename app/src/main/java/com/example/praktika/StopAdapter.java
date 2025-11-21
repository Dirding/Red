package com.example.praktika;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StopAdapter extends RecyclerView.Adapter<StopAdapter.StopViewHolder> {
    public static class StopItem {
        String name;
        String time;
        public StopItem(String name, String time) { this.name = name; this.time = time; }
    }

    private List<StopItem> stops;

    public StopAdapter(List<StopItem> stops) {
        this.stops = stops;
    }

    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stop, parent, false);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position) {
        StopItem item = stops.get(position);
        holder.tvName.setText(item.name);
        holder.tvTime.setText(item.time);
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    static class StopViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvTime;
        public StopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStopName);
            tvTime = itemView.findViewById(R.id.tvStopTime);
        }
    }
}