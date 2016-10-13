package com.mana.francebasket.adapter;

import java.util.ArrayList;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mana.francebasket.R;
import com.mana.francebasket.model.ffbb.Match;

public class FavoriAdapter extends ArrayAdapter<Match> {

	RelativeLayout relativeLayout;
//	ChampionnatDetail championnatDetail; 

	public FavoriAdapter(Context context,int textViewResourceId, ArrayList<Match> objects) {		
		super(context, textViewResourceId, objects);
//		championnatDetail = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;

		Match match = getItem(position);

		if (convertView == null) {
			viewHolder = new ViewHolder();

			convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_scores_championnat, null);
//			convertView.setOnTouchListener(new MyTouchListener());
//			convertView.setOnDragListener(new MyDragListener());
			viewHolder.equipeNameHome = (TextView) convertView.findViewById(R.id.equipe_name_home);
			viewHolder.equipeNameVisitor = (TextView) convertView.findViewById(R.id.equipe_name_visitor);
			viewHolder.score = (TextView) convertView.findViewById(R.id.scores);
			viewHolder.addHome = (Button) convertView.findViewById(R.id.add_home);
			viewHolder.addVisitor = (Button) convertView.findViewById(R.id.add_visitor);
			viewHolder.addHome.setVisibility(View.GONE);
			viewHolder.addVisitor.setVisibility(View.GONE);
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
//				MaDialogBox dialogBox = new MaDialogBox();
//				FragmentManager fragmentManager = getContext().getPackageManager().getactivigetgetSupportFragmentManager();  
//				dialogBox.show(getActivity().getSupportFragmentManager(), "test");
				System.out.println("J'ai cliquer sur l'équipe : OnLongClickListener " + viewHolder.equipeNameHome.getText());
				return true;
			}
		});
		
		viewHolder.equipeNameVisitor.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				System.out.println("J'ai cliquer sur l'équipe : OnLongClickListener " + viewHolder.equipeNameVisitor.getText());
				return true;
			}
		});

		
		return convertView;
	}

	private class ViewHolder {
		TextView equipeNameHome;
		TextView equipeNameVisitor;
		TextView score;
		Button addHome;
		Button addVisitor;
	}
	
	private final class MyTouchListener implements OnTouchListener {
		  public boolean onTouch(View view, MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      ClipData data = ClipData.newPlainText("", "");
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		      view.startDrag(data, shadowBuilder, view, 0);
		      view.setVisibility(View.INVISIBLE);
		      return true;
		    } else {
		    return false;
		    }
		  }
		}
	
	class MyDragListener implements OnDragListener {
//		  Drawable enterShape = getContext().getResources().getDrawable(R.drawable.shape_droptarget);
//		  Drawable normalShape = getContext().getResources().getDrawable(R.drawable.shape);
		  
		  @Override
		  public boolean onDrag(View v, DragEvent event) {
		    int action = event.getAction();
		    switch (event.getAction()) {
		    case DragEvent.ACTION_DRAG_STARTED:
		    // do nothing
		      break;
		    case DragEvent.ACTION_DRAG_ENTERED:
//		      v.setBackgroundDrawable(enterShape);
		      break;
		    case DragEvent.ACTION_DRAG_EXITED:        
//		      v.setBackgroundDrawable(normalShape);
		      break;
		    case DragEvent.ACTION_DROP:
		      // Dropped, reassign View to ViewGroup
		      View view = (View) event.getLocalState();
		      ViewGroup owner = (ViewGroup) view.getParent();
		      owner.removeView(view);
		      LinearLayout container = (LinearLayout) v;
		      container.addView(view);
		      view.setVisibility(View.VISIBLE);
		      break;
		    case DragEvent.ACTION_DRAG_ENDED:
//		      v.setBackgroundDrawable(normalShape);
		      default:
		      break;
		    }
		    return true;
		  }
		} 

}
