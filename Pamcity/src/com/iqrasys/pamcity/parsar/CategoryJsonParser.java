package com.iqrasys.pamcity.parsar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.iqrasys.pamcity.model.Category;
import com.iqrasys.pamcity.model.CategoryContainer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryJsonParser extends AsyncTask<String, Void, List<Category>> {

	Context contxt;
	ProgressDialog pDialog;
	private JSONArray jsonArray;
	private String catUrlBase;
	private List<Category> itemList;

	public CategoryJsonParser(Context contex) {
		// TODO Auto-generated constructor stub
		this.contxt=contex;
	}
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		this.contxt=;
		itemList=new ArrayList<Category>();
		// Showing progress dialog
		pDialog = new ProgressDialog(contxt);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);
		pDialog.show();

	}

	@Override
	protected List<Category> doInBackground(String... arg0) {
		// Creating service handler class instance
		catUrlBase=arg0[0];
		
		HttpRequestHandler sh = new HttpRequestHandler();
		String jsonStr;
		jsonStr=sh.makeServiceCall(catUrlBase, HttpRequestHandler.POST);

		Log.d("Images: ", "> " + jsonStr);

		if (jsonStr != null) {
			try {
//				JSONObject jsonObj = new JSONObject(jsonStr);
				CategoryContainer container=new CategoryContainer();
				// Getting JSON Array node
				container=new Gson().fromJson(jsonStr, CategoryContainer.class);
				container.getCatList();
//				jsonArray = jsonObj.getJSONArray("data");
//				Log.d("Image array: ", "> " + jsonArray);
//
//				// looping through All Contacts
//				for (int i = 0; i < jsonArray.length(); i++) {
//					Category obj=new Category();
//					JSONObject c = jsonArray.getJSONObject(i);
//					obj.setImageUrl(c.getString("Icon"));
//					obj.setmTitle(c.getString("Name"));
//					obj.setmId(Integer.parseInt(c.getString("ID")));
//					itemList.add( obj);
//				}
											
				
			} catch(Exception e)
			{
				Log.d("Error: ", " "+e.getMessage());
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}

		return null;
	}
	@Override
	protected void onPostExecute(List<Category> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		
	}

	private List<Category> ret(){
		return itemList;
	}
	

}