package com.game.mini.object;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import com.game.mini.common.CommonClass;
import com.game.mini.gamestate.GameState;
import com.game.mini.gamestate.LoadingGameState;
import com.game.mini.gamestate.MenuGameState;
import com.game.mini.gamestate.PlayGameState;
import com.game.mini.gamestate.ScoreGameState;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameHandler {
	
	public EnumGameState m_nextGameState = EnumGameState.NULL_GAMESTATE;
	public EnumGameState m_currentGameState = EnumGameState.NULL_GAMESTATE;
	
	public GameState m_runningGameState = null;
	
	public Scene m_rootScene = null;
	public Activity m_rootActivity = null;
	public Engine m_rootEngine = null;
	
	public GameHandler(Activity rootActivity, Engine rootEngine, Scene rootScene) {
		this.m_rootActivity = rootActivity;
		this.m_rootEngine = rootEngine;
		this.m_rootScene = rootScene;
		
		this.m_nextGameState = this.m_currentGameState = EnumGameState.NULL_GAMESTATE;
	}
	
	public void UpdateHandler() {
		if(this.m_nextGameState != EnumGameState.NULL_GAMESTATE) {
			CommonClass.PRINT_LOG("GameHandler::ChangeGameState()");
			
			if(this.m_runningGameState != null) {
				this.m_runningGameState.DestroyGameState();
			}
			
			switch(this.m_nextGameState) {
				case LOADING_GAMESTATE:
					this.m_runningGameState = new LoadingGameState(this.m_rootActivity, this.m_rootEngine);
					this.m_runningGameState.CreateGameState();
					break;
				case MENU_GAMESTATE:
					this.m_runningGameState = new MenuGameState(this.m_rootActivity, this.m_rootEngine);
					this.m_runningGameState.CreateGameState();
					break;
				case PLAY_GAMESTATE:
					this.m_runningGameState = new PlayGameState(this.m_rootActivity, this.m_rootEngine);
					this.m_runningGameState.CreateGameState();
					break;
				case SCORE_GAMESTATE:
					this.m_runningGameState = new ScoreGameState(this.m_rootActivity, this.m_rootEngine);
					this.m_runningGameState.CreateGameState();
					break;
			}
			
			this.m_currentGameState = this.m_nextGameState;
			this.m_nextGameState = EnumGameState.NULL_GAMESTATE;
			
			//attach current Scene into MainScene
			this.m_rootScene.setChildScene(this.m_runningGameState);
		}
		
		if(this.m_runningGameState != null) {
			this.m_runningGameState.UpdateGameState();
		}
	}
	
	public boolean TouchHandler(MotionEvent event) {
		if(this.m_runningGameState != null) {
			return this.m_runningGameState.OnTouchGameState(event);
		} else {
			return false;
		}
	}
	
	public boolean KeyDownHandler(int keycode, KeyEvent event) {
		if(this.m_runningGameState != null) {
			return this.m_runningGameState.OnKeyDownGameState(keycode, event);
		} else {
			return false;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////
	public void runGameState(EnumGameState gamestate) {
		if(gamestate != this.m_currentGameState) {
			this.m_nextGameState = gamestate;
		}
	}
	
	public void releaseHandler() {
		if(this.m_runningGameState != null) {
			this.m_runningGameState.DestroyGameState();
		}
	}
	
}
