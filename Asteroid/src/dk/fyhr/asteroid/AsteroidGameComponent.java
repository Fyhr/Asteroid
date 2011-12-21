package dk.fyhr.asteroid;

import java.awt.Graphics2D;

import dk.fyhr.gameengine.Game;
import dk.fyhr.gameengine.GameComponent;
import dk.fyhr.gameengine.GameTime;

public abstract class AsteroidGameComponent extends GameComponent
{	
	public abstract void update( GameTime time );
	public abstract void render( GameTime time, Graphics2D graphics );
	
	public AsteroidGameComponent(Game game)
	{
		super(game);
	}
	
	public AsteroidGame getGame( )
	{
		return (AsteroidGame) game;
	}
}
