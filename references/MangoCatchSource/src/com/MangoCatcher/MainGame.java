
package com.MangoCatcher;

import java.util.ArrayList;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.IBackground;
import org.anddev.andengine.entity.scene.transition.FadeTransitionScene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.modifier.IModifier;

public class MainGame extends BaseGameActivity implements IOnSceneTouchListener {   
	public static MainGame _main;	
    public static final int CAMERA_WIDTH = 480;
    public static final int CAMERA_HEIGHT = 800;    
    private static Camera _camera;
	private TextureRegion _basketTextureRegion;
	private TextureRegion _mangoTextureRegion;	
	private Sprite _basketSprite;
	private ArrayList<Sprite> _mangoesArray = new ArrayList<Sprite>();	
	private int _score = 0;
	
    public Engine onLoadEngine() {
    	_main = this;
    	MainGame._camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);        
        Engine _engine = new Engine(new EngineOptions(true, ScreenOrientation.PORTRAIT,new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),MainGame._camera));
        return _engine;
    }
    
    /*
     * loading the resources.
     * @see org.anddev.andengine.ui.IGameInterface#onLoadResources()
     */

    public void onLoadResources() {		
    	Texture texture = new Texture(256, 128);    	
    	_basketTextureRegion   = TextureRegionFactory.createFromAsset(texture, this, "gfx/basket.png", 0, 0);
    	_mangoTextureRegion = TextureRegionFactory.createFromAsset(texture, this, "gfx/mango.png", 125, 0);    	
    	this.mEngine.getTextureManager().loadTexture(texture);
    	this.onLoadSprites();
    }

    /*
     * Loading Sprites of textures
     */
    public void onLoadSprites() {    	
    	_basketSprite = new Sprite(getScreenCenterX(_basketTextureRegion.getWidth()), CAMERA_HEIGHT - _basketTextureRegion.getHeight(), _basketTextureRegion);
    }

    /*
     * Updating the mangoes positions.
     * Adding mangoes in the scene
     * @see org.anddev.andengine.ui.IGameInterface#onLoadScene()
     */
	@Override
    public Scene onLoadScene() {
        this.mEngine.registerUpdateHandler(new FPSLogger());
        final Scene scene = new Scene(1);               
        scene.getTopLayer().addEntity(_basketSprite);        
        scene.registerUpdateHandler(new IUpdateHandler() {
        	private long lastRaindropAdd = 0;
			@Override
        	public void onUpdate(final float pSecondsElapsed) {        		
				upDateMangoPoistion(scene);
        		if (System.currentTimeMillis() - lastRaindropAdd  > 3000) {
        			lastRaindropAdd = System.currentTimeMillis();        			
        			addMangoes(scene);
        		}
            }

			@Override
			public void reset() {

			}
        });
        
        final Scene scene2 = new Scene(1); 
        scene2.setBackground(new IBackground() {
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDraw(GL10 pGL, Camera pCamera) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setColor(float pRed, float pGreen, float pBlue, float pAlpha) {
				// TODO Auto-generated method stub
				this.setColor(255f, 255f, 255f);
			}
			
			@Override
			public void setColor(float pRed, float pGreen, float pBlue) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean removeBackgroundModifier(
					IModifier<IBackground> pBackgroundModifier) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void clearBackgroundModifiers() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void addBackgroundModifier(IModifier<IBackground> pBackgroundModifier) {
				// TODO Auto-generated method stub
				
			}
		});
        
        scene.setOnSceneTouchListener(this);  
        
        final FadeTransitionScene fsence = new FadeTransitionScene(500000, scene2, scene, 1f, 1f, 1f);
        return fsence;
    }
	/*
     * Updating the mangoes positions.
     */
	private void upDateMangoPoistion(Scene scene){
		int size = _mangoesArray.size();
		for (int i = 0; i < size; i++) 
		{
			Sprite raindrop = _mangoesArray.get(i);        			
			raindrop.setPosition(raindrop.getX(), raindrop.getY() + 5); 		
			if (raindrop.collidesWith(_basketSprite)) {				
				_score++;
				System.out.println("Score: " + _score);
				synchronized(raindrop) {
					size--;
					_mangoesArray.remove(i);
					scene.getTopLayer().removeEntity(raindrop);
				}
			}
			
			else if (raindrop.getY() > CAMERA_HEIGHT) {			
				_score--;
				System.out.println("Score: " + _score);
				synchronized(raindrop) {
					size--;
					_mangoesArray.remove(i);
					scene.getTopLayer().removeEntity(raindrop);
				}
			}
		}
	}
	

	/*     
     * Adding mangoes in the scene
     */
	public void addMangoes(Scene scene){
		 int START = 1;
		 int END = 300;
		 Random random = new Random();        			    
		 int randomInt = showRandomInteger(START, END, random);
		Sprite raindrop = getMango(randomInt, 0);
		_mangoesArray.add(raindrop);
		scene.getTopLayer().addEntity(raindrop);
	}
	
	/*     
     * Getting the random x position for mangoes.
     */
	
	public int  showRandomInteger(int aStart, int aEnd, Random aRandom){
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	     return randomNumber;
	  }

	/*     
     * Getting the Sprite for mangoes.
     */
	
	public Sprite getMango(float x, float y) {
		return (new Sprite(x, y, _mangoTextureRegion.clone()));
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.anddev.andengine.ui.IGameInterface#onLoadComplete()
	 */
	@Override
	public void onLoadComplete() {
		
	}
      
	/*
	 * On touch moving the basket
	 * @see org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener#onSceneTouchEvent(org.anddev.andengine.entity.scene.Scene, org.anddev.andengine.input.touch.TouchEvent)
	 */
	@Override
	public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
		this.runOnUpdateThread(new Runnable() {
			@Override
			public void run() {
				float touchX = pSceneTouchEvent.getX();
				_basketSprite.setPosition(touchX - _basketSprite.getWidth()/2, _basketSprite.getY());
			}
		});
		return true;
	}
	/*     
     * Getting the Screens CenterX.
     */
	public float getScreenCenterX(float width) {
		return (CAMERA_WIDTH / 2) - width / 2;
	}
	/*     
     * Getting the Screens CenterY.
     */
	public float getScreenCenterY(float height) {
		return (CAMERA_HEIGHT / 2) - height / 2;
	}
}