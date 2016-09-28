package com.wormhole.eyecare.fragment;

import java.util.Timer;
import java.util.TimerTask;

import com.wormhole.eyecare.R;
import com.wormhole.eyecare.utils.Constant;
import com.wormhole.eyecare.utils.DoubleTransaction;
import com.wormhole.eyecare.view.AdjustView;
import com.wormhole.eyecare.view.DoubleToast;

import android.app.Fragment;
import android.app.FragmentManager;
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

public class AdjustFragment extends Fragment implements OnClickListener {

	private static boolean isVisible = false;
	
	private TextView start;
	private TextView next;
	private AdjustView adjustView;
	private DoubleToast doubleToast;
	private RelativeLayout adjustLayout;
	private TrainFragment trainFragmentL;
	private TrainFragment trainFragmentR;
	private FragmentManager fm;

	private boolean isPlay;
	private int deltaY;
	private int count;
	private boolean isDailyModel;

	private Timer timer = new Timer();

	private class MyTimerTask extends TimerTask {
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

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case 0:
				onClick(start);
				break;
			case 1:
				adjustView.setVisibility(View.GONE);
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
		View view = inflater.inflate(R.layout.fragment_adjust_view, container, false);

		start = (TextView) view.findViewById(R.id.adjust_start);
		next = (TextView) view.findViewById(R.id.adjust_next);
		doubleToast = (DoubleToast) view.findViewById(R.id.adjust_toast);
		doubleToast.setFocusable(false);
		
		start.setOnClickListener(this);
		next.setOnClickListener(this);

		adjustView = new AdjustView(getActivity());
		adjustLayout = (RelativeLayout) view.findViewById(R.id.adjust_layout);
		animContorl();
		
		if(isDailyModel){
			MyTimerTask timerTask = new MyTimerTask(0);
			timer.schedule(timerTask, 3000);
		}
		doubleToast.show3DToast("双眼注释屏幕中心");
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
		case R.id.adjust_start:
			v.setVisibility(View.GONE);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			adjustLayout.removeView(adjustView);
			adjustLayout.addView(adjustView, layoutParams);
			isPlay = true;
			break;
		case R.id.adjust_next:
			if (trainFragmentL == null) {
				trainFragmentL = new TrainFragment();
			}
			if (trainFragmentR == null) {
				trainFragmentR = new TrainFragment();
			}
			if(isDailyModel){
				trainFragmentL.setModel(true);
				trainFragmentR.setModel(true);
			}
			transaction.replace(trainFragmentL,trainFragmentR);
			transaction.commit();
			isVisible = true;
			break;
		default:
			break;
		}
	}

	private void animContorl() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					if (isPlay) {
						if (deltaY < (Constant.SCREENHEIGHT * 9) / 10) {
							deltaY += 5;
							adjustView.playAnim(deltaY);
						} else {
							if (count == 0) {
								deltaY = 0;
								adjustView.setColor(getResources().getColor(R.color.indianred));
							} else if (count == 1) {
								deltaY = 0;
								adjustView.setColor(getResources().getColor(R.color.cornflowerblue));
							} else if (count == 2) {
								deltaY = 0;
								adjustView.setColor(getResources().getColor(R.color.greenyellow));
							} else {
								isPlay = false;
								Message message = mHandler.obtainMessage();
								message.what = 1;
								mHandler.sendMessage(message);
							}
							count++;
						}
					}
					try {
						Thread.sleep(21);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void setModel(boolean isDailyModel){
		this.isDailyModel = isDailyModel;
	}
}
