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

public class FreedomFragment extends Fragment implements OnClickListener{

	private TextView relaxBtn;
	private TextView adjustBtn;
	private TextView trainBtn;
	private TextView watchBtn;
	private TextView blinkBtn;
	private TextView backBtn;
	
	private RelaxFragment relaxFragmentL;
	private AdjustFragment adjustFragmentL;
	private TrainFragment trainFragmentL;
	private WatchFragment watchFragmentL;
	private BlinkFragment blinkFragmentL;
	private ModelFragment modelFragmentL;
	private RelaxFragment relaxFragmentR;
	private AdjustFragment adjustFragmentR;
	private TrainFragment trainFragmentR;
	private WatchFragment watchFragmentR;
	private BlinkFragment blinkFragmentR;
	private ModelFragment modelFragmentR;
	private FragmentManager fm;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_freedom_model, container, false);
		
		relaxBtn = (TextView) view.findViewById(R.id.freedom_relax);
		adjustBtn = (TextView) view.findViewById(R.id.freedom_adjust);
		trainBtn = (TextView) view.findViewById(R.id.freedom_train);
		watchBtn = (TextView) view.findViewById(R.id.freedom_watch);
		blinkBtn = (TextView) view.findViewById(R.id.freedom_blink);
		backBtn = (TextView) view.findViewById(R.id.freedom_back);
		
		relaxBtn.setOnClickListener(this);
		adjustBtn.setOnClickListener(this);
		trainBtn.setOnClickListener(this);
		watchBtn.setOnClickListener(this);
		blinkBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		if(fm == null){
			fm = getActivity().getFragmentManager();
		}
		DoubleTransaction transaction = new DoubleTransaction(fm);
		switch (v.getId()) {
		case R.id.freedom_relax:
			if(relaxFragmentL == null){
				relaxFragmentL = new RelaxFragment();
			}
			if(relaxFragmentR == null){
				relaxFragmentR = new RelaxFragment();
			}
			transaction.replace(relaxFragmentL, relaxFragmentR);
			break;
		case R.id.freedom_adjust:
			if(adjustFragmentL == null){
				adjustFragmentL = new AdjustFragment();
			}
			if(adjustFragmentR == null){
				adjustFragmentR = new AdjustFragment();
			}
			transaction.replace(adjustFragmentL, adjustFragmentR);
			break;
		case R.id.freedom_train:
			if(trainFragmentL == null){
				trainFragmentL = new TrainFragment();
			}
			if(trainFragmentR == null){
				trainFragmentR = new TrainFragment();
			}
			transaction.replace(trainFragmentL, trainFragmentR);
			break;
		case R.id.freedom_watch:
			if(watchFragmentL == null){
				watchFragmentL = new WatchFragment();
			}
			if(watchFragmentR == null){
				watchFragmentR = new WatchFragment();
			}
			transaction.replace(watchFragmentL, watchFragmentR);
			break;
		case R.id.freedom_blink:
			if(blinkFragmentL == null){
				blinkFragmentL = new BlinkFragment();
			}
			if(blinkFragmentR == null){
				blinkFragmentR = new BlinkFragment();
			}
			transaction.replace(blinkFragmentL, blinkFragmentR);
			break;
		case R.id.freedom_back:
			if(modelFragmentL == null){
				modelFragmentL = new ModelFragment();
			}
			if(modelFragmentR == null){
				modelFragmentR = new ModelFragment();
			}
			transaction.replace(modelFragmentL, modelFragmentR);
			break;
		default:
			break;
		}
		transaction.commit();
	}
}
