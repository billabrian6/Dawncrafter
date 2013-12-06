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
//File Name  : EditDialog.java												    =
//File Type  : Fragment														    =
//Authors    : Brian Green & Brandon Aikey									    =
//Date       : 12/5/2013														=
//Description: Creates and handles a dialog for deleting and opening builds 	=
//===============================================================================

public class EditDialog extends DialogFragment implements View.OnClickListener {

	Button open, delete;
	EditText text;
	Communicator communicator;
	EditText mEdit;
	String buildname, buildurl;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		communicator = (Communicator) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Bundle ofsticks = getArguments();
		buildname = ofsticks.getString("name");
		buildurl = ofsticks.getString("url");
		View view = inflater.inflate(R.layout.edit_dialog, null);
		open = (Button) view.findViewById(R.id.open);
		delete = (Button) view.findViewById(R.id.delete);
		open.setOnClickListener(this);
		delete.setOnClickListener(this);
		setCancelable(false);
		return view;
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.open) {
			communicator.onDialogSelect("open", buildname, buildurl);
			dismiss();
		} else {
			communicator.onDialogSelect("delete", buildname, buildurl);
			dismiss();
		}

	}

	interface Communicator {
		public void onDialogSelect(String option, String name, String url);
	}
}
