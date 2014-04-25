package com.iqrasys.pamcity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqrasys.pamcity.R;
import com.iqrasys.pamcity.model.RowItem;

import java.util.List;
 
/*
 * Custom adapter for showing all category database
 */

public class CustomAdapterMoreInfo extends ArrayAdapter<RowItem> {
 
    Context context;
 
    public CustomAdapterMoreInfo(Context context, int resourceId,
            List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_more_info_adapter, null);
            holder = new ViewHolder();
            
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
            
        } 
        else
        {
        	holder = (ViewHolder) convertView.getTag();
        }
        
        holder.imageView.setImageResource(rowItem.getImageId());
        holder.txtTitle.setText(rowItem.getTitle());
	    	
        return convertView;
    }
}
