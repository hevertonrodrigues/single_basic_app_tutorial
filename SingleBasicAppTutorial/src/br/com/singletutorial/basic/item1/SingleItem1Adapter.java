package br.com.singletutorial.basic.item1;

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
 * (background).
 * 
 * Copyright 2013 Square, Inc.
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
 * For more information, enter here: http://square.github.io/picasso/
 * 
 * 
 * @author BrunoGabriel
 * @since 07/21/2014
 *
 */

public class SingleItem1Adapter extends ArrayAdapter<SingleBasicItem1JSON> {

	private Context context;
	private int resource;
	private List<SingleBasicItem1JSON> list;

	/**
	 * 
	 * @param context
	 *            the real context of Activity
	 * @param resource
	 *            the layout from a single item
	 * @param list
	 *            the answer from parser
	 */
	public SingleItem1Adapter(Context context, int resource,
			List<SingleBasicItem1JSON> list) {
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
		SingleBasicItem1JSON singleItem = list.get(position);
		// Setters in elements
		// Title String
		TextView basicItemTitle = (TextView) convertView
				.findViewById(R.id.basic_item1_title);
		basicItemTitle.setText(singleItem.getTeamName());
		// Load picture in background using Picasso Library (static method)
		ImageView imageAvatar = (ImageView) convertView
				.findViewById(R.id.basic_item1_avatar);
		Picasso.with(context).load(singleItem.getUrlPicture()).fit()
				.centerCrop().error(R.drawable.ic_launcher).into(imageAvatar);

		return convertView;
	}

}
