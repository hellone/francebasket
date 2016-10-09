package com.mana.francebasket.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


	@SuppressLint("ValidFragment")
	public class MaDialogBox extends DialogFragment {
		
		AlertDialog.Builder builder;
		
		
	    public MaDialogBox(AlertDialog.Builder builder) {
			super();
			this.builder = builder;
		}


		@Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}
