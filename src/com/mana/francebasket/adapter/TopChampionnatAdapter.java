package com.mana.francebasket.adapter;

import java.util.ArrayList;

import com.mana.francebasket.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mana.francebasket.model.ffbb.Championnat;
import com.mana.francebasket.model.ffbb.Values;


public class TopChampionnatAdapter extends ArrayAdapter<Values> {

	public TopChampionnatAdapter(Context context,int textViewResourceId, ArrayList<Values> objects) {		
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		Values championnat = getItem(position);

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_championnats, null);

			viewHolder.championnatName = (TextView) convertView.findViewById(R.id.championnat);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

//		viewHolder.homeTeam.setText(String.format("%s %s", match.getHometeam(), match.getVisitorteam()));
		viewHolder.championnatName.setText(championnat.getName());

//		viewHolder.date.setText(match.getDate());

		return convertView;
	}

	private class ViewHolder {
		TextView championnatName;

	}

}
