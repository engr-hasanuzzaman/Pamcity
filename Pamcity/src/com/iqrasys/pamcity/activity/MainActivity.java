package com.iqrasys.pamcity.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.iqrasys.pamcity.R;
import com.iqrasys.pamcity.adapter.CatgoryAdapter;
import com.iqrasys.pamcity.model.Category;
import com.iqrasys.pamcity.model.CategoryContainer;
import com.iqrasys.pamcity.model.CountryCityContainer;
import com.iqrasys.pamcity.parsar.HttpRequestHandler;
import com.iqrasys.pamcity.utility.DesignUtility;
import com.iqrasys.pamcity.utility.NetworkUtility;

import de.keyboardsurfer.android.widget.crouton.Crouton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

	CategoryContainer catContainer = new CategoryContainer();
	CountryCityContainer countryCityContainer = new CountryCityContainer();
	CountryCityContainer cityContainer = new CountryCityContainer();
	private String LOG = this.getClass().getSimpleName();
	Fragment fragmentMain = null, fragmentDefault = null;
	Spinner mSpinnerCountry, mSpinnerCity;
	GridView gv;
	List<String> mCountryList, mCityList;
	List<String> mCatName;
	List<Category> catItems;
	CatgoryAdapter cat_adptr;
	ProgressDialog pDialog;
	boolean isThredRun = false;
	
	int countrtIDs[]; // store country id
	boolean isCity = false, isCatUrlValid = false; // indicate spinner for citys
			// http://testing.megasoft.com.bd/Login/LoadCategoryAndroid/

	private ArrayAdapter<String> spinnerArrayAdapterCountry;
	private ArrayAdapter<String> spinnerArrayAdapterCity;

	private static String countryUrl = "http://testing.megasoft.com.bd/Home/LoadCountries";
	private static final String cityUrlBase = "http://testing.megasoft.com.bd/Home/LoadCities/";
	private static final String catUrlBase = "http://testing.megasoft.com.bd/Login/LoadCategoryAndroid/";
	private String cityUrl;
	GetContacts c;
	public static boolean IS_1ST_TIME = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		getActionBar().setCustomView(R.layout.actionbar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lay_activity_main);
		mSpinnerCountry = (Spinner) findViewById(R.id.spinner_country);
		mSpinnerCity = (Spinner) findViewById(R.id.spinner_city);
		mCountryList = new ArrayList<String>();
		mCountryList.add("Select CountryCity");
		mCityList = new ArrayList<String>();
		catItems = new ArrayList<Category>();
		c = new GetContacts();

		gv = (GridView) findViewById(R.id.gridView1);
		// CategoryJsonParser j=new CategoryJsonParser(this);

		IS_1ST_TIME = true;

	}

	@Override
	protected void onStart() {
		
		
	if (!NetworkUtility.isNetworkAvailable(this)) {
		
		Crouton.makeText(this, "No net Connection \n" +
				"connect & restart app", DesignUtility.getStyle()).show();
		Log.d("Network status", "No");
		
	}
	else{
		
		Log.d("Network status", "yes");
		if (IS_1ST_TIME) {
			c.execute();
			Log.i("execute", "call execute boy");
			IS_1ST_TIME = false;
		}
	}
	super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//
	private OnItemSelectedListener spinner_selector = new AdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			if (arg0.getId() == R.id.spinner_country) {

				switch (arg2) {

				case 0:
					if (mCityList.size() > 0)
						mCityList.clear();
					mCityList.add("Select CountryCity");
					spinnerArrayAdapterCity = new ArrayAdapter<String>(
							MainActivity.this,
							android.R.layout.simple_spinner_dropdown_item,
							mCityList);
					mSpinnerCity.setAdapter(spinnerArrayAdapterCity);

					break;

				default:
					isCity = true;
					int id = countryCityContainer.getCountryCityList()
							.get(arg2 - 1).getID();
					cityUrl = cityUrlBase + id;
					new GetContacts().execute();

					break;
				}

			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub

		}
	};

	private void attachFramgment() {

		cat_adptr = new CatgoryAdapter(this, R.layout.item_grid_view,
				catContainer.getCatList());
		gv.setAdapter(cat_adptr);
		gv.setOnItemClickListener(gridClick);

	}

	public void goDetail(View v) {

		startActivity();
	}

	private void startActivity() {

		startActivityForResult(new Intent(this, AdselectActivity.class), 100);

	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			MainActivity.this.isThredRun = true;
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			HttpRequestHandler sh = new HttpRequestHandler();
			HttpRequestHandler sh1 = new HttpRequestHandler();
			String jsonStr;
			String jsonCatImage = null;
			// Making a request to url and getting response cityUrl countryUrl
			if (!isCity) {
				jsonStr = sh.makeServiceCall(countryUrl,
						HttpRequestHandler.POST);
				jsonCatImage = sh1.makeServiceCall(catUrlBase,
						HttpRequestHandler.POST);
			} else {
				jsonStr = sh.makeServiceCall(cityUrl, HttpRequestHandler.POST);
			}
			if (jsonStr != null) {
				
					if (!isCity) {
						countryCityContainer = new Gson().fromJson(jsonStr,CountryCityContainer.class);
						catContainer = new Gson().fromJson(jsonCatImage,CategoryContainer.class);

					} else {
						cityContainer = new Gson().fromJson(jsonStr,CountryCityContainer.class);
					}
				
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			if (!isCity) {
				spinnerArrayAdapterCountry = new ArrayAdapter<String>(
						MainActivity.this,
						android.R.layout.simple_spinner_dropdown_item,
						countryCityContainer.countrtyNameList());
				mSpinnerCountry.setAdapter(spinnerArrayAdapterCountry);
				mSpinnerCountry.setOnItemSelectedListener(spinner_selector);
				attachFramgment();

			} else {
				spinnerArrayAdapterCity = new ArrayAdapter<String>(
						MainActivity.this,
						android.R.layout.simple_spinner_dropdown_item,
						cityContainer.cityNameList());
				
				mSpinnerCity.setAdapter(spinnerArrayAdapterCity);
				mSpinnerCity.setOnItemSelectedListener(spinner_selector);
			}
			MainActivity.this.isThredRun = true;
		}

	}

	/*
	 * click listener for each grid item
	 */
	private OnItemClickListener gridClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			startActivity();

		}
	};

	/*
	 * below section for delete this context catch
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (MainActivity.this.isThredRun) {
			c.cancel(true);
		}

		Log.i(LOG, "on onDestroy");
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
