package utilidades;

import java.util.ArrayList;
import java.util.List;

public class Recubrimiento implements Comparable<Recubrimiento>
{
	public List<Integer> nodos;
	public Estadisticas e;
	
	public Recubrimiento(Integer cant_nodos, Estadisticas est)
	{
		e = est;
		nodos = new ArrayList<Integer>(cant_nodos); e.i+=cant_nodos;
	}
	
	public Recubrimiento(Recubrimiento original)
	{
		e = original.e;
		nodos = new ArrayList<Integer>(original.nodos); e.i+=original.nodos.size();
	}
	
	/*public Recubrimiento(List<Integer> listanodos, Estadisticas est)
	{		
		e = est;
		nodos = listanodos;
	}*/
	
	public boolean EsRecubrimiento(Grafo g)
    {
		for(Eje eje : g.DameEjes())
		{
			e.i+=3;
			if(!nodos.contains(Integer.valueOf(eje.x)) && !nodos.contains(Integer.valueOf(eje.y)))
			{
				++e.i;
				return false;
			}
		}
		
		++e.i;
	    return true;
    }
	
	public String toString()
	{
		return nodos.toString();
	}
	
	public Integer Tamano()
	{
		++e.i;
		return nodos.size();
	}

	public int compareTo(Recubrimiento r)
	{
		return (this.nodos.equals(r.nodos) ? 0 : -1);
	}
}
