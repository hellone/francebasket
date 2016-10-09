package com.mana.francebasket.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mana.francebasket.R;
import com.mana.francebasket.dialog.MaDialogBox;
import com.mana.francebasket.model.ffbb.ChampionnatDetail;
import com.mana.francebasket.model.ffbb.Match;
import com.mana.francebasket.utilities.MesPreferences;

public class ClassementAdapter extends ArrayAdapter<Match> {

	RelativeLayout relativeLayout;
	ChampionnatDetail championnatDetail; 
	String idChampionnat = "";
	String idGroup = "";
	FragmentActivity fragmentActivity ;

	public ClassementAdapter(Context context,int textViewResourceId, ChampionnatDetail objects, String idChampionnat, String idGroup, FragmentActivity fragmentActivity) {		
		super(context, textViewResourceId, objects.getMatchs().getValues());
		championnatDetail = objects;
		this.idChampionnat = idChampionnat;
		this.idGroup = idGroup;
		this.fragmentActivity = fragmentActivity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;

		final Match match = getItem(position);

		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_scores_championnat, null);

			viewHolder.equipeNameHome = (TextView) convertView.findViewById(R.id.equipe_name_home);
			viewHolder.equipeNameVisitor = (TextView) convertView.findViewById(R.id.equipe_name_visitor);
			viewHolder.score = (TextView) convertView.findViewById(R.id.scores);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}


		viewHolder.equipeNameHome.setText(match.getHometeam());
		viewHolder.equipeNameVisitor.setText(match.getVisitorteam());
		viewHolder.score.setText(match.getScore());

		
		viewHolder.equipeNameHome.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
		        builder.setMessage("Voulez-vous ajouter cette équipe dans vos favoris?")
		               .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   MesPreferences.addMatchToPreference(fragmentActivity, idChampionnat, idGroup, viewHolder.equipeNameHome.getText().toString());
		                   }
		               })
		               .setNegativeButton("NON", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       // User cancelled the dialog
		                   }
		               });
		        MaDialogBox dialog = new MaDialogBox(builder);
		        dialog.show(fragmentActivity.getSupportFragmentManager(), "1");
				
				
				return true;
			}
		});
		
		viewHolder.equipeNameVisitor.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
		        builder.setMessage("Voulez-vous ajouter cette Ã©quipe dans vos favori?")
		               .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                	   MesPreferences.addMatchToPreference(fragmentActivity, idChampionnat, idGroup, viewHolder.equipeNameVisitor.getText().toString());
		                   }
		               })
		               .setNegativeButton("NON", new DialogInterface.OnClickListener() {
		                   public void onClick(DialogInterface dialog, int id) {
		                       // User cancelled the dialog
		                   }
		               });
		        MaDialogBox dialog = new MaDialogBox(builder);
		        dialog.show(fragmentActivity.getSupportFragmentManager(), "1");
				return true;
			}
		});

		
		return convertView;
	}

	private class ViewHolder {
		TextView equipeNameHome;
		TextView equipeNameVisitor;
		TextView score;
	}

}
