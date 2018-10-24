package cs2340.group61.doughnation.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class DonationViewAdapter extends RecyclerView.Adapter<DonationViewAdapter.ViewHolder>{

    private static final String TAG = "DonationViewAdapter";

    //ArrayList of strings that will hold the string information you want to display
    //Maybe a good display would be Timestamp: Short piece of the description.
    private ArrayList<String> mdonationDisplay = new ArrayList<>();
    private ArrayList<String> mdonationTitles = new ArrayList<>();

    private Context mContext;

    public DonationViewAdapter(Context context, ArrayList<String> donationDisplay,
                               ArrayList<String> donationTitles){
        mdonationDisplay = donationDisplay;
        mdonationTitles = donationTitles;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donation_view_item,
                viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        //Here is where you can fill recycler view textfield.
        viewHolder.donationText.setText(mdonationDisplay.get(i));
        viewHolder.donationTitle.setText(mdonationTitles.get(i));

        //On click listener to listen for clicks from an individual RecyclerView item!
        //Commented out for now because I've not created a donation details page to display
        //Details for each individual donation
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mdonationDisplay.get(i));

                Intent intent = new Intent(mContext, DonationDetailsActivity.class);
                intent.putExtra("location_name", mdonationDisplay.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdonationDisplay.size();
    }

    //Holds items in memory for each individual entry
    //In this case, that would be the list items and the Donation Descriptions
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView donationText;
        TextView donationTitle;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            donationText = itemView.findViewById(R.id.donation_preview);
            donationTitle = itemView.findViewById(R.id.donation_title);
            parentLayout = itemView.findViewById(R.id.DonationView);
        }
    }
}
