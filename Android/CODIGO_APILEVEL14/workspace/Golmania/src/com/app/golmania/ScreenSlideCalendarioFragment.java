package com.app.golmania;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.golmania.service.AppServices;
import com.app.golmania.R;

public class ScreenSlideCalendarioFragment extends AbstractFragment {
	/**
     * The number of pages (wizard steps) to show in this demo.
     */
   

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	onCreateViewRoot(inflater, container, savedInstanceState,R.layout.viewpager_calendario);
    	//siempre que se instancia, se obtiene la fecha de hoy y con base  a la fecha
    	//se accesa a el primero de los 7 partidos que se deben mostrar en esa fecha
    	
    	
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(super.activityRoot.getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        
        AppServices services = new AppServices(super.activityRoot);//TODO:cambiarfecha
        String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        int pagina = services.consultaPaginaPartidosPorFecha(fecha);
        mPager.setCurrentItem(--pagina);
        
        return this.viewRoot;
    }

    /*@Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }*/

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private static final String TAG = "SLIDE";

		public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
        	Log.d(TAG, "position "+position);
        	Bundle args = new Bundle();
        	args.putInt(CalendarioFragment.PAGINA, position);
        	CalendarioFragment fragment = new CalendarioFragment();
        	fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return App.NUM_PAGES;
        }
    }
}

