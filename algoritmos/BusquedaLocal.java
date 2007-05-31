package algoritmos;

import java.util.ArrayList;
import java.util.List;

import utilidades.Eje;
import utilidades.Grafo;

public abstract class BusquedaLocal
{
	public static List<Integer> Ejecutar(Grafo g)
	{
		List<Integer> solucion;
		solucion = ConstruirSolucionInicial(g);
		
		while(ExisteVecinoMejor(solucion, g))
		{
			System.out.println("=========== Habia mejor =============");
			solucion = VecinoMejor(solucion, g);
		}
		System.out.println("=========== NO Habia mejor =============");
		
		return solucion;
	}
	
	private static List<Integer> ConstruirSolucionInicial(Grafo g)
    {
		List<Integer> solucion = new ArrayList<Integer>(g.DameNodos());
		
	    for(Eje e : g.DameEjes())
	    {
	    	if(!solucion.contains(e.x) && !solucion.contains(e.y))
	    	{
	    		solucion.add(e.x);
	    	}
	    }
	    
	    return solucion;
    }

	private static boolean ExisteVecinoMejor(List<Integer> solucion, Grafo g)
    {
	    return VecinoMejor(solucion, g) != null;
    }
	
	private static List<Integer> VecinoMejor(List<Integer> solucion, Grafo g)
    {
		// Armo la lista de nodos que no estan en la solucion
		List<Integer> demas_nodos = new ArrayList<Integer>(g.DameNodos());
		for(int i = 1; i <= g.DameNodos(); ++i)
		{
			if(!solucion.contains(i))
			{
				demas_nodos.add(i);
			}
		}
		
		// Voy armando vecinos hasta encontrar uno que tenga menor objetivo. Voy agregando de a UN nodo. Verifico
		// tambien si no es mejor sacando uno y sin poner nada
		List<Integer> vecino = null;		
		for(int sacar = 0; sacar < solucion.size(); ++sacar)
		{
			vecino = new ArrayList<Integer>(solucion);
			System.out.println("Sol por ahora: " + vecino);
			vecino.remove(sacar);
			if(EsRecubrimiento(vecino, g))
			{
				if(Objetivo(vecino) < Objetivo(solucion))
				{
					System.out.println("Elegi (sin agregar): " + vecino);
					return vecino;
				}
				else
				{
					System.out.println("Era recubrimiento pero no era mejor: " + vecino + " a " + solucion);
				}
			}
			else
			{
				System.out.println("No era recubrimiento: " + vecino);
			}
			
			for(int poner = 0; poner < demas_nodos.size(); ++poner)
			{
				vecino.add(demas_nodos.get(poner));
				if(EsRecubrimiento(vecino, g))
				{
					if(Objetivo(vecino) < Objetivo(solucion))
					{
						System.out.println("Elegi (agregando): " + vecino);
						return vecino;
					}
					else
					{
						System.out.println("Era recubrimiento pero no era mejor: " + vecino + " a " + solucion);
					}
				}
				else
				{
					System.out.println("No era recubrimiento: " + vecino);
					vecino.remove(demas_nodos.get(poner));
				}
			}
		}
		
	    return null;
    }

	// Funcion objetivo
	private static int Objetivo(List<Integer> solucion)
    {
	    return solucion.size();
    }

	private static boolean EsRecubrimiento(List<Integer> solucion, Grafo g)
    {
		for(Eje e : g.DameEjes())
		{
			if(!solucion.contains(Integer.valueOf(e.x)) && !solucion.contains(Integer.valueOf(e.y)))
			{
				return false;
			}
		}
		
	    return true;
    }

}
