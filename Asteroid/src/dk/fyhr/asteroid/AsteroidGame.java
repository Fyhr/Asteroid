package dk.fyhr.asteroid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import dk.fyhr.asteroid.screen.Space;
import dk.fyhr.gameengine.Game;
import dk.fyhr.gameengine.GameTime;

public class AsteroidGame extends Game implements MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH  = 640;
	private static final int HEIGHT = (int) Math.round( WIDTH / ( 16.0 / 9.0 ) );
	
	public Point mousePosition = new Point(0,0);
	
	private Space space;

	public void init()
	{
		space = new Space(this);
	}
	
	@Override
	protected void update( GameTime time )
	{
		space.update(time);
	}

	@Override
	protected void render( GameTime time, Graphics2D graphics )
	{
		graphics.setColor( Color.YELLOW );
		graphics.drawOval( mousePosition.x, mousePosition.y, 5, 5);

		space.render( time, graphics );
	}

	public static void main( String[] args )
	{
		AsteroidGame game  = new AsteroidGame();
		game.setPreferredSize( new Dimension( WIDTH, HEIGHT ) );
		game.setMinimumSize( new Dimension( WIDTH, HEIGHT ) );
		game.setMaximumSize( new Dimension( WIDTH, HEIGHT ) );
		
		JFrame frame = new JFrame("test");
		
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible( true );
		frame.setLayout( new BorderLayout() );
		frame.add( game );
		frame.pack();
		frame.setResizable( false );
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		frame.setCursor( Toolkit.getDefaultToolkit().createCustomCursor( cursorImg, new Point(0, 0), "blank cursor") );
		
		game.addMouseMotionListener(game);
		
		game.init();
		game.start();
	}

	@Override
	public void mouseDragged( MouseEvent e )
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved( MouseEvent e )
	{
		mousePosition = e.getPoint();
	}
}
