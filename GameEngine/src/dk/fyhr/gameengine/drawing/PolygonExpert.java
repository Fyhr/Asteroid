package dk.fyhr.gameengine.drawing;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

public class PolygonExpert
{
	public static Polygon2D rotateDegrees( Polygon2D polygon, double r )
	{
		AffineTransform rotate = AffineTransform.getRotateInstance( (r * 2 * Math.PI) / 360, 
																	polygon.getBounds().getCenterX(), 
																	polygon.getBounds().getCenterY() );
		
		PathIterator it = polygon.getPathIterator( rotate );
		Polygon2D rotatedPolygon = new Polygon2D();
		
		for( int i = 0; !it.isDone() && i < polygon.npoints; i++ )
		{
			double[] coords = new double[2];
			it.currentSegment(coords);
			
			rotatedPolygon.addPoint( (float) coords[0], (float) coords[1] );
			
			it.next();
		}
		
		return rotatedPolygon;
	}
	
	public static Polygon2D rotateRadians( Polygon2D polygon, double r )
	{
		AffineTransform rotate = AffineTransform.getRotateInstance( r, 
																	polygon.getBounds().getCenterX(), 
																	polygon.getBounds().getCenterY() );
		
		PathIterator it = polygon.getPathIterator( rotate );
		Polygon2D rotatedPolygon = new Polygon2D();
		
		for( int i = 0; !it.isDone() && i < polygon.npoints; i++ )
		{
			double[] coords = new double[2];
			it.currentSegment(coords);
			
			rotatedPolygon.addPoint( (float) coords[0], (float) coords[1] );
			
			it.next();
		}
		
		return rotatedPolygon;
	}
	
	public static Polygon2D translate( Polygon2D polygon, double deltaX, double deltaY )
	{
		AffineTransform rotate = AffineTransform.getTranslateInstance( deltaX, deltaY );
		
		PathIterator it = polygon.getPathIterator( rotate );
		Polygon2D translated = new Polygon2D();
		
		for( int i = 0; !it.isDone() && i < polygon.npoints; i++ )
		{
			double[] coords = new double[2];
			it.currentSegment(coords);
			
			translated.addPoint( (float) coords[0], (float) coords[1] );
			
			it.next();
		}
		
		return translated;
	}
}
