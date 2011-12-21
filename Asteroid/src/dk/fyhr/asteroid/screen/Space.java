package dk.fyhr.asteroid.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import dk.fyhr.asteroid.AsteroidGameComponent;
import dk.fyhr.asteroid.entity.Ship;
import dk.fyhr.gameengine.Game;
import dk.fyhr.gameengine.GameTime;

public class Space extends AsteroidGameComponent
{
	private int  lastUpdated = 0;
	private int  fps         = 0;
	private Ship ship        = new Ship( game );
	
	public Space(Game game)
	{
		super(game);
	}
	
	@Override
	public void update( GameTime time )
	{
		int tmpLastUpdated = (int) ( time.getTimeElapsed() / (500 * 1000 * 1000) ); // 10hz

		if( tmpLastUpdated > lastUpdated )
			fps = (int) ( 1000000000 / time.getDelta() );
		
		lastUpdated = tmpLastUpdated;
		
		ship.update(time);
	}
	
	@Override
	public void render( GameTime time, Graphics2D graphics )
	{
		String text = String.format( "FPS: %1d\n", fps );
		graphics.setColor( new Color( 255,255,255 ) );
		graphics.setFont( new Font( "OSAKA", Font.BOLD, 10 ) );
		graphics.drawString( text, (int) (640 - graphics.getFontMetrics().getStringBounds( text, graphics ).getMaxX()), 15 );
		ship.render(time, graphics);
	}
}
