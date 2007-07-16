package algoritmos;

import java.util.ArrayList;
import java.util.List;
import utilidades.Eje;
import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class BusquedaLocal
{
	private static Estadisticas e;
	private static ParametrosBL parametros;
	
	public static Recubrimiento Ejecutar(Grafo g, ParametrosBL parametros_entrada, Recubrimiento sol_inicial, Estadisticas est)
	{
		e = est;
		parametros = parametros_entrada;
		
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(), e);
		
		if(sol_inicial == null)
		{
			solucion = ConstruirSolucionInicial(g); ++e.i;
		}
		else
		{
			solucion = sol_inicial; ++e.i;
		}
		
		//System.out.println("Solucion inicial: " + solucion);
		
		Recubrimiento mejor_vecino;
		
		while((mejor_vecino = VecinoMejor(solucion, parametros.porcentaje_cuantos_saco, parametros.porcentaje_cuantos_agrego, g)) != null)
		{
			//System.out.println("\nMejor vecino: " + mejor_vecino + "\n");
			++e.i;
			solucion = mejor_vecino; ++e.i;
		}
		
		//System.out.println("Solucion final: " + solucion);
		++e.i;
		return solucion;
	}
	
	private static Recubrimiento ConstruirSolucionInicial(Grafo g)
    {
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(), e); ++e.i;
		
	    for(Eje eje : g.DameEjes())
	    {
	    	++e.i;
	    	e.i+=2*solucion.nodos.size();
	    	if(!solucion.nodos.contains(eje.x) && !solucion.nodos.contains(eje.y))
	    	{
	    		solucion.nodos.add(eje.x); ++e.i;
	    	}
	    }
	    
	    ++e.i;
	    return solucion;	
    }

	// Precondicion(o tambien llamado "sentido comun"): cuantos_saco > cuantos_agrego
	private static Recubrimiento VecinoMejor(Recubrimiento solucion, int porcentaje_cuantos_saco, int porcentaje_cuantos_agrego, Grafo g)
    {
		// Si la solucion actual es vacia, ya fue, no voy a conseguir nada mejor
		++e.i;
		if(solucion.Tamano() == 0)
		{
			return null;
		}
		
		// Armo la lista de nodos que no estan en la solucion
		List<Integer> demas_nodos = new ArrayList<Integer>(g.DameNodos()); e.i+=g.DameNodos();
		for(int i = 1; i <= g.DameNodos(); ++i)
		{
			++e.i;
			e.i+=solucion.nodos.size();
			if(!solucion.nodos.contains(i))
			{
				demas_nodos.add(i); ++e.i;
			}
		}
	
		// Calculo el porcentaje de los que saco y pongo
		int cuantos_saco = porcentaje_cuantos_saco * solucion.nodos.size() / 100; ++e.i;
		int cuantos_agrego = porcentaje_cuantos_agrego * solucion.nodos.size() / 100; ++e.i;
		// Verifico no tratar de sacar mas de los que tiene la solucion o agregar mas de los que tengo para agregar
		e.i+=4;
		if(cuantos_saco == 0)
		{
			cuantos_saco = 1;
		}
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
		 *  
		 *  Para sacar, voy iterando por cada uno y saco a ese junto con los demas distanciados por el @offset actual.
		 *  Idem para ir agregando luego a cada combinacion de los que saco.  
		 */ 
		Recubrimiento vecino_sacando = null; ++e.i;				// Vecino al que le saque nodos
		Recubrimiento vecino_agregando = null; ++e.i;			// Vecino al que le saque y luego agregue nodos
		
		//System.out.println("Cuantos saco: " + cuantos_saco);
		//System.out.println("Cuantos agrego: " + cuantos_agrego);
		// Para cada nodo de la solucion actual...
		for(int sacar = 0; sacar < solucion.nodos.size() - cuantos_saco + 1; ++sacar)
		{
			++e.i;
			//System.out.println("Sacar:" + sacar);
			//System.out.println("========");
			
			// Calculo el offset maximo de los que saco
			// Sera el maximo que cumpla
			//							(cuantos_saco - 1) * offset_maximo + cuantos_saco <= solucion.tamano - sacar
			int offset_maximo_saco; e.i+=5;
			if(cuantos_saco > 1)
			{
				offset_maximo_saco = (int)((solucion.nodos.size() - sacar - cuantos_saco) / (cuantos_saco - 1));
			}
			else
			{
				offset_maximo_saco = 0;
			}
			//System.out.println("Offset maximo saco: " + offset_maximo_saco);
			
			// Para cada offset de los que saco...
			for(int offset_actual_saco = 0; offset_actual_saco <= offset_maximo_saco; ++offset_actual_saco)
			{
				++e.i;
				vecino_sacando = new Recubrimiento(solucion); ++e.i;
				
				// Voy sacando uno por uno los que me toca sacar
				List<Integer> a_sacar = new ArrayList<Integer>(cuantos_saco); e.i+=cuantos_saco;
				for(int i = sacar; i < cuantos_saco * (offset_actual_saco + 1) + sacar; i += offset_actual_saco + 1)
				{
					a_sacar.add(vecino_sacando.nodos.get(i)); e.i+=2;
				}
				vecino_sacando.nodos.removeAll(a_sacar); e.i+=a_sacar.size()*vecino_sacando.nodos.size();
				
				// Si lo que me quedo es recubrimiento, gane. Por optmizacion, asumo que SIEMPRE este
				// vecino tendra mejor objetivo (menor cantidad de nodos)
				++e.i;
				if(vecino_sacando.EsRecubrimiento(g))
				{
					return vecino_sacando;
				}
				//System.out.println("Vecino sacando: " + vecino_sacando);
				
				// Si no tengo nodo que agregar, ni pruebo
				++e.i;
				if(cuantos_agrego == 0)
				{
					continue;
				}
				
				// Para cada nodo de los que no estan en la solucion...
				for(int poner = 0; poner < demas_nodos.size() - cuantos_agrego + 1; ++poner)
				{
					++e.i;
					//System.out.println("  Poner:" + poner);
					//System.out.println("  ========");
					
					// Calculo el offset maximo de los que agrego
					int offset_maximo_agrego; e.i+=5;
					if(cuantos_agrego > 1)
					{
						offset_maximo_agrego = (int)((demas_nodos.size() - poner - cuantos_agrego) / (cuantos_agrego - 1));
					}
					else
					{
						offset_maximo_agrego = 0;
					}
					//System.out.println("  Offset maximo agrego: " + offset_maximo_agrego);
					
					// Para cada offset de los que agrego...
					for(int offset_actual_agrego = 0; offset_actual_agrego <= offset_maximo_agrego; ++offset_actual_agrego)
					{
						++e.i;
						vecino_agregando = new Recubrimiento(vecino_sacando); ++e.i;
						
						// Voy agregando los nuevos nodos
						for(int i = poner; i < cuantos_agrego * (offset_actual_agrego + 1) + poner; i += offset_actual_agrego + 1)
						{
							vecino_agregando.nodos.add(demas_nodos.get(i)); e.i+=2;
						}
						
						// Si es un recubrimiento, gane
						++e.i;
						if(vecino_agregando.EsRecubrimiento(g))
						{
							return vecino_agregando;
						}
						//System.out.println("  Vecino agregando: " + vecino_agregando);
					}
				}
			}
		}
		
		// Si llegue hasta aca es porque no hay mejor vecino
		++e.i;
	    return null;
    }
}
