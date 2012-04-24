package com.huynt.libs.interfaces;

public interface Screen {
	/**
	 * @return id of Screen
	 */
	public int getScreenId();
	
	/**
	 * Prepare data for Screen
	 */
	public void loadScreenData();
	
	/**
	 * Clear all data.
	 */
	public void clearData();

	public boolean back();
}
