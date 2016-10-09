package com.mana.francebasket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mana.francebasket.R;
import com.mana.francebasket.model.ffbb.ChampionnatPreference;
import com.mana.francebasket.model.ffbb.ChampionnatsSettings;


	public class SettingsAdapter extends ArrayAdapter<ChampionnatPreference> {

		public SettingsAdapter(Context context,int textViewResourceId, ChampionnatsSettings objects) {		
			super(context, textViewResourceId, objects.getSettings());
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;

			ChampionnatPreference setting = getItem(position);

			if (convertView == null) {
				viewHolder = new ViewHolder();

				convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_settings, null);
				
//				viewHolder.groupId = (TextView) convertView.findViewById(R.id.group_id);
//				viewHolder.id = (TextView) convertView.findViewById(R.id.id);
				viewHolder.championnatName = (TextView) convertView.findViewById(R.id.championnat_name);

				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			

//			viewHolder.groupId.setText(setting.getGroupId());
//			viewHolder.id.setText(setting.getId());
			viewHolder.championnatName.setText(setting.getChampionnatLibelle());
			

			return convertView;
		}

		private class ViewHolder {
			TextView groupId;
			TextView id;
			TextView championnatName;
		}

	}
