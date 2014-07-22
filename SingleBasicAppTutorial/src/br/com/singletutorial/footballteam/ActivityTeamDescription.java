package br.com.singletutorial.footballteam;

import com.squareup.picasso.Picasso;

import br.com.singletutorial.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityTeamDescription extends Activity {

	private ImageView avatar;
	private TextView name;
	private TextView year;
	private TextView stadium;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_team_description);

		// Start all widgets
		avatar = (ImageView) findViewById(R.id.team_description_avatar);
		name = (TextView) findViewById(R.id.team_description_name);
		year = (TextView) findViewById(R.id.team_description_year);
		stadium = (TextView) findViewById(R.id.team_description_stadium);

		// Get results from Intent
		Intent intent = getIntent();
		// Get bundle
		setDataFromBundle(intent.getExtras());
	}

	public void setDataFromBundle(Bundle bundle) {
		if (bundle != null) {
			// Casting serializable data
			TeamJSON nextTeam = (TeamJSON) bundle.getSerializable("TEAM");
			if (nextTeam != null) {
				// Setting Values: avatar uses Picasso
				Picasso.with(getApplicationContext())
						.load(nextTeam.getUrlPicture()).fit().centerCrop()
						.error(R.drawable.ic_launcher).into(avatar);

				name.setText(nextTeam.getTeamName());
				// We need to convert the value in Integer
				year.setText(String.valueOf(nextTeam.getTeamYear()));
				stadium.setText(nextTeam.getTeamStadium());
			}

		}
	}

}
