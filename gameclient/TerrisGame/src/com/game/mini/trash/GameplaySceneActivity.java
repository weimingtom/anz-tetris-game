package com.game.mini.trash;

//import org.anddev.andengine.engine.Engine;
//import org.anddev.andengine.engine.camera.Camera;
//import org.anddev.andengine.engine.handler.IUpdateHandler;
//import org.anddev.andengine.engine.handler.timer.ITimerCallback;
//import org.anddev.andengine.engine.handler.timer.TimerHandler;
//import org.anddev.andengine.engine.options.EngineOptions;
//import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
//import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
//import org.anddev.andengine.entity.scene.Scene;
//import org.anddev.andengine.entity.scene.background.ColorBackground;
//import org.anddev.andengine.entity.text.Text;
//import org.anddev.andengine.entity.util.FPSLogger;
//import org.anddev.andengine.opengl.font.Font;
//import org.anddev.andengine.opengl.texture.TextureOptions;
//import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
//import org.anddev.andengine.ui.activity.BaseGameActivity;
//import org.anddev.andengine.util.HorizontalAlign;
//
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.view.KeyEvent;
//
//import com.game.mini.common.CommonClass;
//
//public class GameplaySceneActivity extends BaseGameActivity {
//
//	private Camera m_Camera;
//	private Scene m_MainScene;
//	private BitmapTextureAtlas m_FontTexture;
//	private Font m_Font;
//	
//	private Scene m_ScoreScene, m_TotalScene;
//	private boolean m_SwitchScene = false;
//	
//	@Override
//	public Engine onLoadEngine() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onLoadEngine()");
//		
//		this.m_Camera = new Camera(0, 0, CommonClass.GAMESCREEN_WIDTH, CommonClass.GAMESCREEN_HEIGHT);
//		EngineOptions eo = new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CommonClass.GAMESCREEN_WIDTH, CommonClass.GAMESCREEN_HEIGHT), this.m_Camera);
//		eo.setNeedsMusic(false);
//		eo.setNeedsSound(false);
//		
//		return new Engine(eo);
//	}
//
//	@Override
//	public void onLoadResources() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onLoadResources()");
//		
//		this.m_FontTexture = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//		this.m_Font = new Font(this.m_FontTexture, Typeface.create(Typeface.DEFAULT, Typeface.BOLD), 12, true, Color.BLACK);
//		
//		this.mEngine.getTextureManager().loadTexture(this.m_FontTexture);
//		this.getFontManager().loadFont(this.m_Font);
//	}
//
//	@Override
//	public Scene onLoadScene() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onLoadScene()");
//		
//		this.mEngine.registerUpdateHandler(new FPSLogger());
//		
//		this.m_MainScene = new Scene();
//		this.m_MainScene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
//		
//		this.m_MainScene.registerUpdateHandler(new TimerHandler(0.3f, true, new ITimerCallback() {
//			
//			@Override
//			public void onTimePassed(TimerHandler pTimerHandler) {
//				CommonClass.PRINT_LOG("GameplaySceneActivity::Update on MainScene");
//			}
//		}));
//		
//		this.m_ScoreScene = new Scene();
//		this.m_ScoreScene.setBackground(new ColorBackground(0.2f, 0.3f, 0.4f));
//		
//		this.m_TotalScene = new Scene();
//		this.m_TotalScene.setBackground(new ColorBackground(0.8f, 0.7f, 0.9f));
//		
////		Bundle bd = this.getIntent().getExtras();
////		final Text theText = new Text(10, CommonClass.GAMESCREEN_HEIGHT/2, this.m_Font, bd.getString(CommonClass.KEY_DATA_SENT_1), HorizontalAlign.CENTER);
////		theText.setZIndex(1);
//		
//		final Text scoreText = new Text(10, CommonClass.GAMESCREEN_HEIGHT/2, this.m_Font, "Score Scene", HorizontalAlign.CENTER);
//		this.m_ScoreScene.attachChild(scoreText);
//		
//		final Text totalText = new Text(10, CommonClass.GAMESCREEN_HEIGHT/2, this.m_Font, "Total Scene", HorizontalAlign.CENTER);
//		this.m_TotalScene.attachChild(totalText);
//		
//		
////		this.m_MainScene.attachChild(theText);
//		showGameScene(1);
//		
//		return this.m_MainScene;
//	}
//
//	@Override
//	public void onLoadComplete() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onLoadComplete()");
//		
//	}
//	
//	@Override
//	public void onResumeGame() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onResumeGame()");
//		
//		super.onResumeGame();
//	}
//	
//	@Override
//	public void onPauseGame() {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onPauseGame()");
//		
//		super.onPauseGame();
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		CommonClass.PRINT_LOG("GameplaySceneActivity::onKeyDown()");
//		
//		if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
//			//turn to next activity: ResultSceneActivity
//			CommonClass.PRINT_LOG("GameplaySceneActivity::KEYCODE_MENU()");
//			
//			this.m_SwitchScene = !this.m_SwitchScene;
//			if(this.m_SwitchScene)
//				showGameScene(2);
//			else
//				showGameScene(1);
//			
//			return true;
//		} else if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//			//turn to previous activity
//			CommonClass.PRINT_LOG("GameplaySceneActivity::KEYCODE_BACK()");
//			
//			this.finish();
//			return super.onKeyDown(keyCode, event);
//		} else {
//			CommonClass.PRINT_LOG("GameplaySceneActivity::super.onKeyDown()");
//			return super.onKeyDown(keyCode, event);
//		}
//	}
//	
//	//////////////////////////////////////////////////////////////////////////////////////////
//	
//	public void showGameScene(int index) {
//		switch(index) {
//			case 1:
//				this.m_MainScene.setChildScene(this.m_ScoreScene);
//				this.m_ScoreScene.setIgnoreUpdate(false);
//				this.m_ScoreScene.setVisible(true);
//				this.m_TotalScene.setIgnoreUpdate(true);
//				this.m_TotalScene.setVisible(false);
//				break;
//			case 2:
//				this.m_MainScene.setChildScene(this.m_TotalScene);
//				this.m_ScoreScene.setIgnoreUpdate(true);
//				this.m_ScoreScene.setVisible(false);
//				this.m_TotalScene.setIgnoreUpdate(false);
//				this.m_TotalScene.setVisible(true);
//				break;
//		}
//	}
//}
