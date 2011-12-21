package dk.fyhr.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import dk.fyhr.asteroid.AsteroidGameComponent;
import dk.fyhr.gameengine.Game;
import dk.fyhr.gameengine.GameTime;

public class Bullet extends AsteroidGameComponent
{
	private Point2D.Double position;
	private Point2D.Double speed; 
	
	public Bullet( Game game, Double position, double rotation) 
	{
		super(game);
		
		this.position = new Point2D.Double( position.x + 10 * Math.cos( rotation ), position.y + 10 * Math.sin( rotation ) ) ;
		this.speed    = new Point2D.Double( 150 * Math.cos( rotation ), 150 * Math.sin( rotation ) );
	}

	@Override
	public void update( GameTime time )
	{
		position.x += (time.getDelta() / 1000000000.0) * speed.x;
		position.y += (time.getDelta() / 1000000000.0) * speed.y;
	}

	@Override
	public void render( GameTime time, Graphics2D graphics )
	{
		graphics.setColor( Color.CYAN );
		graphics.drawOval( (int) position.x, (int) position.y, 2, 2 );

	}
}
