package tp3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import utilidades.*;
import algoritmos.*;

public class Main
{
	static Map<ParametrosBL, Integer> puntajes = new TreeMap<ParametrosBL, Integer>();
	
	public static void main(String[] args)
	{
		final int cant_grafos_aleatorios = 486;
				
		Recubrimiento recubrimiento_actual;
		ParametrosBL parametros_actuales = new ParametrosBL();
		Map<Integer, Set<ParametrosBL>> resultados_actuales;
		
		Grafico3D grafico = new Grafico3D();
		
		
		
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", cant_grafos_aleatorios);
		
		System.out.println("Ejecutando Busquedas Locales");
		Estadisticas est = new Estadisticas("busqueda_local");
		parametros_actuales.mezclar_nodos_busqueda_vecino = false;

		// Ejectuto las pruebas para cada grafo
		for(int i = 0; i < cant_grafos_aleatorios; ++i)
		{
<<<<<<< .mine
			resultados_actuales = new TreeMap<ParametrosBL, Integer>();
			for(int porcentaje_cuantos_saco = 1; porcentaje_cuantos_saco <= 100; ++porcentaje_cuantos_saco)
=======
			rec_exacto = Exacto.Ejecutar(g,est);
			rec_busqueda_local = BusquedaLocal.Ejecutar(g, porcentaje_cuantos_saco, porcentaje_cuantos_agrego, est);
			
			if(rec_exacto.nodos.size() != rec_busqueda_local.nodos.size())
>>>>>>> .r42
			{
				parametros_actuales.porcentaje_cuantos_saco = porcentaje_cuantos_saco;
				for(int porcentaje_cuantos_agrego = 0; porcentaje_cuantos_agrego < porcentaje_cuantos_saco; ++porcentaje_cuantos_agrego)
				{
					parametros_actuales.porcentaje_cuantos_agrego = porcentaje_cuantos_agrego;
					recubrimiento_actual = BusquedaLocal.Ejecutar(aleatorios.get(i), parametros_actuales, est);
					resultados_actuales.put(parametros_actuales, recubrimiento_actual.Tamano());
				}
			}
			ActualizarPuntajes(resultados_actuales);
		}
		
		
		Parser.Escribir(grafico, "./dat/Comparaciones_BL_sin_mezcla.dat");
		
		
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
			if(podio.isEmpty())
			{
				
			}
		}
    }

	/*private static void ActualizarMejoresDif(List<Punto3D> mejores_dif, Punto3D nuevo)
    {
		if(mejores_dif.size() < 5)
		{
			mejores_dif.add(nuevo);
		}
		else
		{
			for(Punto3D p : mejores_dif)
			{
				if(p.z > nuevo.z)
				{
					mejores_dif.remove(p);
					mejores_dif.add(nuevo);
					System.out.println("Cambio en mejores: " + mejores_dif);
					break;
				}
			}
		}
    }*/
}
