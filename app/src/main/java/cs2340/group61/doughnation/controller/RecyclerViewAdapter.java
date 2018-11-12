package cs2340.group61.doughnation.controller;

import android.annotation.SuppressLint;
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

import java.util.List;

import cs2340.group61.doughnation.R;

/**
 * This is a class for the Recycler View.
 */
@SuppressWarnings("ChainedMethodCall")
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private final List<String> lTitles;
    private final Context context;


    /**
     * Creates the recycler view.
     * @param lTitles The list of titles.
     * @param context The context.
     */
    public RecyclerViewAdapter(List<String> lTitles, Context context) {
        this.lTitles = lTitles;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_list_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,
                                 @SuppressLint("RecyclerView") final int i) {
        //Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.locName.setText(lTitles.get(i).toUpperCase());
        //Log.d(TAG, "onBindViewHolder: Titles set for recycler view");

        //On click listener to listen for clicks from an individual RecyclerView item!
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + lTitles.get(i));

                Intent intent = new Intent(context, LocationDetailsActivity.class);
                intent.putExtra("location_name", lTitles.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lTitles.size();
    }

    //Holds items in memory for each individual entry
    //In this case, that would be the list items and the Location Names
    class ViewHolder extends RecyclerView.ViewHolder{

        final TextView locName;
        final RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            locName = itemView.findViewById(R.id.location_name);
            parentLayout = itemView.findViewById(R.id.ItemLayout);

        }
    }
    
}
