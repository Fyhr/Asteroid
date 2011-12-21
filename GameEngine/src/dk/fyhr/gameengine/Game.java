package dk.fyhr.gameengine;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private boolean       isRunning = false;
	private BufferedImage image     = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB );

	protected abstract void update( GameTime time );
	protected abstract void render( GameTime time, Graphics2D graphics );
	
	@Override
	public void run()
	{
		GameTime time = new GameTime( );
		
		while( isRunning )
		{
			time.tick();
			
			update( time );
			render( time );
			
			try
			{
				int sleep = (int) ( ( 1000 / 60.0 ) - ( time.getNanoSecondsSinceLastTick() / 1000000.0 ) );

				if( sleep <= 0 )
					continue;
				
				Thread.sleep( sleep );
			}
			catch( InterruptedException e )
			{
				e.printStackTrace();
			}
		}
	}
	
	protected void render( GameTime time )
	{
		BufferStrategy bs = getBufferStrategy();
		
		if( bs == null )
		{
			createBufferStrategy(2);
			return;
		}

		bs.getDrawGraphics().drawImage( image, 0, 0, getWidth(), getHeight(), null );
		
		render( time, (Graphics2D) bs.getDrawGraphics() );
		
		bs.getDrawGraphics().dispose();			
		bs.show();
	}
	
	public void start()
	{
		if( isRunning ) return;
		
		isRunning = true;
		
		new Thread( this ).start();
	}
	
	public void stop()
	{
		isRunning = false;
	}
}
