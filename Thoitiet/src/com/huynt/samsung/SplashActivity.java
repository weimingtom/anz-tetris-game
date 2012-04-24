package com.huynt.samsung;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 1000){
				Intent intent = new Intent(SplashActivity.this, ThoiTietActivity.class);
				SplashActivity.this.startActivity(intent);
				SplashActivity.this.finish();
			}
		};
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
    	super.onCreate(icicle);
    	setContentView(R.layout.splash_layout);
    	mHandler.sendEmptyMessageDelayed(1000, 3000);
    }
    
    
}
