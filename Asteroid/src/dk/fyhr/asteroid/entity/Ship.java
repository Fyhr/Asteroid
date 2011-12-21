package dk.fyhr.asteroid.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import dk.fyhr.asteroid.AsteroidGameComponent;
import dk.fyhr.gameengine.Game;
import dk.fyhr.gameengine.GameTime;
import dk.fyhr.gameengine.drawing.Polygon2D;
import dk.fyhr.gameengine.drawing.PolygonExpert;

public class Ship extends AsteroidGameComponent implements MouseListener, KeyListener
{
	private Point2D.Double    position;
	private Point2D.Double    speed         = new Point2D.Double(0,0);
	private Polygon2D         ship          = new Polygon2D( new int[]{0,20,0}, new int[]{0,8,16}, 3);
	private ArrayList<Bullet> bullets       = new ArrayList<Bullet>();
	private double            rotation      = 0.0;
	private boolean           thrust        = false;
	private boolean           right         = false;
	private boolean           left          = false;
	private boolean           reverse       = false;

	public Ship(Game game)
	{
		super(game);
		
		position = new Point2D.Double( game.getWidth() / 2 - 10, game.getHeight() / 2 - 8 );
		
		game.addMouseListener(this);
		game.addKeyListener(this);
	}
	
	@Override
	public void update( GameTime time )
	{
		// Set rotation to angle between Y-axis and mouse position
		rotation = Math.atan2( getGame().mousePosition.y - position.y, getGame().mousePosition.x - position.x );
		
		speed.y = 0;
		speed.x = 0;
		
		if( thrust )
		{
			speed.y += 90 * Math.sin( rotation );
			speed.x += 90 * Math.cos( rotation );
		}
		
		if( reverse )
		{
			speed.y -= 90 * Math.sin( rotation );
			speed.x -= 90 * Math.cos( rotation );
		}
		
		if( right )
		{
			speed.y += 90 * Math.sin( rotation + ( 90 * Math.PI / 180 ) );
			speed.x += 90 * Math.cos( rotation + ( 90 * Math.PI / 180 ) );
		}
		
		if( left )
		{
			speed.y += 90 * Math.sin( rotation - ( 90 * Math.PI / 180 ) );
			speed.x += 90 * Math.cos( rotation - ( 90 * Math.PI / 180 ) );
		}
		
		position.x += (time.getDelta() / 1000000000.0) * speed.x;
		position.y += (time.getDelta() / 1000000000.0) * speed.y;
		
		Polygon2D shipToDraw = getShipToDraw();
		
		for( int i = 0; i < shipToDraw.npoints; i++)
		{
			if( game.getBounds().getMaxX() < shipToDraw.xpoints[i] || game.getBounds().getMinX() > shipToDraw.xpoints[i] )
				speed.x *= -1;
			
			if( game.getBounds().getMaxY() < shipToDraw.ypoints[i] || game.getBounds().getMinY() > shipToDraw.ypoints[i] )
				speed.y *= -1;
		}
		
		synchronized( bullets )
		{
			for( Bullet bullet : bullets )
				bullet.update(time);
		}
	}

	@Override
	public void render( GameTime time, Graphics2D graphics )
	{
		graphics.setColor( Color.green );
		graphics.draw( getShipToDraw() );

		synchronized( bullets )
		{
			for( Bullet bullet : bullets )
				bullet.render( time, graphics );
		}
	}
	
	private Polygon2D getShipToDraw()
	{
		Polygon2D shipToDraw = PolygonExpert.rotateRadians( ship, rotation );
		
		shipToDraw = PolygonExpert.translate( shipToDraw, position.x -8, position.y -5 );
		
		return shipToDraw;
	}

	@Override
	public void mouseClicked( MouseEvent arg0 ) {}

	@Override
	public void mouseEntered( MouseEvent arg0 ) {}

	@Override
	public void mouseExited( MouseEvent arg0 ) {}

	@Override
	public void mousePressed( MouseEvent arg0 ) {}

	@Override
	public void mouseReleased( MouseEvent arg0 ) {}

	@Override
	public void keyPressed( KeyEvent e )
	{
		if( e.getKeyCode() == KeyEvent.VK_W )
			thrust = true;
		
		if( e.getKeyCode() == KeyEvent.VK_A )
			left = true;
		
		if( e.getKeyCode() == KeyEvent.VK_D )
			right = true;
		
		if( e.getKeyCode() == KeyEvent.VK_S )
			reverse = true;
	}
	
	@Override
	public void keyReleased( KeyEvent e )
	{
		if( e.getKeyCode() == KeyEvent.VK_W )
			thrust = false;
		
		if( e.getKeyCode() == KeyEvent.VK_A )
			left = false;
		
		if( e.getKeyCode() == KeyEvent.VK_D )
			right = false;
		
		if( e.getKeyCode() == KeyEvent.VK_S )
			reverse = false;
	}

	@Override
	public void keyTyped( KeyEvent e )
	{
		if( e.getKeyChar() == ' ' )
			synchronized( bullets )
			{
				bullets.add( new Bullet( game, position, rotation ) );
			}
	}
}
