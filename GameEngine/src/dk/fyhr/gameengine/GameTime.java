package dk.fyhr.gameengine;

public class GameTime
{
	protected long delta = 0;
	
	private long timeOfFirstTick = System.nanoTime();
	private long timeOfLastTick  = System.nanoTime();
	
	public long getNanoSecondsSinceLastTick()
	{
		return System.nanoTime() - timeOfLastTick;
	}
	
	public long getDelta()
	{
		return delta;
	}
	
	public long getTimeElapsed()
	{
		return System.nanoTime() - timeOfFirstTick;
	}
	
	public void tick()
	{
		delta          = System.nanoTime() - timeOfLastTick;
		timeOfLastTick = System.nanoTime();
	}
}
