package com.wormhole.eyecare.utils;

import com.wormhole.eyecare.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public class DoubleTransaction {

	private FragmentTransaction transactionL;
	private FragmentTransaction transactionR;
	private FragmentManager fmL;
	private FragmentManager fmR;
	
	
	public DoubleTransaction(FragmentManager fm){
		
		transactionL = fm.beginTransaction();
		transactionR = fm.beginTransaction();
	}
	
	public void replace(Fragment fragmentL, Fragment fragmentR){
		
		transactionL.replace(R.id.fragment_content_left, fragmentL);
		transactionR.replace(R.id.fragment_content_right, fragmentR);
		transactionL.commit();
		transactionR.commit();
	}
	
	public void commit(){
//		transactionL.commit();
//		transactionR.commit();
	}
}
