package com.iqrasys.pamcity.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.iqrasys.pamcity.R;

import java.io.File;

public class CompanyDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_company_detail_activity);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.company_detail, menu);
		return true;
	}

	public void goForMoreInfo(View v) {
		startActivityForResult(new Intent(this, MoreInfoActivity.class), 5);
		
	}
	/*
	 * below section for delete this context catch 	
	 */
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
			
			 try {
			        trimCache(this);
			       // Toast.makeText(this,"onDestroy " ,Toast.LENGTH_LONG).show();
			    } catch (Exception e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }

			}

			public static void trimCache(Context context) {
			    try {
			       File dir = context.getCacheDir();
			       Log.i("catchdir", dir.toString());
			       if (dir != null && dir.isDirectory()) {
			          deleteDir(dir);
			       }
			    } catch (Exception e) {
			       // TODO: handle exception
			    }
			 }

			 public static boolean deleteDir(File dir) {
			    if (dir != null && dir.isDirectory()) {
			       String[] children = dir.list();
			       for (int i = 0; i < children.length; i++) {
			          boolean success = deleteDir(new File(dir, children[i]));
			          if (!success) {
			             return false;
			          }
			       }
			    }

			    // The directory is now empty so delete it
			    return dir.delete();
			 }
		/*
		 * End of catch delete blog
		 */
}
