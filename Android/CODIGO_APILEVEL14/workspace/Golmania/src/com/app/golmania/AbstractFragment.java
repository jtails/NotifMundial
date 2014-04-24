package com.app.golmania;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AbstractFragment extends Fragment {
	/**
	 * La instancia principal del fragment
	 */
	protected View viewRoot;

	/**
	 * La Activity principal del fragment
	 */
	protected FragmentActivity activityRoot;

	/**
	 * Retorna de la vista principal el id
	 * 
	 * @param id
	 * @return
	 */
	protected View findViewById(int id) {
		return this.viewRoot != null ? viewRoot.findViewById(id) : null;
	}
	
	
	public void onCreateViewRoot(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState,int layout) {
		this.viewRoot = inflater.inflate(layout,container,false);
		this.activityRoot = (FragmentActivity) getActivity();
		super.onCreateView(inflater, container, savedInstanceState);
	}
	
}
