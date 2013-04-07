package com.skyWords.runtime.scene;

import org.andengine.util.adt.pool.GenericPool;

public class TilePool extends GenericPool{
	
	public static TilePool instance;
	
	public static TilePool sharedTilePool()
	{
		if(instance == null)
			instance = new TilePool();
		return instance;
	}
	
	private TilePool()
	{
		super();
	}
	
	protected Tile onAllocatePoolItem()
	{
		return new Tile();
	}
	
	protected void onHandleObtainItem(Tile tItem)
	{
		tItem.init();
	}
	
	protected void onHandleRecycleItem(final Tile t)
	{
		t.sprite.setVisible(false);
		t.sprite.detachSelf();
		t.clean();
		/*
		t.sprite.clearEntityModifiers();
		t.sprite.clearUpdateHandlers();
		t.sprite.setVisible(false);
		t.sprite.detachSelf();
		*/
	}

}
