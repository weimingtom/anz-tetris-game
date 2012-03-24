package com.game.mini.common;

import android.util.Log;

public class CommonClass {
	public static boolean DEBUG = true;
	public static String DEBUG_LOG_TAG = "GAME";
	
	public static int GAMESCREEN_WIDTH = 240; //camera width
	public static int GAMESCREEN_HEIGHT = 320; //camera height
	
	public static float GAMEUPDATE_INTERVAL = 0.1f;
	
//	public static String KEY_DATA_SENT_1 = "DATA_STRING_FROM_MENU";
	
	public static void PRINT_LOG(String value) {
		if(CommonClass.DEBUG)
			Log.i(CommonClass.DEBUG_LOG_TAG, value);
	}
}