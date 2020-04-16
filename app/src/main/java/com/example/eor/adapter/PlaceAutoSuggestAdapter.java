package com.example.eor.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eor.api.PlacesAPI;

import java.util.ArrayList;

public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable {
    ArrayList<String> results;
    Context context;
    int resource_id;
    PlacesAPI placesAPI = new PlacesAPI();
    public PlaceAutoSuggestAdapter(Context context,int resID){
        super(context,resID);
        this.context=context;
        this.resource_id=resID;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return results.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint!=null){
                    results = placesAPI.autoComplete(constraint.toString());
                    filterResults.values = results;
                    filterResults.count=results.size();

                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results!=null && results.count>0){
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}
