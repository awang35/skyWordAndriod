package com.skyWords.runtime.scene;

import java.util.Iterator;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.color.Color;

import com.badlogic.gdx.math.Vector2;
import com.skyWords.runtime.SkyWordsActivity;
import com.skyWords.manager.BaseScene;
import com.skyWords.manager.SceneManager;
import com.skyWords.manager.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener{
	private HUD gameHUD;
	private Text scoreText;
	private int score = 0;
	private PhysicsWorld physicsWorld;
	// juan
	public Ship ship;
	Camera mCamera;
	boolean isGreen; // to test 
	public GameScene()
	{
		//setBackground(...
		mCamera = SkyWordsActivity.getSharedInstance().mCamera;
		ship = Ship.getSharedInstance();
		attachChild(ship.sprite);
		setOnSceneTouchListener(this);
		//
		registerUpdateHandler(new GameLoopUpdateHandler());
		attachChild(new TileLayer(8));
		isGreen = true;
	}
	
	public void moveShip()
	{
		ship.moveShip();
	}
	
	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
	{
		synchronized(this)
		{
			if(pSceneTouchEvent.isActionDown())
			{
				Iterator<Tile> it = TileLayer.getIterator();
				while(it.hasNext())
				{
					Tile t = it.next();
					if(t.hit(pSceneTouchEvent.getX(), pSceneTouchEvent.getY()))
					{
						//it.remove();
						//break;
					}
				}
			}
			/*
			if(pSceneTouchEvent.isActionDown()) // so it doesnt change if you press and move finger
			{				
				if(isGreen)
				{
					setBackground(new Background(Color.CYAN));
					isGreen = false;
				}
				else
				{
					setBackground(new Background(Color.GREEN));
					isGreen = true;
				}
			}
			*/
		}
		return true;
	}
	
	public void cleaner()
	{

	}
	
	private void addToScore(int i)
	{
		score += i;
		scoreText.setText("Score: " + score);
	}
	private void createHUD()
	{
		gameHUD = new HUD();

		// CREATE SCORE TEXT

		scoreText = new Text(20, 420, resourcesManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
		
		scoreText.setSkewCenter(0, -100);    
		scoreText.setText("Score: 0");
		gameHUD.attachChild(scoreText);

		camera.setHUD(gameHUD);
	}
	private void createBackground()
	{
	    setBackground(new Background(Color.CYAN)); // juan changed color
	}
	private void createPhysics()
	{
	    physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false); 
	    registerUpdateHandler(physicsWorld);
	}
	@Override
	public void createScene() {
		createBackground();
	    createHUD();
	    createPhysics();
	}

	@Override
	public void onBackKeyPressed() {
		// TODO Auto-generated method stub
		setBackground(new Background(Color.RED)); // registering back button press
		//SceneManager.getInstance().setScene(SceneType.SCENE_MENU);
	}

	@Override
	public SceneType getSceneType() {
		// TODO Auto-generated method stub
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
	    camera.setHUD(null);
	    camera.setCenter(400, 240);
	    // TODO code responsible for disposing scene
	    // removing all game scene objects.
	}

}
