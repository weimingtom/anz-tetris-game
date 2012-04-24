package com.huynt.danhba.view;

import android.widget.LinearLayout;

import com.huynt.danhba.DanhBaActivity;
import com.huynt.danhba.R;
import com.huynt.danhba.interfaces.Screen;

public class HomeView extends LinearLayout implements Screen {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = HomeView.class.getSimpleName();

	private DanhBaActivity mFCBCActivity;

	public HomeView(DanhBaActivity fcbcActivity) {
		super(fcbcActivity);
		mFCBCActivity = fcbcActivity;

		fcbcActivity.getLayoutInflater().inflate(R.layout.danhba_layout, this, true);

	}

	@Override
	public boolean back() {
		return false;
	}

	@Override
	public int getScreenId() {
		return DanhBaActivity.VIEW_HOME;
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
