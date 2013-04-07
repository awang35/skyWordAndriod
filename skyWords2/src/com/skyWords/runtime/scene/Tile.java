package com.skyWords.runtime.scene;

//
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.primitive.Rectangle;
import com.skyWords.runtime.SkyWordsActivity;

public class Tile {
	public Rectangle sprite;
	public char letter; // eventually we will need this
	
	Tile()
	{
		sprite = new Rectangle(0, 0, 30, 30, SkyWordsActivity.getSharedInstance().getVertexBufferObjectManager());
		sprite.setColor((float)Math.random(),(float)Math.random(), (float)Math.random());
		//sprite.setColor(0.09904f, 0.8574f, 0.1786f);
		init();
	}
	
	public void init()
	{
		sprite.registerEntityModifier(new LoopEntityModifier(new RotationModifier(5, 0, 360))); // make the tile rotate
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
			return false;
		}
	}

}
