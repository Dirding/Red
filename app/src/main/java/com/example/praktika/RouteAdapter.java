package com.example.praktika;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktika.model.TransportRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteViewHolder> {
    private List<TransportRoute> routes = new ArrayList<>();
    private List<TransportRoute> routesFull = new ArrayList<>();
    private Context context;

    public void setRoutes(List<TransportRoute> newRoutes) {
        this.routes = newRoutes;
        this.routesFull = new ArrayList<>(newRoutes);
        notifyDataSetChanged();
    }

    public void applyFilters(String query, String typeFilter) {
        routes.clear();

        String lowerQuery = query.toLowerCase(Locale.ROOT).trim();

        for (TransportRoute item : routesFull) {

            boolean matchesType = typeFilter.equals("Все") ||
                    (item.vehicleType != null && item.vehicleType.equalsIgnoreCase(typeFilter));

            boolean matchesText = false;
            if (lowerQuery.isEmpty()) {
                matchesText = true;
            } else {
                matchesText = (item.number != null && item.number.toLowerCase(Locale.ROOT).contains(lowerQuery)) ||
                        (item.startPoint != null && item.startPoint.toLowerCase(Locale.ROOT).contains(lowerQuery)) ||
                        (item.endPoint != null && item.endPoint.toLowerCase(Locale.ROOT).contains(lowerQuery)) ||
                        (item.stops != null && item.stops.toLowerCase(Locale.ROOT).contains(lowerQuery));
            }

            if (matchesType && matchesText) {
                routes.add(item);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RouteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_route, parent, false);
        return new RouteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouteViewHolder holder, int position) {
        TransportRoute route = routes.get(position);

        holder.tvNumber.setText(route.number);
        holder.tvRoute.setText(route.startPoint + " - " + route.endPoint);
        holder.tvType.setText(route.vehicleType);
        holder.tvSchedule.setText("Расписание: " + route.schedule);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("route_data", route);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    static class RouteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber, tvRoute, tvType, tvSchedule;
        public RouteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvRoute = itemView.findViewById(R.id.tvRoute);
            tvType = itemView.findViewById(R.id.tvType);
            tvSchedule = itemView.findViewById(R.id.tvSchedule);
        }
    }
}