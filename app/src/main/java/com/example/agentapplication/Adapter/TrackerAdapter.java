package com.example.agentapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agentapplication.Model.Result;
import com.example.agentapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrackerAdapter extends BaseAdapter {


    LayoutInflater inflater;
    private Context mContext;

    private List<Result> mData = new ArrayList<Result>();
    ViewHolder holder;
    private ArrayList<Result> arraylist;

    public TrackerAdapter(Context context, List<Result> worldpopulationlist) {
        this.mContext = context;
        this.mData = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Result>();
        this.arraylist.addAll(worldpopulationlist);
    }

//    public Adapter_List(Context mContext, ArrayList<Listdata_Model> mData) {
//        this.mContext = mContext;
//        this.mData = mData;
//    }

    public void setData(List<Result> mListData) {
        this.mData = mListData;
//        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.more_details, parent, false);
            holder = new ViewHolder();

            holder.dealerName = (TextView)row.findViewById(R.id.textview_name);
            holder.artworkUrl = (ImageView)row.findViewById(R.id.imageview_icon);
            holder.trackName = (TextView)row.findViewById(R.id.textview_Trackname);
            holder.priceRate = (TextView)row.findViewById(R.id.textview_price);
            holder.country = (TextView)row.findViewById(R.id.textview_Countryname);
            holder.currency = (TextView)row.findViewById(R.id.textview_Currencyyname);

            row.setTag(holder);

        } else {
            holder = (ViewHolder) row.getTag();
        }

        final Result item = mData.get(position);
        if ((item.getArtistName() != null) || (item.getArtistId() != null)) {

            holder.dealerName.setText(item.getArtistName());
            holder.trackName.setText(item.getTrackName());
            holder.country.setText(item.getCountry());
            holder.currency.setText(item.getCurrency());
            Picasso.get().load(item.getArtworkUrl60()).into(holder.artworkUrl);
            holder.rate  =item.getTrackPrice();
            holder.priceRate.setText(String.valueOf(holder.rate));



        } else {
            holder.dealerName.setText("no data");
            holder.trackName.setText("no data");
            holder.country.setText("no data");
            holder.currency.setText("no data");

        }

        return row;

    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mData.clear();
        if (charText.length() == 0) {
            mData.addAll(arraylist);
        }
        else
        {
            for (Result wp : arraylist)
            {
                if (wp.getArtistName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mData.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    static class ViewHolder {

        TextView dealerName, country, currency, trackName, priceRate;
        ImageView artworkUrl;
        int trackId;
        double rate;
        WebView URL;



        // "collectionPrice": 12.99,

    }

}