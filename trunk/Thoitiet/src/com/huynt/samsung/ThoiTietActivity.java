package com.huynt.samsung;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewAnimator;

import com.huynt.libs.interfaces.Screen;
import com.huynt.samsung.dialog.ChooseArea;
import com.huynt.samsung.view.HomeView;

public class ThoiTietActivity extends Activity {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = ThoiTietActivity.class.getSimpleName();

	public static final int VIEW_HOME = 0;

	Animation anim_in;
    Animation anim_out;
    
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

		anim_in = AnimationUtils.loadAnimation(this, R.anim.home_slide_in);
		anim_out = AnimationUtils.loadAnimation(this, R.anim.home_slide_out);
		
		mMainLayout = (ViewAnimator) findViewById(R.id.mainViewAnimator);

		createActivities();

		showArea();
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
			showArea();
			return true;
		case R.id.update:
			updateData();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// ///////////////////////////////////////////////
	// METHODS
	// ///////////////////////////////////////////////
	/**
	 * Show a pop-up for user select an area.
	 */
	private void showArea() {
		ChooseArea area = new ChooseArea(this);
		area.show();
	}

	private void updateData() {
		mHomeView.loadScreenData();
	}

	private void createActivities() {
		mHomeView = new HomeView(this);
		mMainLayout.addView(mHomeView, VIEW_HOME);
	}

	public void gotoScreen(Screen screen, boolean isNeedReload) {
		Screen currentScreen = (Screen) mMainLayout.getCurrentView();

//		if (currentScreen.equals(screen))
//			return;

		if(isNeedReload)
			screen.loadScreenData();

		mMainLayout.setInAnimation(anim_in);
		mMainLayout.setOutAnimation(anim_out);

		mMainLayout.setDisplayedChild(screen.getScreenId());
	}

	// ///////////////////////////////////////////////
	// GETTER/SETTER
	// ///////////////////////////////////////////////
	public Screen getHomeScreen() {
		return mHomeView;
	}

	// ///////////////////////////////////////////////
	// INNER CLASS
	// ///////////////////////////////////////////////
}