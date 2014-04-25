package com.iqrasys.pamcity.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.iqrasys.pamcity.R;
import com.iqrasys.pamcity.adapter.CustomAdapterMoreInfo;
import com.iqrasys.pamcity.model.RowItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
 * Menu that show all the options category for selecting word learning
 */


public class MoreInfoActivity extends Activity implements
		OnItemClickListener {

	// array of all categories images
	private String []name={"A","B","c","d"};
	ListView listView;
	List<RowItem> rowItems;
	CustomAdapterMoreInfo adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{

		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.actionbar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_more_info_activity);

		rowItems = new ArrayList<RowItem>(); // List of RowItem type
		
		for (int i = 0; i < name.length; i++) {
			
			RowItem item = new RowItem(R.drawable.ic_launcher, name[i]);
			rowItems.add(item);
		}

		listView = (ListView) findViewById(R.id.list_more_info);
		adapter = new CustomAdapterMoreInfo(this,
				R.layout.item_more_info_adapter, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		startNewActivity();

	}

	private void startNewActivity() {
		startActivityForResult(new Intent(this, CompanyDetailActivity.class), 5);
	}

	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		
//		adapter = new CustomAdapterMoreInfo(this,
//				R.layout.item_adselect_adapter, rowItems);
//		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
//		if (requestCode == 123 && resultCode == RESULT_OK) {
//			this.adapter.notifyDataSetChanged();
//		}

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