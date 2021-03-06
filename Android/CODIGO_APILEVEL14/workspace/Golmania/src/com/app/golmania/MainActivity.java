package com.app.golmania;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.golmania.dto.Grupo;
import com.app.golmania.service.AppServices;

public class MainActivity extends FragmentActivity {
	
	
	/**/
	public static final String EXTRA_MESSAGE = "message";
    
	private static final int CALENDARIO = 9;
	public static final int HOME = 8;
	public static final int GOL = 10;
	private  Context context;
	
   
    
    
	public static String OPCION_SELECCIONADA = "INDICE_GRUPO";
	
	private DrawerLayout mDrawerLayout;
	public static ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mGroupTitles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ActionBar actionBar = getActionBar();
		getActionBar().setHomeButtonEnabled(false);
		actionBar.setCustomView(R.layout.menu_custom_layout);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.divisor));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
		setContentView(R.layout.activity_main);
		
		//Obtene indice desde el bundle
		int indice = getIntent().getIntExtra(OPCION_SELECCIONADA, HOME);
		
		mTitle = mDrawerTitle = getTitle();
		mGroupTitles = AppServices.obtenerTitulosMenuNavegacion().toArray(new String[8]);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mGroupTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		
		
		if (savedInstanceState == null) {
			selectItem(indice,getIntent().getExtras());
		}
		context = getApplicationContext();
		

	   
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		Log.d("APPMUNDIA", "drawerOpen:"+drawerOpen);
		// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case 0:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position,getIntent().getExtras());
		}
	}

	private void selectItem(int position, Bundle args) {
		//Decide que fragment iniciar con base a la position
		Fragment fragment = null;
		if(args == null){
			args = new Bundle();
		}
		
		switch (position) {
			case GOL:
				fragment = new GolFragment();
				setTitle("Goool!");
				break;
			case HOME:
				fragment = new GruposFragment();
				setTitle(mGroupTitles[position]);
				break;
			case CALENDARIO:
				setTitle(mGroupTitles[position]);
				fragment = new ScreenSlideCalendarioFragment();
				break;
			default:
				fragment = new EstadisticasFragment();
				setTitle(mGroupTitles[position]);
				args.putInt(EstadisticasFragment.INDICE_GRUPO, position);

		}
		
		fragment.setArguments(args);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
		App.setText(getActionBar().getCustomView().findViewById(R.id.menu_titulo), title.toString());
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	
	public void onClick(String displayName) {
		// TODO Auto-generated method stub		
		AppServices appservice = new AppServices(this);
		Grupo grupo = appservice.consultaGrupoPorDisplayName(displayName);
		setTitle(displayName);
		
		selectItem(Integer.parseInt(grupo.getId())-1,getIntent().getExtras());
		
	}

	public void onClickA(View v) {
		onClick("GRUPO A");
	}

	public void onClickB(View v) {
		onClick("GRUPO B");
	}

	public void onClickC(View v) {
		onClick("GRUPO C");
	}

	public void onClickD(View v) {
		onClick("GRUPO D");
	}

	public void onClickE(View v) {
		onClick("GRUPO E");
	}

	public void onClickF(View v) {
		onClick("GRUPO F");
	}

	public void onClickG(View v) {
		onClick("GRUPO G");
	}

	public void onClickH(View v) {
		onClick("GRUPO H");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		selectItem(GOL,intent.getExtras());
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onResumeFragments() {
		// TODO Auto-generated method stub
		super.onResumeFragments();
	}
	
	
	
	
}
