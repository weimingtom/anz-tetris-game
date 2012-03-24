package com.game.mini.trash;

//import org.anddev.andengine.engine.Engine;
//import org.anddev.andengine.engine.camera.Camera;
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
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.Typeface;
//import android.os.Bundle;
//import android.view.KeyEvent;
//import android.view.MotionEvent;
//
//import com.game.mini.common.CommonClass;
//
//public class MenuSceneActivity extends BaseGameActivity {
//
//	////////////////////////////////////////////////////////////
//	public static final int GAMEPLAY_REQUESTCODE = 1;
//	public static final int RESULT_REQUESTCODE = 2;
//	////////////////////////////////////////////////////////////
//	
//	private Camera m_Camera;
//	private Scene m_MainScene;
//	private BitmapTextureAtlas m_FontTexture;
//	private Font m_Font;
//	
//	@Override
//	public Engine onLoadEngine() {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onLoadEngine()");
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
//		CommonClass.PRINT_LOG("MenuSceneActivity::onLoadResources()");
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
//		CommonClass.PRINT_LOG("MenuSceneActivity::onLoadScene()");
//		
//		this.mEngine.registerUpdateHandler(new FPSLogger());
//		
//		this.m_MainScene = new Scene();
//		this.m_MainScene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
//		
//		final Text theText = new Text(10, CommonClass.GAMESCREEN_HEIGHT/2, this.m_Font, "MenuSceneActivity", HorizontalAlign.CENTER);
//		theText.setZIndex(1);
//		
//		this.m_MainScene.attachChild(theText);
////		this.m_MainScene.detachChild(theText);
//		
//		this.m_MainScene.registerUpdateHandler(new TimerHandler(0.5f, true, new ITimerCallback() {
//			
//			@Override
//			public void onTimePassed(TimerHandler pTimerHandler) {
//				//do update for current scene in here
//				MenuSceneActivity.this.updateScene();
//			}
//		}));
//		
//		return this.m_MainScene;
//	}
//	
//	@Override
//	public void onLoadComplete() {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onLoadComplete()");
//		
//	}
//	
//	@Override
//	public void onResumeGame() {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onResumeGame()");
//		
//		super.onResumeGame();
//	}
//	
//	@Override
//	public void onPauseGame() {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onPauseGame()");
//		
//		super.onPauseGame();
//	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onKeyDown()");
//		
//		if(keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
//			//turn to next activity: GameplaySceneActivity
//			CommonClass.PRINT_LOG("MenuSceneActivity::KEYCODE_MENU()");
//			
//			turnToGameplayScene();
//			return true;
//		} else if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
//			//turn to previous activity
//			CommonClass.PRINT_LOG("MenuSceneActivity::KEYCODE_BACK()");
//			
//			this.finishActivity(MenuSceneActivity.RESULT_REQUESTCODE);
//			this.finishActivity(MenuSceneActivity.GAMEPLAY_REQUESTCODE);
//			this.finish();
//			return true;
//		} else {
//			CommonClass.PRINT_LOG("MenuSceneActivity::super.onKeyDown()");
//			return super.onKeyDown(keyCode, event);
//		}
//	}
//	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch(event.getAction()) {
//			case MotionEvent.ACTION_DOWN:
//				CommonClass.PRINT_LOG("MenuSceneActivity::ACTION_DOWN");
//				break;
//			case MotionEvent.ACTION_UP:
//				CommonClass.PRINT_LOG("MenuSceneActivity::ACTION_UP");
//				break;
//			case MotionEvent.ACTION_MOVE:
//				CommonClass.PRINT_LOG("MenuSceneActivity::ACTION_MOVE");
//				break;
//		}
//		return super.onTouchEvent(event);
//	}
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		CommonClass.PRINT_LOG("MenuSceneActivity::onActivityResult()");
//		
//		super.onActivityResult(requestCode, resultCode, data);
//	}
//	
//	////////////////////////////// Non-Override functions //////////////////////////////
//	public void turnToGameplayScene() {
//		Intent it = new Intent(this, GameplaySceneActivity.class);
//		Bundle bd = new Bundle();
//		bd.putString(CommonClass.KEY_DATA_SENT_1, "GameplaySceneActivity");
//		it.putExtras(bd);
//		this.startActivityForResult(it, MenuSceneActivity.GAMEPLAY_REQUESTCODE);
//	}
//	
//	public void updateScene() {
//		CommonClass.PRINT_LOG("MenuSceneActivity::updateScene()");
//		
//		//do something for update
//	}
//	
//}
