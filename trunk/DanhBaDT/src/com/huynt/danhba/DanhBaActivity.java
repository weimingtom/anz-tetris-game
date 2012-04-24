package com.huynt.danhba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import com.huynt.danhba.interfaces.Screen;
import com.huynt.danhba.view.HomeView;

public class DanhBaActivity extends Activity {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = DanhBaActivity.class.getSimpleName();

	public static final int VIEW_HOME = 0;

	// ///////////////////////////////////////////////
	// FIELDS
	// ///////////////////////////////////////////////
	private ViewAnimator mMainLayout;

	private HomeView mHomeView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mMainLayout = (ViewAnimator) findViewById(R.id.mainViewAnimator);
		
		createActivities();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main_menu, menu);
	    return true;

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.area:
//	            newGame();
	            return true;
	        case R.id.search:
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	// ///////////////////////////////////////////////
	// METHODS
	// ///////////////////////////////////////////////

	private void createActivities() {
		mHomeView = new HomeView(this);
		mMainLayout.addView(mHomeView, VIEW_HOME);
	}

	public void gotoScreen(Screen screen) {
		Screen currentScreen = (Screen) mMainLayout.getCurrentView();

		if (currentScreen.equals(screen))
			return;

		screen.loadScreenData();

		mMainLayout.setDisplayedChild(screen.getScreenId());
	}

	// ///////////////////////////////////////////////
	// GETTER/SETTER
	// ///////////////////////////////////////////////
	public Screen getHomeScreen() {
		return mHomeView;
	}
}