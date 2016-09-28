package com.wormhole.eyecare.utils;

import com.wormhole.eyecare.R;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;

public class DoubleFragmentTransaction extends FragmentTransaction{

	private int contentLeft ;
	private int contentRight ;
	private FragmentTransaction transaction;
	
	public DoubleFragmentTransaction(int contentLeft,int contentRight, FragmentTransaction transaction) {
		this.transaction = transaction;
		this.contentLeft = contentLeft;
		this.contentRight = contentRight;
	}
	
	public FragmentTransaction replace(Fragment fragment) {
		transaction.replace(contentLeft, fragment);
		transaction.commit();
		transaction.replace(contentRight, fragment);
		transaction.commit();
		return null;
	}
	
	@Override
	public FragmentTransaction add(Fragment fragment, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction add(int containerViewId, Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction replace(int containerViewId, Fragment fragment) {
		
		return null;
	}

	@Override
	public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction remove(Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction hide(Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction show(Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction detach(Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction attach(Fragment fragment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FragmentTransaction setCustomAnimations(int enter, int exit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setCustomAnimations(int enter, int exit, int popEnter, int popExit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setTransition(int transit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction addSharedElement(View sharedElement, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setTransitionStyle(int styleRes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction addToBackStack(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAddToBackStackAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FragmentTransaction disallowAddToBackStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setBreadCrumbTitle(int res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setBreadCrumbTitle(CharSequence text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setBreadCrumbShortTitle(int res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FragmentTransaction setBreadCrumbShortTitle(CharSequence text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commit() {
		return 0;
	}

	@Override
	public int commitAllowingStateLoss() {
		// TODO Auto-generated method stub
		return 0;
	}

}
