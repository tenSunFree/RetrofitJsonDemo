package com.example.administrator.retrofitjsondemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Hero> heroes;

    public enum ITEM_TYPE {
        ITEM_TYPE_FIRST,
        ITEM_TYPE_OTHER
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_FIRST.ordinal() : ITEM_TYPE.ITEM_TYPE_OTHER.ordinal();
    }

    public RecyclerViewAdapter(Context context, List<Hero> heroes) {
        this.layoutInflater = LayoutInflater.from(context);
        this.heroes = heroes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_FIRST.ordinal()) {
            return new ViewHolder(layoutInflater.inflate(R.layout.recycler_view_first_item, parent, false));
        } else {
            return new ViewHolder(layoutInflater.inflate(R.layout.recycler_view_other_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.nameTextView.setText("Name: " + heroes.get(i).getName());
        viewHolder.teamTextView.setText("Team: " + heroes.get(i).getTeam());
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView teamTextView;

        ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.nameTextView);
            teamTextView = view.findViewById(R.id.teamTextView);
        }
    }
}
