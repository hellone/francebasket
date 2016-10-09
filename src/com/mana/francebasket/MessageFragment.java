package com.mana.francebasket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageFragment extends Fragment {

	
	private String message;

	public MessageFragment(String message) {
		this.message = message;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	 	View view = inflater.inflate(R.layout.fragment_message, container, false);	 	
	 	return view; }
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {  
		super.onActivityCreated(savedInstanceState);  

		TextView messageTextView = (TextView) getActivity().findViewById(R.id.txtLabel);
		messageTextView.setText(message);

	}
	
}
