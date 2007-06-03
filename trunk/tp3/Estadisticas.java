package tp3;

import java.util.LinkedList;
import java.util.List;

import utilidades.Recubrimiento;

public class Estadisticas
{
	private String nombre_algoritmo;
	private List<Punto> resultados = new LinkedList<Punto>();						// Lista de resultados parciales del grafico de este algo
	private List<Recubrimiento> recubrimientos = new LinkedList<Recubrimiento>();	// Lista de recubrimientos generados por este algo
	public long i;																	// Cantidad de instrucciones por el momento para este grafo (al final se agregan a resultados)
	
	public Estadisticas(String nombre_algoritmo)
	{
		this.nombre_algoritmo = nombre_algoritmo;
		this.i = 0;
	}
	
	// Guarda un resultado
	public void Almacenar(Punto r)
    {
		resultados.add(r);
    }
	
	public String NombreAlgoritmo()
	{
		return nombre_algoritmo;
	}
	
	// Devuelve los puntos del grafico ya listos para ser lanzados al archivo
	public String Puntos()
	{
		String ret = "";
		
		for(Punto r : resultados)
		{
			ret += r.x + " " + r.y + "\n";
		}
		
		return ret;
	}
	
	// Devuelve los recubrimientos ya listos para ser lanzados al archivo
	public String Recubrimientos()
	{
		String ret = "";
		
		for(Recubrimiento recubrimiento : recubrimientos)
		{
			ret += recubrimiento.Tamano() + "\n";
		}
		
		return ret;
	}

	// Guarda en this.nodos el resultado actual y resetea el contador
	public void GuardarResultado(int x)
    {
	    this.resultados.add(new Punto(x,i));
	    this.i = 0;
    }

	// Almacena el recubrimiento generado para el grafo actual
	public void GuardarRecubrimiento(Recubrimiento recubrimiento)
    {
		recubrimientos.add(recubrimiento);	    
    }
}
