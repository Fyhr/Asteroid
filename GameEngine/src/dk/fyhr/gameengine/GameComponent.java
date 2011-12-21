package dk.fyhr.gameengine;

public abstract class GameComponent implements IDrawable
{
	public Game game;
	
	public GameComponent( Game game )
	{
		this.game = game;
	}
}
