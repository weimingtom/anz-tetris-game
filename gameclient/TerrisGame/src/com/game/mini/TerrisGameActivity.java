package com.game.mini;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSCounter;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.game.mini.common.CommonClass;
import com.game.mini.common.EnumGameState;
import com.game.mini.object.GameHandler;

public class TerrisGameActivity extends BaseGameActivity {

	private Camera m_cameraMain = null;
	private TimerHandler m_handlerUpdate = null;
	private Scene m_sceneMain = null;
	
	public GameHandler m_gameHandler = null;
	
	@Override
	public Engine onLoadEngine() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onLoadEngine()");
		
		this.m_cameraMain = new Camera(0, 0, CommonClass.GAMESCREEN_WIDTH, CommonClass.GAMESCREEN_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CommonClass.GAMESCREEN_WIDTH, CommonClass.GAMESCREEN_HEIGHT), this.m_cameraMain));
	}

	@Override
	public void onLoadResources() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onLoadResources()");
		
	}

	@Override
	public Scene onLoadScene() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onLoadScene()");
		
		this.mEngine.registerUpdateHandler(new FPSCounter());
		
		this.m_sceneMain = new Scene();
		
		this.m_handlerUpdate = new TimerHandler(CommonClass.GAMEUPDATE_INTERVAL, true, new ITimerCallback() {
			
			@Override
			public void onTimePassed(TimerHandler pTimerHandler) {
				TerrisGameActivity.this.UpdateMainScene();
			}
		});
		this.m_sceneMain.registerUpdateHandler(this.m_handlerUpdate); //this handler should be deleted when close game
		
		this.m_gameHandler = new GameHandler(this, this.mEngine, this.m_sceneMain);
		this.m_gameHandler.runGameState(EnumGameState.LOADING_GAMESTATE);
		
		return this.m_sceneMain;
	}

	@Override
	public void onLoadComplete() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onLoadComplete()");
		
	}

	@Override
	public void onPauseGame() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onPauseGame()");
		
		super.onPauseGame();
	}
	
	@Override
	public void onResumeGame() {
		CommonClass.PRINT_LOG("TerrisGameActivity::onResumeGame()");
		
		super.onResumeGame();
	}
	/////////////////////////////////////////Update Looping/////////////////////////////////////
	public void UpdateMainScene() {
		if(this.m_gameHandler != null) {
			this.m_gameHandler.UpdateHandler();
		}
	}
	
	/////////////////////////////////////////Event//////////////////////////////////////////////
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_MOVE:
				if(this.m_gameHandler != null) {
					return this.m_gameHandler.TouchHandler(event);
				} else {
					return false;
				}
			default:
				return super.onTouchEvent(event);
				
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			
			if(this.m_gameHandler.m_currentGameState == EnumGameState.MENU_GAMESTATE) {
				//stop & release game
				this.m_gameHandler.releaseHandler();
				this.m_sceneMain.clearChildScene();
				this.m_sceneMain.clearUpdateHandlers();
				this.finish();
				return true;
				
			} else if(this.m_gameHandler.m_currentGameState != EnumGameState.LOADING_GAMESTATE) {
				return this.m_gameHandler.KeyDownHandler(keyCode, event);
				
			} else { //In Loading state
				return false;
				
			}
			
		} else if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
			if(this.m_gameHandler.m_currentGameState == EnumGameState.LOADING_GAMESTATE) {
				return false;
				
			} else {
				return this.m_gameHandler.KeyDownHandler(keyCode, event);
				
			}
			
		} else {
			return super.onKeyDown(keyCode, event);
			
		}
	}
	
}
