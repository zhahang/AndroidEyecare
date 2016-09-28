package com.wormhole.eyecare.fragment;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.DoubleTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ModelFragment extends Fragment implements OnClickListener{

	private TextView dailyModel;
	private TextView freedomModel;
	private TextView back;
	
	private MainFragment mainFragmentL;
	private RelaxFragment relaxFragmentL;
	private FreedomFragment freedomFragmentL;
	private MainFragment mainFragmentR;
	private RelaxFragment relaxFragmentR;
	private FreedomFragment freedomFragmentR;
	private FragmentManager fm;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_model, container, false);
		
		dailyModel = (TextView) view.findViewById(R.id.model_daily);
		freedomModel = (TextView) view.findViewById(R.id.model_freedom);
		back = (TextView) view.findViewById(R.id.model_back);
		
		dailyModel.setOnClickListener(this);
		freedomModel.setOnClickListener(this);
		back.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		if(fm == null){
			fm = getActivity().getFragmentManager();
		}
		DoubleTransaction transaction = new DoubleTransaction(fm);
		
		switch (v.getId()) {
		case R.id.model_daily:
			if(relaxFragmentL == null){
				relaxFragmentL = new RelaxFragment();
			}
			if(relaxFragmentR == null){
				relaxFragmentR = new RelaxFragment();
			}
			relaxFragmentL.setModel(true);
			relaxFragmentR.setModel(true);
			transaction.replace(relaxFragmentL, relaxFragmentR);
			break;
		case R.id.model_freedom:
			if(freedomFragmentL == null){
				freedomFragmentL = new FreedomFragment();
			}
			if(freedomFragmentR == null){
				freedomFragmentR = new FreedomFragment();
			}
			transaction.replace(freedomFragmentL, freedomFragmentR);
			break;
		case R.id.model_back:
			if(mainFragmentL == null){
				mainFragmentL = new MainFragment();
			}
			if(mainFragmentR == null){
				mainFragmentR = new MainFragment();
			}
			transaction.replace(mainFragmentL, mainFragmentR);
			break;
		default:
			break;
		}
		transaction.commit();
	}
}
