package utilidades;

import java.util.LinkedList;
import java.util.List;

public class Grafico
{
	private List<Punto> puntos;
	private String nombre;
	
	public Grafico(String nombre)
	{
		puntos = new LinkedList<Punto>();
		this.nombre = nombre;
	}
	
	public void Agregar(Punto p)
	{
		puntos.add(p);
	}
	
	public String Nombre()
	{
		return this.nombre;
	}
	
	public String GenerarDat()
	{
		String ret = "";
		for(Punto p : puntos)
		{
			ret += p.toString() + "\n";
		}
		
		return ret; 
	}
}
