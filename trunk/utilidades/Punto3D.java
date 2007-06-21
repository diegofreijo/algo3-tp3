package utilidades;

public class Punto3D
{
	public int x, y, z;
	
	public Punto3D(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public String toString()
	{
		return x + " " + y + " " + z;
	}
}
