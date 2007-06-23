package tp3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import utilidades.*;
import algoritmos.*;
import java.util.Collections;

public class Pruebas_BusquedaLocal
{
	public static Map<ParametrosBL, Integer> puntajes = new TreeMap<ParametrosBL, Integer>();
	
	public static void main(String[] args)
	{
		Cronometro cronometro = new Cronometro();
		
		final int cant_grafos_aleatorios = 450;
		final int cant_mejores_puntajes_a_filtrar = 5;
		
		Recubrimiento recubrimiento_actual;
		ParametrosBL parametros_actuales;
		Map<Integer, Set<ParametrosBL>> resultados_actuales;
		
		Grafico grafico = new Grafico();

		
		
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", cant_grafos_aleatorios);
		
		System.out.println("Ejecutando Busquedas Locales");
		Estadisticas est = new Estadisticas("busqueda_local");

		// Ejectuto las pruebas para cada grafo
		System.out.println("Analizando " + aleatorios.size() + " grafos: ");
		for(int i = 0; i < aleatorios.size(); ++i)
		{
			System.out.print((i+1) + " ");
			resultados_actuales = new TreeMap<Integer, Set<ParametrosBL>>();
			for(int porcentaje_cuantos_saco = 1; porcentaje_cuantos_saco <= 100; ++porcentaje_cuantos_saco)
			{
				for(int porcentaje_cuantos_agrego = 0; porcentaje_cuantos_agrego < porcentaje_cuantos_saco; ++porcentaje_cuantos_agrego)
				{
					parametros_actuales = new ParametrosBL();
					parametros_actuales.porcentaje_cuantos_saco = porcentaje_cuantos_saco;
					parametros_actuales.porcentaje_cuantos_agrego = porcentaje_cuantos_agrego;
					
					recubrimiento_actual = BusquedaLocal.Ejecutar(aleatorios.get(i), parametros_actuales, est);
					
					Set<ParametrosBL> anteriores = resultados_actuales.get(recubrimiento_actual.Tamano());
					if(anteriores == null)
					{
						anteriores = new HashSet<ParametrosBL>();
					}
					anteriores.add(parametros_actuales);
					resultados_actuales.put(recubrimiento_actual.Tamano(), anteriores);
				}
			}			
			ActualizarPuntajes(resultados_actuales);
		}
		System.out.println();
		
		System.out.println("#Puntajes: " + puntajes.size());
		System.out.println("Puntajes: " + toString(puntajes));
		
		// Me quedo con los mejores
		List<Integer> mejores = FiltrarMejoresPuntajes(cant_mejores_puntajes_a_filtrar);
		System.out.println("Mejores puntajes:");
		for(Integer mejor : mejores)
		{
			System.out.println("  " + mejor + ":");
			Iterator it = puntajes.keySet().iterator();
			while(it.hasNext())
			{
				ParametrosBL pactual = (ParametrosBL)it.next();
				Integer actual = puntajes.get(pactual);
				if(actual.equals(mejor))
				{
					System.out.println("    " + pactual);
				}
			}
		}
				
		System.out.println("Fin de las pruebas");
		System.out.println("Tiempo de ejecucion: " + cronometro.VerSegundos() + " seg");
	}

	private static void ActualizarPuntajes(Map<Integer, Set<ParametrosBL>> resultados)
    {
		// Busco los 3 valores mas chicos
		List<Integer> podio = new ArrayList<Integer>(3);
		Iterator it = resultados.keySet().iterator();
		
		while(it.hasNext())
		{
			Integer actual = (Integer)it.next();
			if(podio.size() < 3)
			{
				podio.add(actual);
			}
			else
			{
				// Veo si el actual es mejor que alguno ya en el podio
				for(Integer campeon : podio)
				{
					if(campeon > actual)
					{
						podio.remove(campeon);
						podio.add(actual);
					}
				}
			}
		}
		
		// Ahora que tengo el podio, lo ordeno
		Collections.sort(podio);
		
		// Y asigno el puntaje correspondiente a los parametros del podio
		Set<ParametrosBL> ganadores_actuales;
		for(int i = 0; i < podio.size(); ++i)
		{
			ganadores_actuales = resultados.get(podio.get(i));
			for(ParametrosBL ganador : ganadores_actuales)
			{
				Integer puntaje = puntajes.get(ganador);
				if(puntaje == null)
				{
					puntaje = 0;
				}
				else
				{
					puntajes.remove(ganador);
				}
				puntajes.put(ganador, puntaje + 5 - 2 * i);
			}
		}
    }

	private static List<Integer> FiltrarMejoresPuntajes(int cuantos)
	{
		// Busco los mejores puntajes
		List<Integer> mejores = new ArrayList<Integer>(cuantos);
		Iterator it = puntajes.keySet().iterator();
		
		while(it.hasNext())
		{
			Integer actual = puntajes.get((ParametrosBL)it.next());
			if(mejores.contains(actual))
			{
				continue;
			}
			else if(mejores.size() < cuantos)
			{
				mejores.add(actual);
			}
			else
			{
				// Veo si el actual es mejor que alguno ya entre los mejores
				Collections.sort(mejores);
				for(Integer mejor : mejores)
				{
					if(mejor < actual)
					{
						mejores.remove(mejor);
						mejores.add(actual);
						break;
					}
				}
			}
		}
		
		return mejores;
	}
	
	public static String toString(Map<ParametrosBL, Integer> m)
	{
		String ret = "";
		Iterator it = m.entrySet().iterator();
	    while (it.hasNext())
	    {
	        Map.Entry pairs = (Map.Entry)it.next();
	        ret += pairs.getKey() + " -> " + pairs.getValue() + ", ";
	    }
	    
	    return ret;
	}
	
}
