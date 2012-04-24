package com.huynt.samsung.dialog;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huynt.samsung.PConfig;
import com.huynt.samsung.R;
import com.huynt.samsung.ThoiTietActivity;
import com.huynt.samsung.view.HomeView;

public class ChooseArea extends Dialog {

	// ///////////////////////////////////////////////
	// FIELDS
	// ///////////////////////////////////////////////
	private LinearLayout mListData;
	
	private LayoutInflater mInflater;
	
	private ThoiTietActivity mActivity;
	// ///////////////////////////////////////////////
	// CONSTRUCTORS
	// ///////////////////////////////////////////////
	public ChooseArea(ThoiTietActivity activity) {
		super(activity, android.R.style.Theme_Black_NoTitleBar);
		setContentView(R.layout.choose_area_popup);
		
		mActivity = activity;
		
		mInflater = activity.getLayoutInflater();
		
		mListData = (LinearLayout) findViewById(R.id.lnData);
	}

	public void show(){
		mListData.removeAllViews();
		
		for(int i = 0 ; i < PConfig.AREA.length ; i++){
			final RelativeLayout itemLayout = new RelativeLayout(getContext());
			mInflater.inflate(R.layout.area_item, itemLayout, true);
	
			final View rlItem = itemLayout.findViewById(R.id.rlItem);
			final TextView tvAreaName = (TextView) itemLayout.findViewById(R.id.tvAreaName);
			tvAreaName.setText(PConfig.AREA[i]);

			rlItem.setOnClickListener(new OnItemClickListener(i));
			
			mListData.addView(itemLayout);
		}
		
		super.show();
	}
	
	class OnItemClickListener implements View.OnClickListener{
		private int mIndex;
		
		public OnItemClickListener(int index){
			mIndex = index;
		}
		
		@Override
		public void onClick(View v) {
			((HomeView)mActivity.getHomeScreen()).setCurrentCtyIndex(mIndex);
			((HomeView)mActivity.getHomeScreen()).loadScreenData();
			dismiss();
		}
	}
}
