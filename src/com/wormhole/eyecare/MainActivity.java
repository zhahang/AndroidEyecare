package com.wormhole.eyecare;

import com.wormhole.eyecare.fragment.MainFragment;
import com.wormhole.eyecare.utils.Constant;
import com.wormhole.eyecare.utils.DoubleFragmentTransaction;
import com.wormhole.eyecare.utils.DoubleTransaction;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private MainFragment mainFragmentL;
	private MainFragment mainFragmentR;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
		
		getScreenInfo();
		setContentView(R.layout.activity_main);
//		Constant.THREADRUN = true;
		setDefaultFragment();
	}

	private void setDefaultFragment() {
		FragmentManager fm = getFragmentManager();
		DoubleTransaction transaction = new DoubleTransaction(fm);
		mainFragmentL = new MainFragment();
		mainFragmentR = new MainFragment();
		transaction.replace(mainFragmentL,mainFragmentR);
		transaction.commit();
		
//		FragmentManager fm1 = getFragmentManager();
//		FragmentTransaction transaction1 = fm.beginTransaction();
//		mainFragment = new MainFragment();
//		transaction1.replace(R.id.fragment_content_right, mainFragment);
//		transaction1.commit();
		
	}

	private void getScreenInfo(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Constant.SCREENWIDTH = dm.widthPixels;// 获取屏幕分辨率宽度
		Constant.SCREENHEIGHT = dm.heightPixels;
	}

	@Override
	protected void onDestroy() {
		Constant.THREADRUN = false;
		super.onDestroy();
	}
	
	@Override
	protected void onStop() {
//		Constant.THREADRUN = false;
		super.onStop();
	}
}