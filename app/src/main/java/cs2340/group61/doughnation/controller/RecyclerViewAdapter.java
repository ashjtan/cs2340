//This is a class for the Recycler View.
package cs2340.group61.doughnation.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> lTitles = new ArrayList<>();
    private Context context;

    /**
     * Instantiates the Recycler View Adapter.
     * @param lTitles The location titles.
     * @param context The current state.
     */
    public RecyclerViewAdapter(ArrayList<String> lTitles, Context context) {
        this.lTitles = lTitles;
        this.context = context;
    }

    /**
     * A method to create the adapter.
     * @param viewGroup The view group.
     * @param i The position.
     * @return The ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * This method is to display information at specified position.
     * @param viewHolder The ViewHolder.
     * @param i The position.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.locName.setText(lTitles.get(i).toUpperCase());
        //Log.d(TAG, "onBindViewHolder: Titles set for recycler view");

        //On click listener to listen for clicks from an individual RecyclerView item!
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            /**
             * Allows users to click on the item.
             * @param v The page
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + lTitles.get(i));

                Intent intent = new Intent(context, LocationDetailsActivity.class);
                intent.putExtra("location_name", lTitles.get(i));
                context.startActivity(intent);
            }
        });
    }

    /**
     * Getter to retrieve number of items.
     * @return The number of items.
     */
    @Override
    public int getItemCount() {
        return lTitles.size();
    }

    /**
     * Holds items in memory for each individual entry.
     * In this case, that would be the list of items and the Location names.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView locName;
        RelativeLayout parentLayout;

        /**
         * Instantiates the viewholder.
         * @param itemView The location view.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            locName = itemView.findViewById(R.id.location_name);
            parentLayout = itemView.findViewById(R.id.ItemLayout);

        }
    }
    
}
