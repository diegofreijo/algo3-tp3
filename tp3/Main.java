package tp3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utilidades.*;
import algoritmos.*;
import java.util.Collections;

public class Main
{
	public static Map<ParametrosBL, Integer> puntajes = new HashMap<ParametrosBL, Integer>();;
	
	public static void main(String[] args)
	{
		final int cant_grafos_aleatorios = 10;
		
		Recubrimiento recubrimiento_actual;
		ParametrosBL parametros_actuales;
		Map<Integer, Set<ParametrosBL>> resultados_actuales;
		
		Grafico3D grafico = new Grafico3D();
		
		// Agrego en el arbol cada parametro con un puntaje inicial de 0  
		// Ya se que es re cabeza, pero simplifica algunas cosas y aca la complejidad no importa...
		/*for(int porcentaje_cuantos_saco = 1; porcentaje_cuantos_saco <= 100; ++porcentaje_cuantos_saco)
		{
			for(int porcentaje_cuantos_agrego = 0; porcentaje_cuantos_agrego < porcentaje_cuantos_saco; ++porcentaje_cuantos_agrego)
			{
				parametros_actuales = new ParametrosBL();
				parametros_actuales.porcentaje_cuantos_saco = porcentaje_cuantos_saco;
				parametros_actuales.porcentaje_cuantos_agrego = porcentaje_cuantos_agrego;
								
				puntajes.put(parametros_actuales, 0);
			}
		}*/
		
		
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
		
		System.out.println(toString(puntajes));
		
		//Parser.Escribir(grafico, "./dat/Comparaciones_BL_sin_mezcla.dat");
		
		
		System.out.println("Fin de las pruebas");
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
				puntajes.put(ganador, puntaje + 5 - 2 * i);
			}
		}
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
