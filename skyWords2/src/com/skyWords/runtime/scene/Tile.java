package com.skyWords.runtime.scene;

//
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.primitive.Rectangle;

import android.graphics.Color;

import com.skyWords.runtime.SkyWordsActivity;

public class Tile {
	public Rectangle sprite;
	public char letter; // eventually we will need this
	
	Tile()
	{
		sprite = new Rectangle(0, 0, 80, 80, SkyWordsActivity.getSharedInstance().getVertexBufferObjectManager());
		sprite.setColor((float)Math.random(),(float)Math.random(), (float)Math.random());
		//sprite.setColor(0.09904f, 0.8574f, 0.1786f);
		init();
	}
	
	public void init()
	{
		//sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(5, 0, 360))); // make the tile rotate
		//MoveYModifier mod = new MoveYModifier(1.5f, sprite.getY(),-sprite.getHeight()); how to get tiles to move down (fall)
	}
	
	public void clean()
	{
		sprite.clearEntityModifiers();
		sprite.clearUpdateHandlers();
	}
	// checking if tile got hit
	public boolean gotHit()
	{
		synchronized(this)
		{
			sprite.setColor(0.0f, 0.0f, 0.0f);
			return false;
		}
	}
	
	public boolean hit(float xhit, float yhit)
	{
		if(sprite.contains(xhit, yhit))
		{
			//sprite.setColor(0.0f, 0.0f, 0.0f);
			sprite.setVisible(false);
			return true;
		}
		else
			return false;
	}

}