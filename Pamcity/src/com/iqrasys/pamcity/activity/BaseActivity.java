package com.iqrasys.pamcity.activity;

import android.app.Activity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public abstract class BaseActivity extends Activity {



    public ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		imageLoader.init(ImageLoaderConfiguration.createDefault(getBaseContext()));
	}
	
/*
 *clear loader disk catch  
 */
public void clearDiskCache() {
	imageLoader.clearDiscCache();
	
}

/*
 *clear loader memory cache  
 */
public void clearMemoryCache() {
	imageLoader.clearMemoryCache();
	
	
}


   
}