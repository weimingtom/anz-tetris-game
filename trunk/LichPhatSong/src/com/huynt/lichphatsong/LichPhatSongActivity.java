package com.huynt.lichphatsong;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ViewAnimator;

import com.huynt.lichphatsong.interfaces.Screen;
import com.huynt.lichphatsong.view.Details;
import com.huynt.lichphatsong.view.HomeView;

public class LichPhatSongActivity extends Activity {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = LichPhatSongActivity.class.getSimpleName();

	public static final int VIEW_HOME = 0;
	public static final int VIEW_DETAILS = 1;
	
	// ///////////////////////////////////////////////
	// FIELDS
	// ///////////////////////////////////////////////
	private ViewAnimator mMainLayout;

	private HomeView mHomeView;
	private Details mDetailsView;
	
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
	        case R.id.setting:
//	            newGame();
	            return true;
	        case R.id.back:
	        	((Screen)mMainLayout.getCurrentView()).back();
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
		
		mDetailsView = new Details(this);
		mMainLayout.addView(mDetailsView, VIEW_DETAILS);
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
	
	public Screen getDetailsScreen(){
		return mDetailsView;
	}
}