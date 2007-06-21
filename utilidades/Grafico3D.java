package utilidades;

import java.util.LinkedList;
import java.util.List;

public class Grafico3D
{
	private List<Punto3D> puntos;
	
	public Grafico3D()
	{
		puntos = new LinkedList<Punto3D>();
	}
	
	public void Agregar(Punto3D p)
	{
		puntos.add(p);
	}
	
	public String toString()
	{
		String ret = "";
		for(Punto3D p : puntos)
		{
			ret += p.toString() + "\n";
		}
		return ret; 
	}
}
