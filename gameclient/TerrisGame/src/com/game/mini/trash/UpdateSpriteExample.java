package com.game.mini.trash;

//import org.anddev.andengine.engine.Engine;
//import org.anddev.andengine.engine.options.EngineOptions;
//import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
//import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
//import org.anddev.andengine.engine.camera.Camera;
//import org.anddev.andengine.engine.handler.physics.PhysicsHandler;
//import org.anddev.andengine.entity.scene.Scene;
//import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
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
//import android.widget.Toast;
//
//public class UpdateSpriteExample extends BaseGameActivity {
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
//    private TiledTextureRegion mFaceTextureRegion;
//
//    private boolean mToggleBox = true;
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
//            Toast.makeText(this, "Touch the screen to update (redraw) an existing BitmapTextureAtlas with every touch!", Toast.LENGTH_LONG).show();
//            this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
//            return new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
//    }
//
//    @Override
//    public void onLoadResources() {
//            this.mBitmapTextureAtlas = new BitmapTextureAtlas(128, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
//            BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//            this.mFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "helicopter_tiled.png", 0, 0, 2, 2);
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
//            /* Calculate the coordinates for the face, so its centered on the camera. */
//            final int x = (CAMERA_WIDTH - this.mFaceTextureRegion.getTileWidth()) / 2;
//            final int y = (CAMERA_HEIGHT - this.mFaceTextureRegion.getTileHeight()) / 2;
//
//            /* Create the face and add it to the scene. */
//            final AnimatedSprite face = new AnimatedSprite(x, y, this.mFaceTextureRegion);
//            face.animate(100);
//            scene.attachChild(face);
//            
//            
//
//            scene.setOnSceneTouchListener(new IOnSceneTouchListener() {
//                    @Override
//                    public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
//                            if(pSceneTouchEvent.isActionDown()) {
//                                    UpdateSpriteExample.this.toggle();
//                            }
//                            return true;
//                    }
//            });
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
//    private void toggle() {
//            this.mBitmapTextureAtlas.clearTextureAtlasSources();
//            this.mToggleBox = !this.mToggleBox;
//            if(this.mToggleBox) {
//            	BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "helicopter_tiled.png", 0, 0, 2, 2);
//            } else {
//            	BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_circle_tiled.png", 0, 0, 2, 1);
//            }
//    }
//
//}
