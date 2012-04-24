package com.huynt.lichphatsong.view;

import android.widget.LinearLayout;

import com.huynt.lichphatsong.LichPhatSongActivity;
import com.huynt.lichphatsong.R;
import com.huynt.lichphatsong.interfaces.Screen;

public class Details extends LinearLayout implements Screen {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = Details.class.getSimpleName();

	private LichPhatSongActivity mFCBCActivity;

	public Details(LichPhatSongActivity fcbcActivity) {
		super(fcbcActivity);
		mFCBCActivity = fcbcActivity;

		fcbcActivity.getLayoutInflater().inflate(R.layout.schedule_details_layout, this, true);

	}

	@Override
	public boolean back() {
		mFCBCActivity.gotoScreen(mFCBCActivity.getHomeScreen());
		return true;
	}

	@Override
	public int getScreenId() {
		return LichPhatSongActivity.VIEW_DETAILS;
	}

	@Override
	public void loadScreenData() {
	}

	@Override
	public void clearData() {
	}

	// ///////////////////////////////////////////////
	// INNER CLASS
	// ///////////////////////////////////////////////
}
