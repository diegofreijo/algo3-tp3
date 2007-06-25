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
	public static final int cant_grafos_chicos_ejes = 505;
	public static final int cant_grafos_grandes_ejes = 505;
	public static final int cant_grafos_chicos_nodos = 100;
	public static final int cant_grafos_grandes_nodos = 250;
	
	public static final int cant_mejores_puntajes_a_filtrar = 5;

	// Diccionario de puntajes para cada parametro
	public static Map<ParametrosBL, Integer> puntajes = new TreeMap<ParametrosBL, Integer>();
	
	public static void main(String[] args)
	{
		// Para contar el tiempo que tarda
		Cronometro cronometro = new Cronometro();
		
		// Ejecuto prueba por prueba. Cada una genera un grafico.
		ParametrosBL mejor = CorrerComparacionesEntreParametros();
		CorrerInstruccionesEjes(mejor);
		CorrerInstruccionesNodos(mejor);
		CorrerComparacionEjes(mejor);
		CorrerComparacionNodos(mejor);
		
		System.out.println("\nTiempo de ejecucion - TODAS las pruebas: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
		
		System.out.println("Fin de las pruebas");
	}
	
	// Compara los posibles parametros para hallar el mejor
	private static ParametrosBL CorrerComparacionesEntreParametros()
	{
		System.out.println("Corriendo: Comparaciones entre parametros");
		System.out.println("=========================================");
		Cronometro cronometro = new Cronometro();
		
		Map<Integer, Set<ParametrosBL>> resultados_actuales;
		ParametrosBL parametros_actuales;
		Recubrimiento recubrimiento_actual;
		
		// Levanto los grafos grandes
		System.out.println("Leyendo grafos grandes_ejes...");
		List<Grafo> grafos = Parser.LeerGrafos("aleatorio", "grandes_ejes", cant_grafos_grandes_ejes);
		
		// Corro las pruebas
		Estadisticas estadisticas = new Estadisticas();
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
					
					recubrimiento_actual = BusquedaLocal.Ejecutar(grafos.get(i), parametros_actuales, null, estadisticas);
					
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
		System.out.println("Generando grafico de comparacion de parametros...");
		Grafico grafico = new Grafico("BusquedaLocal", "comparacion_parametros");
		Iterator it = puntajes.keySet().iterator();
		while(it.hasNext())
		{
			ParametrosBL pactual = (ParametrosBL)it.next();
			Punto p = new Punto3D(pactual.porcentaje_cuantos_agrego, pactual.porcentaje_cuantos_saco, puntajes.get(pactual));
			grafico.Agregar(p);
		}
		Parser.EscribirGrafico(grafico);
		
		
		// Selecciono los mejores y genero los resultados
		System.out.println("Escribiendo mejores parametros...");
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
				
		// Me quedo con el mejor parametro (considero mejor al mas chico de los que sacaron mayor puntaje)
		System.out.println("Buscando mejor parametro...");
		Collections.sort(mejores);
		it = puntajes.keySet().iterator();
		ParametrosBL mejor = null;
		while(it.hasNext())
		{
			ParametrosBL pactual = (ParametrosBL)it.next();
			Integer actual = puntajes.get(pactual);
			if(actual.equals(mejores.get(mejores.size() - 1)) && ( mejor == null || mejor.compareTo(pactual) > 0 ))
			{
				mejor = pactual;
			}
		}
		
		// Escribo los resultados
		resultados += "Me quede con: " + mejor + "\n";
		Parser.EscribirPlano("BusquedaLocal", "mejores_parametros", resultados);
		System.out.println("Mejor parametro elegido: " + mejor);
		
		System.out.println("\nTiempo de ejecucion - Comparacion entre parametros: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
		
		return mejor;
	}

	// Cuenta la cantidad de instrucciones de Busqueda Local con los mejores parametros con grafos en funcion de los ejes
	// Cuenta la cantidad de instrucciones de Busqueda Local con los mejores parametros con grafos en funcion de los ejes
	private static void CorrerInstruccionesEjes(ParametrosBL mejor)
	{
		System.out.println("Corriendo: Instrucciones ejes");
		System.out.println("=============================");
		Cronometro cronometro = new Cronometro();
		
		// Levanto los grafos aleatorios
		System.out.println("Leyendo grafos grandes_ejes...");
		List<Grafo> grafos = Parser.LeerGrafos("aleatorio", "grandes_ejes", cant_grafos_grandes_ejes);
		
		// Corro las pruebas
		Estadisticas estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos de " + grafos.get(0).DameNodos() + " nodos para contar las instrucciones del mejor (" + mejor + "): ");
		Grafico grafico = new Grafico("BusquedaLocal", "instrucciones_ejes");
		for(int i = 0; i < grafos.size(); ++i)
		{
			System.out.print((i+1) + " ");
			Integer densidad = grafos.get(i).DameEjes().size() * 100 / (grafos.get(i).DameNodos() * (grafos.get(i).DameNodos() - 1) / 2);
			estadisticas.Resetar();
			
			// Corro el algoritmo para contar instrucciones
			BusquedaLocal.Ejecutar(grafos.get(i), mejor, null, estadisticas);
			
			// Agrego la cantidad de instrucciones al grafico
			grafico.Agregar(new Punto2D(densidad, estadisticas.i));
		}
		System.out.println();
		
		// Escribo los gaficos
		System.out.println("Escribiendo grafico...");
		Parser.EscribirGrafico(grafico);
		
		System.out.println("\nTiempo de ejecucion - Instrucciones ejes: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
	}
	
	// Cuenta la cantidad de instrucciones de Busqueda Local con los mejores parametros con grafos en funcion de los nodos
	private static void CorrerInstruccionesNodos(ParametrosBL mejor)
	{
		System.out.println("Corriendo: Instrucciones nodos");
		System.out.println("==============================");
		Cronometro cronometro = new Cronometro();
		
		// Levanto los grafos aleatorios
		System.out.println("Leyendo grafos grandes_nodos...");
		List<Grafo> grafos = Parser.LeerGrafos("aleatorio", "grandes_nodos", cant_grafos_grandes_nodos);
		
		// Corro las pruebas
		Estadisticas estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos en funcion de los nodos para contar las instrucciones del mejor (" + mejor + "): ");
		Grafico grafico = new Grafico("BusquedaLocal", "instrucciones_nodos");
		for(int i = 0; i < grafos.size(); ++i)
		{
			System.out.print((i+1) + " ");
			Integer cant_nodos = i / 5 + 1;
			estadisticas.Resetar();
			
			// Corro el algoritmo para contar instrucciones
			BusquedaLocal.Ejecutar(grafos.get(i), mejor, null, estadisticas);
			
			// Agrego la cantidad de instrucciones al grafico
			grafico.Agregar(new Punto2D(cant_nodos, estadisticas.i));
		}
		System.out.println();
		
		// Escribo los gaficos
		System.out.println("Escribiendo grafico...");
		Parser.EscribirGrafico(grafico);
		
		System.out.println("\nTiempo de ejecucion - Instrucciones nodos: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
	}
	
	// Compara Busqueda Local con el mejor parametro contra el exacto con grafos en funcion de los ejes
	private static void CorrerComparacionEjes(ParametrosBL mejor)
	{
		System.out.println("Corriendo: Comparaciones exacto ejes");
		System.out.println("====================================");
		Cronometro cronometro = new Cronometro();
		
		// Levanto los grafos aleatorios
		System.out.println("Leyendo grafos chicos_ejes...");
		List<Grafo> grafos = Parser.LeerGrafos("aleatorio", "chicos_ejes", cant_grafos_chicos_ejes);
		
		// Corro las pruebas
		Estadisticas estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos de " + grafos.get(0).DameNodos() + " nodos entre el mejor (" + mejor + ") y el exacto: ");
		Grafico grafico = new Grafico("BusquedaLocal", "comparacion_exacto_ejes");
		for(int i = 0; i < grafos.size(); ++i)
		{
			System.out.print((i+1) + " ");
			Integer densidad = grafos.get(i).DameEjes().size() * 100 / (grafos.get(i).DameNodos() * (grafos.get(i).DameNodos() - 1) / 2);
			
			// Corro los 2 algoritmos en busqueda de diferencias
			Integer diferencias = BusquedaLocal.Ejecutar(grafos.get(i), mejor, null, estadisticas).Tamano() - Exacto.Ejecutar(grafos.get(i), estadisticas).Tamano();
			
			// Agrego las diferencias al grafico
			grafico.Agregar(new Punto2D(densidad, diferencias));
		}
		System.out.println();
		
		// Escribo los gaficos
		System.out.println("Escribiendo grafico...");
		Parser.EscribirGrafico(grafico);
		
		System.out.println("\nTiempo de ejecucion - Comparacion exacto ejes: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
	}

	// Compara Busqueda Local con el mejor parametro contra el exacto con grafos en funcion de los nodos
	private static void CorrerComparacionNodos(ParametrosBL mejor)
	{
		System.out.println("Corriendo: Comparaciones exacto nodos");
		System.out.println("=====================================");
		Cronometro cronometro = new Cronometro();
		
		// Levanto los grafos aleatorios
		System.out.println("Leyendo grafos chicos_nodos...");
		List<Grafo> grafos = Parser.LeerGrafos("aleatorio", "chicos_nodos", cant_grafos_chicos_nodos);
		
		// Corro las pruebas
		Estadisticas estadisticas = new Estadisticas();
		System.out.println("Analizando " + grafos.size() + " grafos en funcion de los nodos entre el mejor (" + mejor + ") y el exacto: ");
		Grafico grafico = new Grafico("BusquedaLocal", "comparacion_exacto_nodos");
		for(int i = 0; i < grafos.size(); ++i)
		{
			System.out.print((i+1) + " ");
			Integer cant_nodos = i / 5 + 1;
			
			// Corro los 2 algoritmos en busqueda de diferencias
			Integer diferencias = BusquedaLocal.Ejecutar(grafos.get(i), mejor, null, estadisticas).Tamano() - Exacto.Ejecutar(grafos.get(i), estadisticas).Tamano();
			
			// Agrego las diferencias al grafico
			grafico.Agregar(new Punto2D(cant_nodos, diferencias));
		}
		System.out.println();
		
		// Escribo los gaficos
		System.out.println("Escribiendo grafico...");
		Parser.EscribirGrafico(grafico);
		
		System.out.println("\nTiempo de ejecucion - Comparacion exacto nodos: " + cronometro.VerSegundos() + " seg");
		System.out.println("-------------------------------------\n");
	}

	
	
	// Metodos auxiliares
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
