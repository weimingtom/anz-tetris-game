package com.game.mini.gamestate;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.buffer.BufferObjectManager;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import com.game.mini.TerrisGameActivity;
import com.game.mini.common.CommonClass;
import com.game.mini.object.EnumGameState;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class PlayGameState extends GameState {

	private BitmapTextureAtlas m_textureatlasMain = null;
	private TextureRegion m_regionMain = null;
	
	private Sprite m_spriteMain = null;
	
	public PlayGameState(Activity base, Engine ebase) {
		super(base, ebase);
	}

	@Override
	public void CreateGameState() {
		CommonClass.PRINT_LOG("PlayGameState::CreateGameState()");
		
		this.setBackground(new ColorBackground(0.4f, 0.1f, 0.4f, 1.f));
		
		this.m_textureatlasMain = new BitmapTextureAtlas(64, 64, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.m_regionMain = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.m_textureatlasMain, this.m_activityBase, "face_box_tiled.png", 0, 0);
		
		this.m_engineBase.getTextureManager().loadTexture(this.m_textureatlasMain);
		
		this.m_spriteMain = new Sprite(50, 50, this.m_regionMain);
		this.attachChild(this.m_spriteMain);
		
		this.setIgnoreUpdate(false);
		this.setVisible(true);
	}

	@Override
	public void DestroyGameState() {
		CommonClass.PRINT_LOG("PlayGameState::DestroyGameState()");
		
		this.m_spriteMain.detachSelf();
		BufferObjectManager.getActiveInstance().unloadBufferObject(this.m_regionMain.getTextureBuffer());
		this.m_engineBase.getTextureManager().unloadTexture(this.m_textureatlasMain);
		this.m_textureatlasMain.clearTextureAtlasSources();
		
		this.setIgnoreUpdate(true);
		this.setVisible(false);
	}

	@Override
	public void UpdateGameState() {
		
	}

	@Override
	public boolean OnKeyDownGameState(int keycode, KeyEvent event) {
		if(keycode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
			CommonClass.PRINT_LOG("PlayGameState::OnKeyDownGameState() - KEYCODE_BACK");
			
			((TerrisGameActivity)this.m_activityBase).m_gameHandler.runGameState(EnumGameState.MENU_GAMESTATE);
			return true;
			
		} else if(keycode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN) {
			CommonClass.PRINT_LOG("PlayGameState::OnKeyDownGameState() - KEYCODE_MENU");
			return true;
			
		} else {
			return false;
		}
	}

	@Override
	public boolean OnTouchGameState(MotionEvent event) {
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN: //beginning of touch
				CommonClass.PRINT_LOG("PlayGameState::ACTION_DOWN()");
				return true;
			case MotionEvent.ACTION_UP: //ending of touch
				CommonClass.PRINT_LOG("PlayGameState::ACTION_UP()");
				
				((TerrisGameActivity)this.m_activityBase).m_gameHandler.runGameState(EnumGameState.SCORE_GAMESTATE);
				return true;
			case MotionEvent.ACTION_MOVE:
				CommonClass.PRINT_LOG("PlayGameState::ACTION_MOVE()");
				return true;
			default:
				return false;
		}
	}

}
