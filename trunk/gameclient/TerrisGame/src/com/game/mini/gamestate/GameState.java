package com.game.mini.gamestate;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class GameState extends Scene {
	
	protected Activity m_activityBase = null;
	protected Engine m_engineBase = null;
	
	public GameState(Activity base, Engine ebase) {
		this.m_activityBase = base;
		this.m_engineBase = ebase;
	}
	
	public abstract void CreateGameState();
	public abstract void DestroyGameState();
	public abstract void UpdateGameState();
	
	public abstract boolean OnKeyDownGameState(int keycode, KeyEvent event);
	public abstract boolean OnTouchGameState(MotionEvent event);
}
