//This class is to display a list of donations.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cs2340.group61.doughnation.R;

public class DonationViewAdapter extends RecyclerView.Adapter<DonationViewAdapter.ViewHolder>{

    private static final String TAG = "DonationViewAdapter";

    //ArrayList of strings that will hold the string information you want to display
    //Maybe a good display would be Timestamp: Short piece of the description.
    private List<String> mDonationDisplay;
    private List<String> mDonationTitles;

    private final Context mContext;

    DonationViewAdapter(Context context, List<String> donationDisplay,
                        List<String> donationTitles){
        mDonationDisplay = donationDisplay;
        mDonationTitles = donationTitles;
        mContext = context;
    }

    /**
     * Initializes ViewHolders.
     * @param viewGroup The view group.
     * @param i The position.
     * @return The final ViewHolder.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donation_view_item,
                viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * This method is to display information at specified position.
     * @param viewHolder The ViewHolder
     * @param i The position.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,
                                 @SuppressLint("RecyclerView") final int i) {

        //Here is where you can fill recycler view text field.
        viewHolder.donationText.setText(mDonationDisplay.get(i));
        viewHolder.donationTitle.setText(mDonationTitles.get(i));

        //On click listener to listen for clicks from an individual RecyclerView item!
        //Commented out for now because I've not created a donation details page to display
        //Details for each individual donation
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mDonationDisplay.get(i));

                Intent intent = new Intent(mContext, DonationDetailsActivity.class);
                intent.putExtra("donation_title", mDonationTitles.get(i));
                mContext.startActivity(intent);
            }
        });
    }

    /**
     * Displays the number of donations.
     * @return the number of donations.
     */
    @Override
    public int getItemCount() {
        Log.d(TAG, "donation display is of size " + mDonationDisplay.size());
        return mDonationDisplay.size();
    }

    /**
     * This is a class to hold items in memory for each individual entry.
     * In this case, that would be the list items and the Donation Descriptions.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView donationText;
        TextView donationTitle;
        RelativeLayout parentLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            donationText = itemView.findViewById(R.id.donation_preview);
            donationTitle = itemView.findViewById(R.id.donation_title);
            parentLayout = itemView.findViewById(R.id.DonationView);
        }
    }

    /**
     * This is a method to update the list of donations.
     * @param newDisplayList The list to replace the current one.
     * @param newTitlesList The list of donation names.
     */
    void updateList(Collection<String> newDisplayList, Collection<String> newTitlesList) {
        mDonationDisplay = new ArrayList<>();
        mDonationTitles = new ArrayList<>();
        mDonationDisplay.addAll(newDisplayList);
        mDonationTitles.addAll(newTitlesList);
        notifyDataSetChanged();

    }
}
