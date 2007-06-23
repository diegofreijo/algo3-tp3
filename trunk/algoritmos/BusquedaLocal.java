package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utilidades.Eje;
import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class BusquedaLocal
{
	private static Estadisticas e;
	private static ParametrosBL parametros;
	
	public static Recubrimiento Ejecutar(Grafo g, ParametrosBL par, Estadisticas est)
	{
		e = est;
		parametros = par;
		
		Recubrimiento solucion = ConstruirSolucionInicial(g); ++e.i;
		//System.out.println("Sol inicial: " + solucion);
		Recubrimiento mejor_vecino;
		
		//System.out.println("==== Comienzo a buscar mejores vecinos ==== ");
		
		while((mejor_vecino = VecinoMejor(solucion, parametros.porcentaje_cuantos_saco, parametros.porcentaje_cuantos_agrego, g)) != null)
		{
			++e.i;
			//System.out.println(" *** Mejor que esta solucion " + solucion + " es " + mejor_vecino);
			solucion = mejor_vecino; ++e.i;
		}
		
		//System.out.println(" *** Solucion final: " + solucion);
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
	    
	    ++e.i;
	    return solucion;	
    }

	// Precondicion(o tambien llamado "sentido comun"): cuantos_saco > cuantos_agrego
	// IGUALDAD OBSERVACIONAL
	private static Recubrimiento VecinoMejor(Recubrimiento solucion, int porcentaje_cuantos_saco, int porcentaje_cuantos_agrego, Grafo g)
    {
		// Si la solucion actual es vacia, ya fue, no voy a conseguir nada mejor
		if(solucion.Tamano() == 0)
		{
			return null;
		}
		
		// Armo la lista de nodos que no estan en la solucion
		List<Integer> demas_nodos = new ArrayList<Integer>(g.DameNodos()); e.i+=g.DameNodos();
		for(int i = 1; i <= g.DameNodos(); ++i)
		{
			++e.i;
			if(!solucion.nodos.contains(i))
			{
				e.i+=solucion.nodos.size();
				demas_nodos.add(i); ++e.i;
			}
		}
	
		// Calculo el porcentaje de los que saco y pongo
		int cuantos_saco = porcentaje_cuantos_saco * solucion.nodos.size() / 100 + 1; ++e.i;
		int cuantos_agrego = porcentaje_cuantos_agrego * solucion.nodos.size() / 100 + 1; ++e.i;
		// Verifico no tratar de sacar mas de los que tiene la solucion o agregar mas de los que tengo para agregar
		if(cuantos_agrego > demas_nodos.size())
		{
			cuantos_agrego = demas_nodos.size();
		}
		if(cuantos_saco > solucion.nodos.size())
		{
			cuantos_saco = solucion.nodos.size();
		}
		// Verifico que no me hallan quedado iguales las cantidades que saco y agrego
		if(cuantos_agrego >= cuantos_saco)
		{
			cuantos_agrego = cuantos_saco - 1;
		}
		
		
		/*
		 *  Voy armando vecinos hasta encontrar uno que tenga menor objetivo.
		 *  Los genero sacando @cuantos_saco nodos y verificando si es un recubrimiento. 
		 *  	Si lo es, kapanga, lo devuelvo.
		 *  	Si no, agrego @cuantos_agrego y verifico si es recubrimiento. Si lo es, kapanga.
		 */ 
		Recubrimiento vecino_sacando = null; ++e.i;				// Vecino al que le saque nodos
		Recubrimiento vecino_agregando = null; ++e.i;			// Vecino al que le saque y luego agregue nodos
		if(parametros.mezclar_nodos_busqueda_vecino)
		{
			Collections.shuffle(solucion.nodos);
		}
		for(int sacar = 0; sacar < solucion.nodos.size() - cuantos_saco + 1; ++sacar)
		{
			++e.i;
			vecino_sacando = new Recubrimiento(solucion); ++e.i;
			
			// Voy sacando uno por uno los que me toca sacar
			List<Integer> a_sacar = new ArrayList<Integer>(cuantos_saco);
			for(int i = sacar; i < sacar + cuantos_saco; ++i)
			{
				a_sacar.add(vecino_sacando.nodos.get(i));
				//// System.out.println("sacar: " + sacar + " y i: " + i + " y vecino_sacando.size()" + vecino_sacando.nodos.size());
				
			}
			vecino_sacando.nodos.removeAll(a_sacar);
			
			// Si lo que me quedo es recubrimiento, gane. Por optmizacion, asumo que SIEMPRE este
			// vecino tendra mejor objetivo (menor cantidad de nodos)
			if(vecino_sacando.EsRecubrimiento(g))
			{
				// System.out.println("Solamente sacando consegui un recubrimiento!: " + vecino_sacando);
				++e.i;
				return vecino_sacando;
			}
			// System.out.println("Sacando no consegui un recubrimiento");
			
			// Voy agregando nuevos nodos
			for(int poner = 0; poner < demas_nodos.size() - cuantos_agrego + 1; ++poner)
			{
				++e.i;
				vecino_agregando = new Recubrimiento(vecino_sacando);
				
				// Voy agregando los nuevos nodos
				for(int i = poner; i < poner + cuantos_agrego; ++i)
				{
					vecino_agregando.nodos.add(demas_nodos.get(i)); ++e.i;
				}
				
				// Si es un recubrimiento, gane
				if(vecino_agregando.EsRecubrimiento(g))
				{
					// System.out.println("Agrege y era recubrimiento: " + vecino_agregando);
					++e.i;
					return vecino_agregando;
				}
				//// System.out.println("Agrege pero no era recubrimiento: " + vecino_agregando);
			}
		}
		
		// Si llegue hasta aca es porque no hay mejor vecino
		// System.out.println("No habia mejor");
		++e.i;
	    return null;
    }
}
