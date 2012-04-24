package com.huynt.lichphatsong.view;

import android.view.View;
import android.widget.LinearLayout;

import com.huynt.lichphatsong.LichPhatSongActivity;
import com.huynt.lichphatsong.R;
import com.huynt.lichphatsong.interfaces.Screen;

public class HomeView extends LinearLayout implements Screen {
	// ///////////////////////////////////////////////
	// CONSTANTS
	// ///////////////////////////////////////////////
	public static final String TAG = HomeView.class.getSimpleName();

	private LichPhatSongActivity mFCBCActivity;

	public HomeView(LichPhatSongActivity fcbcActivity) {
		super(fcbcActivity);
		mFCBCActivity = fcbcActivity;

		fcbcActivity.getLayoutInflater().inflate(R.layout.schedule_layout, this, true);

		OnClickListener listener = new ItemClickListener();
		findViewById(R.id.line1).setOnClickListener(listener);
		findViewById(R.id.line2).setOnClickListener(listener);
		findViewById(R.id.line3).setOnClickListener(listener);
		findViewById(R.id.line4).setOnClickListener(listener);
		findViewById(R.id.line5).setOnClickListener(listener);
		findViewById(R.id.line6).setOnClickListener(listener);
	}

	@Override
	public boolean back() {
		mFCBCActivity.finish();
		return true;
	}

	@Override
	public int getScreenId() {
		return LichPhatSongActivity.VIEW_HOME;
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
	class ItemClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			mFCBCActivity.gotoScreen(mFCBCActivity.getDetailsScreen());
		}
	}
}
