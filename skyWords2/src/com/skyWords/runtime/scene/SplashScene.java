package com.skyWords.runtime.scene;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.skyWords.manager.BaseScene;
import com.skyWords.manager.SceneManager.SceneType;

public class SplashScene extends BaseScene{
	private Sprite splash; 
	@Override
	    public void createScene()
	    {
		splash = new Sprite(0, 0, resourcesManager.splash_region, vbom)
		{
		    @Override
		    protected void preDraw(GLState pGLState, Camera pCamera) 
		    {
		       super.preDraw(pGLState, pCamera);
		       pGLState.enableDither();
		    }
		};
		        
		//splash.setScale(1.5f);
		splash.setScale(3.5f);
		//splash.setPosition(400, 240);
		splash.setPosition(275, 500);
		attachChild(splash);
	    }

	    @Override
	    public void onBackKeyPressed()
	    {

	    }

	    @Override
	    public SceneType getSceneType()
	    {
	    	return SceneType.SCENE_SPLASH;
	    }

	    @Override
	    public void disposeScene()
	    {
	    	splash.detachSelf();
	        splash.dispose();
	        this.detachSelf();
	        this.dispose();
	    }
}
