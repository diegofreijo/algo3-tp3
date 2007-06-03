package algoritmos;

import java.util.ArrayList;
import java.util.List;

import tp3.Estadisticas;
import utilidades.Eje;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class BusquedaLocal
{
	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g, Estadisticas est)
	{
		e = est;
		Recubrimiento solucion = ConstruirSolucionInicial(g); ++e.i;
		Recubrimiento mejor_vecino;
		
		while((mejor_vecino = VecinoMejor(solucion, g)) != null)
		{
			++e.i;
			solucion = mejor_vecino; ++e.i;
		}
		
		return solucion;
	}
	
	private static Recubrimiento ConstruirSolucionInicial(Grafo g)
    {
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(), e); ++e.i;
		
	    for(Eje eje : g.DameEjes())
	    {
	    	++e.i;
	    	++e.i;
	    	if(!solucion.nodos.contains(eje.x) && !solucion.nodos.contains(eje.y))
	    	{
	    		solucion.nodos.add(eje.x); ++e.i;
	    	}
	    }
	    
	    return solucion;
    }

	private static Recubrimiento VecinoMejor(Recubrimiento solucion, Grafo g)
    {
		// Armo la lista de nodos que no estan en la solucion
		List<Integer> demas_nodos = new ArrayList<Integer>(g.DameNodos()); e.i+=g.DameNodos();
		for(int i = 1; i <= g.DameNodos(); ++i)
		{
			++e.i;
			if(!solucion.nodos.contains(i))
			{
				++e.i;
				demas_nodos.add(i); ++e.i;
			}
		}
		
		// Voy armando vecinos hasta encontrar uno que tenga menor objetivo. Voy agregando de a UN nodo. Verifico
		// tambien si no es mejor sacando uno y sin poner nada
		Recubrimiento vecino = null; ++e.i;		
		for(int sacar = 0; sacar < solucion.nodos.size(); ++sacar)
		{
			++e.i;
			vecino = new Recubrimiento(solucion); ++e.i;
			vecino.nodos.remove(sacar); ++e.i;
			if(vecino.EsRecubrimiento(g))
			{
				++e.i;
				if(Objetivo(vecino) < Objetivo(solucion))
				{
					++e.i;
					return vecino;
				}
			}
			
			for(int poner = 0; poner < demas_nodos.size(); ++poner)
			{
				++e.i;
				vecino.nodos.add(demas_nodos.get(poner)); ++e.i;
				if(vecino.EsRecubrimiento(g))
				{
					++e.i;
					if(Objetivo(vecino) < Objetivo(solucion))
					{
						++e.i;
						return vecino;
					}
				}
				else
				{
					++e.i;
					vecino.nodos.remove(demas_nodos.get(poner));
				}
			}
		}
		
		++e.i;
	    return null;
    }

	private static int Objetivo(Recubrimiento solucion)
    {
		++e.i;
	    return solucion.nodos.size();
    }
}
