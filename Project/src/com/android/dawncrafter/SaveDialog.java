package com.android.dawncrafter;

import com.example.project.R;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SaveDialog extends DialogFragment implements View.OnClickListener{
	
	Button cancel, save;
	Communicator communicator;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		communicator = (Communicator) activity;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

		View view = inflater.inflate(R.layout.save_dialog, null);
		cancel = (Button) view.findViewById(R.id.cancel_save);
		save = (Button) view.findViewById(R.id.save);
		cancel.setOnClickListener(this);
		save.setOnClickListener(this);
		setCancelable(false);
		return view;
	}
	@Override
	public void onClick(View view) {
		if(view.getId() == R.id.cancel_save){
			communicator.onDialogSave("Save Canceled");
			dismiss();
		}else{
			communicator.onDialogSave("Build Saved");
			dismiss();
		}
		
	}
	interface Communicator{
		public void onDialogSave(String message);
	}
}
