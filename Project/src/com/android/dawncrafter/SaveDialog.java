package com.android.dawncrafter;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.project.R;
//===============================================================================
//Project    : Android: Dawncrafter Theorycrafting Application               	=
//File Name  : SaveDialog.java									        		=
//File Type  : Fragment														    =
//Authors    : Brian Green & Brandon Aikey									    =
//Date       : 12/5/2013														=
//Description: Fragment that displays save dialog					            =
//===============================================================================

public class SaveDialog extends DialogFragment implements View.OnClickListener {
	Button cancel, save;
	EditText text;
	Communicator communicator;
	EditText mEdit;

	//Method casts activity as a communicator
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		communicator = (Communicator) activity;
	}

	//Method inflates view and unpacks passed data
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.save_dialog, null);
		text = (EditText) view.findViewById(R.id.editText1);
		cancel = (Button) view.findViewById(R.id.cancel_save);
		save = (Button) view.findViewById(R.id.save);
		cancel.setOnClickListener(this);
		save.setOnClickListener(this);
		setCancelable(false);

		return view;
	}

	//Method handles buttons clicks while saving or canceling save of build
	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.cancel_save) {
			communicator.onDialogSave("Save Canceled");
			dismiss();
		} else {
			String saveName = text.getText().toString();
			if (saveName.equals("")) {
				communicator.onDialogSave("Invalid build name.");
			} else {
				communicator.onBuildSave(saveName);
			}
			dismiss();
		}

	}
	
	//Interface allows a message to be sent back to the activity
	interface Communicator {
		public void onDialogSave(String message);

		public void onBuildSave(String name);
	}
}
