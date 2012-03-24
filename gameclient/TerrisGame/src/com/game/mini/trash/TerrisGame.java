package com.game.mini.trash;

//import org.anddev.andengine.engine.Engine;
//import org.anddev.andengine.engine.options.EngineOptions;
//import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
//import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
//import org.anddev.andengine.engine.camera.Camera;
//import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
//import org.anddev.andengine.entity.scene.Scene;
//import org.anddev.andengine.entity.scene.background.ColorBackground;
//import org.anddev.andengine.entity.sprite.AnimatedSprite;
//import org.anddev.andengine.entity.sprite.Sprite;
//import org.anddev.andengine.entity.util.FPSLogger;
//import org.anddev.andengine.input.touch.TouchEvent;
//import org.anddev.andengine.opengl.texture.TextureOptions;
//import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
//import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
//import org.anddev.andengine.opengl.texture.region.TextureRegion;
//import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
//import org.anddev.andengine.ui.activity.BaseGameActivity;
//
//import com.game.mini.common.CommonClass;
//
//import android.widget.Toast;
//
//public class TerrisGame extends BaseGameActivity {
//	//dummy class :D
//	
//	// ===========================================================
//    // Constants
//    // ===========================================================
//
//    private static final int CAMERA_WIDTH = 240;
//    private static final int CAMERA_HEIGHT = 320;
//
//    // ===========================================================
//    // Fields
//    // ===========================================================
//
//    private Camera mCamera;
//    private BitmapTextureAtlas mBitmapTextureAtlas;
//    private TextureRegion mFaceTextureRegion;
//
//    // ===========================================================
//    // Constructors
//    // ===========================================================
//
//    // ===========================================================
//    // Getter & Setter
//    // ===========================================================
//
//    // ===========================================================
//    // Methods for/from SuperClass/Interfaces
//    // ===========================================================
//
//    @Override
//    public Engine onLoadEngine() {
//            Toast.makeText(this, "Touch & Drag the face!", Toast.LENGTH_LONG).show();
//            this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
//            return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
//    }
//
//    @Override
//    public void onLoadResources() {
//            this.mBitmapTextureAtlas = new BitmapTextureAtlas(32, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//            BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//            this.mFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mBitmapTextureAtlas, this, "face_box.png", 0, 0);
//
//            this.mEngine.getTextureManager().loadTexture(this.mBitmapTextureAtlas);
//    }
//
//    @Override
//    public Scene onLoadScene() {
//            this.mEngine.registerUpdateHandler(new FPSLogger());
//
//            final Scene scene = new Scene();
//            scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
//
//            final int centerX = (CAMERA_WIDTH - this.mFaceTextureRegion.getWidth()) / 2;
//            final int centerY = (CAMERA_HEIGHT - this.mFaceTextureRegion.getHeight()) / 2;
//            final Sprite face = new Sprite(centerX, centerY, this.mFaceTextureRegion) {
//                    @Override
//                    public boolean onAreaTouched(final TouchEvent pSceneTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY) {
//                    	this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2, pSceneTouchEvent.getY() - this.getHeight() / 2);
//                        
//                    	int eventAction = pSceneTouchEvent.getAction();
//                    	switch(eventAction) {
//	                    	case TouchEvent.ACTION_DOWN:
//	                    		CommonClass.PRINT_LOG("onAreaTouched::ACTION_DOWN");
//	                    		break;
//	                    	case TouchEvent.ACTION_MOVE:
//	                    		CommonClass.PRINT_LOG("onAreaTouched::ACTION_MOVE");
//	                    		break;
//	                    	case TouchEvent.ACTION_UP:
//	                    		CommonClass.PRINT_LOG("onAreaTouched::ACTION_UP");
//	                    		break;
//                    	}
//                        
//                        return true;
//                    }
//                    
//            };
////            face.setScale(4);
//            scene.attachChild(face);
//            scene.registerTouchArea(face);
//            scene.setTouchAreaBindingEnabled(true);
//            
//            return scene;
//    }
//
//    @Override
//    public void onLoadComplete() {
//
//    }
//
//    // ===========================================================
//    // Methods
//    // ===========================================================
//
//    // ===========================================================
//    // Inner and Anonymous Classes
//    // ===========================================================
//}