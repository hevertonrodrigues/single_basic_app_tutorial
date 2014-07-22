package br.com.singletutorial.footballteam;

import com.google.gson.Gson;

import br.com.singletutorial.R;
import br.com.singletutorial.util.HTTPReader;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/***
 * Contain a basic implementation of how to use a list view with ArrayAdapter in
 * Android.
 * 
 * Copyright (c) 2008-2009 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * For more information enter in: https://code.google.com/p/google-gson
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 */
public class TeamListActivity extends Activity {

	// URL model
	/**
	 * Note: if this url don't appear in your browser, create a public link
	 * using this data:
	 * https://github.com/brunogabriel/single_basic_app_tutorial
	 * /blob/master/json/team_list.json
	 */
	private String JSON_URL = "https://dl.dropboxusercontent.com/u/68552256/Android%20github%20Request/team_list.json";

	// Instance of adapter
	private TeamArrayAdapter singleAdapter;

	// ListView to put items
	private ListView activityListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_team_list);
		// instance of list view
		activityListView = (ListView) findViewById(R.id.team_list_view);
		activityListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				TeamJSON clickedTeam = (TeamJSON) singleAdapter
						.getItem(position);
				if (clickedTeam != null) {
					Intent intent = new Intent(TeamListActivity.this,
							ActivityTeamDescription.class);

					Bundle bundle = new Bundle();
					bundle.putSerializable("TEAM", clickedTeam);
					intent.putExtras(bundle);

					startActivity(intent);
				}

			}
		});
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
			progressDialog = new ProgressDialog(TeamListActivity.this);
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
				Toast.makeText(TeamListActivity.this,
						"Error, we don't receive data.", Toast.LENGTH_LONG)
						.show();
			else {
				Gson gson = new Gson();
				Log.v("Result:", result);
				QueryTeamJSON list = gson.fromJson(result, QueryTeamJSON.class);
				if (!list.isResult())
					Toast.makeText(TeamListActivity.this, "No results.",
							Toast.LENGTH_LONG).show();
				else {
					// Create a instance of adapter
					singleAdapter = new TeamArrayAdapter(TeamListActivity.this,
							R.layout.layout_single_team, list.getSingleItems());
					if (singleAdapter != null && activityListView != null) {
						activityListView.setAdapter(singleAdapter);
					}

				}
			}
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		this.finish();
	}

}
