package br.com.singletutorial.footballteam;

import java.util.List;

import com.squareup.picasso.Picasso;

import br.com.singletutorial.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * This classe using picasso to put a picture from web url in a ImageView
 * (background). For more information, enter here:
 * http://square.github.io/picasso/
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 *
 */

public class TeamArrayAdapter extends ArrayAdapter<TeamJSON> {

	private Context context;
	private int resource;
	private List<TeamJSON> list;

	/**
	 * 
	 * @param context
	 *            the real context of Activity
	 * @param resource
	 *            the layout from a single item
	 * @param list
	 *            the answer from parser
	 */
	public TeamArrayAdapter(Context context, int resource, List<TeamJSON> list) {
		super(context, resource, list);
		this.context = context;
		this.resource = resource;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// single position of List view (single element of team)
		if (convertView == null) {
			convertView = inflater.inflate(resource, parent, false);
		}
		// Single item from JSON
		TeamJSON singleItem = list.get(position);
		// Setters in elements
		// Title String
		TextView basicItemTitle = (TextView) convertView
				.findViewById(R.id.single_team_name);
		basicItemTitle.setText(singleItem.getTeamName());
		// Load picture in background using Picasso Library (static method)
		ImageView imageAvatar = (ImageView) convertView
				.findViewById(R.id.single_team_avatar);
		Picasso.with(context).load(singleItem.getUrlPicture()).fit()
				.centerCrop().error(R.drawable.ic_launcher).into(imageAvatar);

		return convertView;
	}

}
