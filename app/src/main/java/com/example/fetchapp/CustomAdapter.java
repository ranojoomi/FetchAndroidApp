package com.example.fetchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final List<Item> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameView;
        private TextView listIdView;
        private TextView idView;

        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.textView);
            listIdView = (TextView) view.findViewById(R.id.textView2);
            idView = (TextView) view.findViewById(R.id.textView3);
        }
    }

    public CustomAdapter(List<Item> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.example_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        Item currentItem = dataset.get(position);

        String listId = String.valueOf(currentItem.getItemId()); // Convert to string
        String id = String.valueOf(currentItem.getId());

        viewHolder.nameView.setText("Name: " + currentItem.getName());
        viewHolder.listIdView.setText("listId: " + listId);
        viewHolder.idView.setText("id: " + id);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
