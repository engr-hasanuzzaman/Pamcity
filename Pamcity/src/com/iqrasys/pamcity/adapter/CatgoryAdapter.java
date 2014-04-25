package com.iqrasys.pamcity.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iqrasys.pamcity.R;
import com.iqrasys.pamcity.model.Category;

import java.util.List;

public class CatgoryAdapter extends ArrayAdapter<Category>{
	
Context context;
List<Category>items;
	
	public CatgoryAdapter(Context context, int resource,List<Category> numbers) {
		super(context, resource);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.items=numbers;
	}
	/*private view holder class*/
    private class ViewHolder {
        ImageView catImage;
        TextView catTitle;
               
    }
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        
 
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_grid_view, null);
            holder = new ViewHolder();
            holder.catTitle=(TextView) convertView.findViewById(R.id.cat_name);
            holder.catImage = (ImageView) convertView.findViewById(R.id.cat_image);
            convertView.setTag(holder);
            
        } 
        else
        {
        	holder = (ViewHolder) convertView.getTag();
        }
        
        
        holder.catTitle.setText(items.get(position).getmTitle());
        Glide.load(items.get(position).getImageUrl())
//        .centerCrop()
        
        .placeholder(R.drawable.cat3)
//        .animate(R.anim.fade_in)
        .into(holder.catImage);

       
	    	
        return convertView;
    }
	
	@Override
	public int getCount() {
	    // TODO Auto-generated method stub
	    return items.size();
	}
}
