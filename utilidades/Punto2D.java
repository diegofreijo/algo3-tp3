package utilidades;

public class Punto2D implements Punto
{
	public int x, y;
	
	public Punto2D(int x, int y)
    {
		this.x = x;
		this.y = y;
    }
	
	public String toString()
	{
		return x + " " + y;
	}
}
