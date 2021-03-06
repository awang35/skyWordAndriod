package com.skyWords.runtime;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.util.DisplayMetrics;

import com.skyWords.manager.ResourceManager;
import com.skyWords.manager.SceneManager;


public class SkyWordsActivity  extends BaseGameActivity{
	// ====================================================
	// VARIABLES
	// ====================================================
	// Declare a Camera object for our activity
	private Camera mCamera;
	// Declare a Scene object for our activity
	private Scene mScene;
	public float CAMERA_WIDTH = 800;
	public float CAMERA_HEIGHT = 1280;
	private ResourceManager resourcesManager;

	public EngineOptions onCreateEngineOptions()
	{
		 DisplayMetrics dm = new DisplayMetrics();
	      this.getWindowManager().getDefaultDisplay().getMetrics(dm);
	      this.CAMERA_WIDTH = dm.widthPixels;
	      this.CAMERA_HEIGHT = dm.heightPixels;
		mCamera = new Camera (0,0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(),
				mCamera);
		// Enable sounds.
		engineOptions.getAudioOptions().setNeedsSound(true);
		// Enable music.
		engineOptions.getAudioOptions().setNeedsMusic(true);
		// Turn on Dithering to smooth texture gradients.
		engineOptions.getRenderOptions().setDithering(true);
		//Disable the display from going to sleep from inactive use
		engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);

		// Return the engineOptions object, passing it to the engine
		return engineOptions;
	}

	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException
	{
		ResourceManager.prepareManager(mEngine, this, mCamera, getVertexBufferObjectManager());
	    resourcesManager = ResourceManager.getInstance();
	    pOnCreateResourcesCallback.onCreateResourcesFinished();
	}

	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException
	{
		// SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
		SceneManager.getInstance().createMenuScene();
	}

	public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException
	{
		  mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
		    {
		           public void onTimePassed(final TimerHandler pTimerHandler) 
		           {
		        	   mEngine.unregisterUpdateHandler(pTimerHandler);
		               SceneManager.getInstance().createMenuScene();
		          }
		    }));
		    pOnPopulateSceneCallback.onPopulateSceneFinished();
	}
	@Override
	protected void onDestroy()
	{
	    super.onDestroy();
	  //  System.exit(0);
	}
}
