package com.skyWords.runtime.scene;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;

import com.skyWords.runtime.SkyWordsActivity;

public class TileLayer extends Entity{
	private LinkedList tiles;
	public static TileLayer instance;
	public int tileCount;
	
	public TileLayer()
	{
		tiles = new LinkedList();
		instance = this;
		tileCount = (int)(Math.random()*5 + 1);
		restart();
	}
	
	public TileLayer(int x)
	{
		tiles = new LinkedList();
		instance = this;
		tileCount = x;
		restart();
	}
	
	public void restart()
	{
		tiles.clear();
		clearEntityModifiers();
		clearUpdateHandlers();
		for(int i=0; i<tileCount; i++)
		{
			Tile t = (Tile)TilePool.sharedTilePool().obtainPoolItem();
			float finalPosX = (i%6) * 4 * t.sprite.getWidth();
			float finalPosY = ((int) (i/6))* t.sprite.getHeight()*2;
			
			Random r = new Random();
			t.sprite.setPosition(r.nextInt(2) == 0 ? -t.sprite.getWidth()*3 : SkyWordsActivity.CAMERA_WIDTH + t.sprite.getWidth()*3, (r.nextInt(5) +1) * t.sprite.getHeight());
			t.sprite.setVisible(true);
			
			attachChild(t.sprite);
			t.sprite.registerEntityModifier(new MoveModifier(2, t.sprite.getX(), finalPosX, t.sprite.getY(), finalPosY));
			
			tiles.add(t);
		}
	}
	
	public void purge()
	{
		detachChildren();
		/*for(Tile t:tiles)
		{
			TilePool.sharedTilePool().recyclePoolItem(t);
		}
		*/
		tiles.clear();
	}
	
	public static void purgeAndRestart()
	{
		instance.purge();
		instance.restart();
	}
	
	@Override
	public void onDetached()
	{
		purge();
		super.onDetached();
	}
	
	public TileLayer getSharedInstance()
	{
		return instance;
	}
	
	public static boolean isEmpty()
	{
		if(instance.tiles.size() == 0)
			return true;
		else
			return false;
	}
	
	public static Iterator getIterator()
	{
		return instance.tiles.iterator();
	}
	
	

}
