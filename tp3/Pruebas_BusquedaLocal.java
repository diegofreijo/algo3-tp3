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
	// Parametros de las pruebas
	public static final int cant_grafos = 505;
	public static final int cant_mejores_puntajes_a_filtrar = 5;

	// Diccionario de puntajes para cada parametro
	public static Map<ParametrosBL, Integer> puntajes = new TreeMap<ParametrosBL, Integer>();
		
	public static void main(String[] args)
	{
		// Para contar el tiempo que tarda
		Cronometro cronometro = new Cronometro();
		
		// Variables para las pruebas
		Recubrimiento recubrimiento_actual;
		ParametrosBL parametros_actuales;
		Map<Integer, Set<ParametrosBL>> resultados_actuales;
		List<Grafo> grafos;
		Iterator it;
		Estadisticas estadisticas;
		Grafico grafico_comparacion_parametros, grafico_comparacion_exacto, grafico_instrucciones;
		
		
		
		
		//////////////////////////////////////////////////////////////////
		// Corro comparaciones entre los parametros
		//////////////////////////////////////////////////////////////////

		// Levanto los grafos grandes
		System.out.println("Leyendo grafos grandes");
		grafos = Parser.LeerGrafos("aleatorio", "grandes", cant_grafos);
		
		// Corro las pruebas
		estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos de " + grafos.get(0).DameNodos() + " nodos entre parametros: ");
		for(int i = 0; i < grafos.size(); ++i)
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
					
					recubrimiento_actual = BusquedaLocal.Ejecutar(grafos.get(i), parametros_actuales, estadisticas);
					
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
		
		
		// Grafico de comparacion de parametros
		System.out.println("Generando grafico de comparacion de parametros");
		grafico_comparacion_parametros = new Grafico("BusquedaLocal", "comparacion_parametros");
		it = puntajes.keySet().iterator();
		while(it.hasNext())
		{
			ParametrosBL pactual = (ParametrosBL)it.next();
			Punto p = new Punto3D(pactual.porcentaje_cuantos_agrego, pactual.porcentaje_cuantos_saco, puntajes.get(pactual));
			grafico_comparacion_parametros.Agregar(p);
		}
		Parser.EscribirGrafico(grafico_comparacion_parametros);
		
		
		// Selecciono los mejores y genero los resultados
		System.out.println("Escribiendo mejores parametros");
		List<Integer> mejores = FiltrarMejoresPuntajes(cant_mejores_puntajes_a_filtrar);
		String resultados = "Mejores puntajes:\n";
		for(int i = mejores.size() - 1; i >= 0 ; --i)
		{
			resultados += "  " + mejores.get(i) + ":\n";
			it = puntajes.keySet().iterator();
			while(it.hasNext())
			{
				ParametrosBL pactual = (ParametrosBL)it.next();
				Integer actual = puntajes.get(pactual);
				if(actual.equals(mejores.get(i)))
				{
					resultados += "    " + pactual + "\n";
				}
			}
		}
				
		// Me quedo con el mejor parametro (
		System.out.println("Buscando mejor parametro");
		it = puntajes.keySet().iterator();
		ParametrosBL mejor = (ParametrosBL)it.next();
		while(it.hasNext())
		{
			ParametrosBL pactual = (ParametrosBL)it.next();
			Integer actual = puntajes.get(pactual);
			if(actual.equals(mejores.get(mejores.size() - 1))  &&  mejor.compareTo(pactual) > 0)
			{
				mejor = pactual;
			}
		}
		
		// Escribo los resultados
		resultados += "Me quede con: " + mejor + "\n";
		Parser.EscribirPlano("BusquedaLocal", "mejores_parametros", resultados);
		System.out.println("Mejor parametro elegido: " + mejor);
		
		System.out.println("\nTiempo de ejecucion de la primer tanda: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
		cronometro.Resetear();
		
		
		
		//////////////////////////////////////////////////////////////////
		// Corro comparaciones entre el mejor parametro y el exacto
		//////////////////////////////////////////////////////////////////

		// Levanto los grafos aleatorios
		System.out.println("Leyendo grafos chicos");
		grafos = Parser.LeerGrafos("aleatorio", "chicos", cant_grafos);
		
		// Corro las pruebas
		estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos de " + grafos.get(0).DameNodos() + " nodos entre el mejor y el exacto: ");
		grafico_comparacion_exacto = new Grafico("BusquedaLocal", "comparacion_exacto");
		grafico_instrucciones = new Grafico("BusquedaLocal", "instrucciones");
		for(int i = 0; i < grafos.size(); ++i)
		{
			System.out.print((i+1) + " ");
			Integer densidad = grafos.get(i).DameEjes().size() * 100 / (grafos.get(i).DameNodos() * (grafos.get(i).DameNodos() - 1) / 2);
						
			// Corro los 2 algoritmos y comparo
			recubrimiento_actual = BusquedaLocal.Ejecutar(grafos.get(i), mejor, estadisticas);
			Integer diferencia = recubrimiento_actual.Tamano() - Exacto.Ejecutar(grafos.get(i), estadisticas).Tamano();
			
			// Agrego al grafico de comparacion
			Punto p = new Punto2D(densidad, diferencia);
			grafico_comparacion_exacto.Agregar(p);
			
			// Agrego al grafico de instrucciones
			p = new Punto2D(densidad, estadisticas.i);
			grafico_instrucciones.Agregar(p);
		}
		System.out.println();
		
		// Escribo los gaficos
		System.out.println("");
		Parser.EscribirGrafico(grafico_comparacion_exacto);
		Parser.EscribirGrafico(grafico_instrucciones);
		
		
		
		System.out.println("\nTiempo de ejecucion de la segunda tanda: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
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
