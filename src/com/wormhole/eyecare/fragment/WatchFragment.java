package com.wormhole.eyecare.fragment;

import java.util.Timer;
import java.util.TimerTask;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;
import com.wormhole.eyecare.utils.DoubleTransaction;
import com.wormhole.eyecare.view.WatchView;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class WatchFragment extends Fragment implements OnClickListener{

	private static boolean isVisible;
	private TextView start;
	private TextView next;
	private WatchView watchView;
	private RelativeLayout watchLayout;
	private BlinkFragment blinkFragmentL;
	private BlinkFragment blinkFragmentR;
	private FragmentManager fm;
	
	private boolean playOne;
	private boolean playRotate;
	private boolean playComplete;
	private int count;
	private boolean isDailyModel;
	
	private Timer timer = new Timer();
	
	
	private class MyTimerTask extends TimerTask{
		int what;

		public MyTimerTask(int what) {
			this.what = what;
		}
		
		@Override
		public void run() {
			Message message = mHandler.obtainMessage();
			message.what = this.what;
			mHandler.sendMessage(message);
		}
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				onClick(start);
				break;
			case 1:
				watchView.setVisibility(View.GONE);
				next.setVisibility(View.VISIBLE);
				next.setFocusable(true);
				if(isDailyModel){
					MyTimerTask timerTask = new MyTimerTask(-1);
					timer.schedule(timerTask, 3000);
				}
				break;
			case -1:
				onClick(next);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_watch_view, container, false);
		
		start = (TextView) view.findViewById(R.id.watch_start);
		next = (TextView) view.findViewById(R.id.watch_next);
		start.setOnClickListener(this);
		next.setOnClickListener(this);
		
		watchView = new WatchView(getActivity());
		watchLayout = (RelativeLayout) view.findViewById(R.id.watch_layout);
		
		animControl();
		
		if(isDailyModel){
			MyTimerTask timerTask = new MyTimerTask(0);
			timer.schedule(timerTask, 3000);
		}
		return view;
	}

	@Override
	public void onClick(View v) {
		if(isVisible){
			return;
		}
		if(fm == null){
			fm = getActivity().getFragmentManager();
		}
		DoubleTransaction transaction = new DoubleTransaction(fm);
		
		switch (v.getId()) {
		case R.id.watch_start:
			v.setVisibility(View.GONE);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			watchLayout.removeView(watchView);
			watchLayout.addView(watchView, layoutParams);
			playOne = true;
			break;
		case R.id.watch_next:
			if(blinkFragmentL == null)
				blinkFragmentL = new BlinkFragment();
			if(blinkFragmentR == null)
				blinkFragmentR = new BlinkFragment();
			if(isDailyModel){
				blinkFragmentL.setModel(true);
				blinkFragmentR.setModel(true);
			}
			transaction.replace(blinkFragmentL, blinkFragmentR);
			transaction.commit();
			isVisible = true;
			break;
		default:
			break;
		}
	}
	
	private void animControl(){
		new Thread(new Runnable() {
			public void run() {
				while(true){
					if(playOne){
						if(count < 15){
							watchView.playCount(count);
							count++;
						}else {
							playOne = false;
							count = 0;
							playRotate = true;
							if(playComplete){
								Message message = mHandler.obtainMessage();
								message.what = 1;
								mHandler.sendMessage(message);
							}
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}else if (!playComplete && playRotate) {
						if(count < 201){
							watchView.playRotate(true);
							count++;
						}else if (count < 401) {
							watchView.playRotate(false);
							count++;
						}else {
							playRotate = false;
							count = 0;
							watchView.clear();
							playComplete = true;
							playOne = true;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
	
	public void setModel(boolean isDailyModel){
		this.isDailyModel = isDailyModel;
	}
}
