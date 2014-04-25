package com.iqrasys.pamcity.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iqrasys.pamcity.R;
import com.iqrasys.pamcity.model.RowItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
 
/*
 * Custom adapter for showing all category database
 */

public class CustomAdapterAdselect extends ArrayAdapter<RowItem> {
 
    Context context;
    DisplayImageOptions options;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
 
    public CustomAdapterAdselect(Context context, int resourceId,
            List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
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
            convertView = mInflater.inflate(R.layout.item_adselect_adapter, null);
            holder = new ViewHolder();
            
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
            
        } 
        else
        {
        	holder = (ViewHolder) convertView.getTag();
        }
       /*
//        holder.imageView.setImageResource(rowItem.getImageId());
        Glide.load(rowItem.getTitle())
//        .centerCrop()
        
        .placeholder(R.drawable.ic_launcher)
//        .animate(R.anim.fade_in)
        .into(holder.imageView);
     */
        imageLoader.displayImage(rowItem.getTitle(), holder.imageView, options);

        holder.txtTitle.setText("Ads Title");
	    	
        return convertView;
    }
}
