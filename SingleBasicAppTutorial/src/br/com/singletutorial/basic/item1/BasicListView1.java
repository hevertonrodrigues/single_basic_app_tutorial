package br.com.singletutorial.basic.item1;

import com.google.gson.Gson;

import br.com.singletutorial.R;
import br.com.singletutorial.util.HTTPReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

/***
 * Contain a basic implementation of how to use a list view with ArrayAdapter in
 * Android.
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */
public class BasicListView1 extends Activity {

	// URL model
	/**
	 * Note: if this url don't appear in your browser, create a public link
	 * using this data:
	 * https://github.com/brunogabriel/single_basic_app_tutorial
	 * /blob/master/json/team_list.json
	 */
	private String JSON_URL = "https://dl.dropboxusercontent.com/u/68552256/Android%20github%20Request/team_list.json";

	// Instance of adapter
	private SingleItem1Adapter singleAdapter;

	// ListView to put items
	private ListView activityListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_list_1);
		// instance of list view
		activityListView = (ListView) findViewById(R.id.single_list1_listview);
		// Background instance of AsyncTask
		new BasicItemTask().execute();
	}

	private class BasicItemTask extends AsyncTask<Void, String, String> {

		// Loading data
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// New Instance of ProgressDialog in this Activity Context
			progressDialog = new ProgressDialog(BasicListView1.this);
			progressDialog.setCancelable(false); // Impossible to cancel this
													// dialog
			progressDialog.setTitle("Loading data");
			progressDialog.setMessage("Wait please, we searching data...");
			progressDialog.show();
		}

		@Override
		protected String doInBackground(Void... arg0) {
			// Reading data from Web
			try {
				String result = HTTPReader.readUrl(JSON_URL);
				return result;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// Remove progress bar from screen
			progressDialog.dismiss();

			if (result == null || result.trim().length() <= 0)
				Toast.makeText(BasicListView1.this,
						"Error, we don't receive data.", Toast.LENGTH_LONG)
						.show();
			else {
				Gson gson = new Gson();
				Log.v("Result:", result);
				JSONBasicItem1Result list = gson.fromJson(result,
						JSONBasicItem1Result.class);
				if (!list.isResult())
					Toast.makeText(BasicListView1.this, "No results.",
							Toast.LENGTH_LONG).show();
				else {
					// Create a instance of adapter
					singleAdapter = new SingleItem1Adapter(BasicListView1.this,
							R.layout.basic_item1, list.getSingleItems());
					if (singleAdapter != null && activityListView != null) {
						activityListView.setAdapter(singleAdapter);
					}

				}
			}
		}
	}

}
