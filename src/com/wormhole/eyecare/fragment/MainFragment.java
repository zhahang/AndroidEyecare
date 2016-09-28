package com.wormhole.eyecare.fragment;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.DoubleTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainFragment extends Fragment implements OnClickListener{

	private static boolean isOpen;
	private TextView beginBtn;
	private TextView exitBtn;
	
	private FragmentManager fm;
	private ModelFragment modelFragmentL;
	private ModelFragment modelFragmentR;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);
		beginBtn = (TextView)view.findViewById(R.id.begin_btn);
		exitBtn = (TextView) view.findViewById(R.id.exit_btn);
		Log.w("ischanged", "isOpen = "+isOpen);
		if(!isOpen){
			beginBtn.setOnClickListener(this);
			exitBtn.setOnClickListener(this);
		}
		isOpen = true;
		
		return view;
	}
	
	@Override
	public void onClick(View v) {
		
		if(fm == null){
			fm = getActivity().getFragmentManager();
		}
		DoubleTransaction transaction = new DoubleTransaction(fm);
		switch (v.getId()) {
		case R.id.begin_btn:
			if(modelFragmentL == null){
				modelFragmentL = new ModelFragment();
			}
			if(modelFragmentR == null){
				modelFragmentR = new ModelFragment();
			}
			transaction.replace(modelFragmentL, modelFragmentR);
			transaction.commit();
			break;
		case R.id.exit_btn:
			getActivity().finish();
			break;
		default:
			break;
		}
	}
	
}
