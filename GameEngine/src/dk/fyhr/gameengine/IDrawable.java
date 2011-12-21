package dk.fyhr.gameengine;

import java.awt.Graphics2D;

public interface IDrawable
{
	void update( GameTime time );
	void render( GameTime time, Graphics2D graphics );
}
