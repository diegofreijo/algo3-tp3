package algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class GRASP
{
	private static Estadisticas e;
	private static ParametrosGRASP parametros_grasp;

	public static Recubrimiento Ejecutar(Grafo g, ParametrosGRASP parametros_entrada, Estadisticas est)
	{
		e = est;
		parametros_grasp = parametros_entrada;
		
		// Genero los parametros que le voy a pasar a la busqueda local
		ParametrosBL parametros_bl = new ParametrosBL();
		parametros_bl.porcentaje_cuantos_agrego = parametros_grasp.porcentaje_cuantos_agrego;
		parametros_bl.porcentaje_cuantos_saco = parametros_grasp.porcentaje_cuantos_saco;
		
		// Variables para uso del algoritmo
		Recubrimiento mejor_solucion = null;
		Recubrimiento solucion_actual = null;
		
		int iteracion_actual = 0;
		int iteraciones_sin_cambios = 0;
		
		do
		{
			++iteracion_actual;
			solucion_actual = SolucionGolosaAzarosa(g);
			solucion_actual = BusquedaLocal.Ejecutar(g, parametros_bl, solucion_actual, e);
			
			if(mejor_solucion == null || mejor_solucion.nodos.size() > solucion_actual.nodos.size())
			{
				iteraciones_sin_cambios = 0;
				mejor_solucion = solucion_actual;
			}
			else
			{
				++iteraciones_sin_cambios;
			}
		}
		while(!CondicionesDeParada(iteraciones_sin_cambios, iteracion_actual));

		return solucion_actual;
	}

	private static boolean CondicionesDeParada(int iteraciones_sin_cambios, int iteracion_actual)
	{	
		if(iteraciones_sin_cambios < parametros_grasp.iteraciones_sin_cambio &&
				iteracion_actual < parametros_grasp.iteraciones_max)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	private static Recubrimiento SolucionGolosaAzarosa(Grafo g)
	{
		return GolosoRandom.Ejecutar(g, parametros_grasp.porcentaje_goloso, e);
	}
	
	public static class GolosoRandom
	{
		private static Estadisticas e;

		public static Recubrimiento Ejecutar(Grafo g, int porcentaje_goloso, Estadisticas est)
		{
			e = est;

			List<Integer> nodos = SacarAislados(g.DameNodos(), g.DameAislados());

			Recubrimiento solucion = new Recubrimiento(g.DameNodos(), e);

			while (!solucion.EsRecubrimiento(g))
			{
				Integer nodoParaAgregar = NodoAgregar(nodos, porcentaje_goloso, g); ++e.i;
				solucion.nodos.add(nodoParaAgregar); ++e.i;
				nodos.remove(nodoParaAgregar); e.i+=nodos.size();
			}

			return solucion;
		}

		private static int NodoAgregar(List<Integer> nodos, int porcentaje_goloso, Grafo g)
		{
			List<Integer> nodosTemp = new ArrayList<Integer>(); ++e.i;
			List<Integer> nodosFin = new ArrayList<Integer>(); ++e.i;

			nodosTemp = Ordenar(nodos, g); ++e.i;
			int i = 0;
			int vecinosPermitidos = (g.DameVecinos(nodosTemp.get(nodosTemp.size() - 1)).size() * porcentaje_goloso) / 100; ++e.i;
			
			while(i < nodosTemp.size())
			{
				++e.i;
				if(g.DameVecinos(nodosTemp.get(i)).size() >= vecinosPermitidos)
				{
					nodosFin.add(nodosTemp.get(i)); ++e.i;
				}
				++i;
			}
			
			Random rnd = new Random(nodosFin.size() + 1235689 / 10 * 8); ++e.i;

			int ret = rnd.nextInt(nodosFin.size()); ++e.i;

			++e.i;
			return nodosFin.get(ret);
		}
		
		public static List<Integer> Ordenar(List<Integer> nodos, Grafo g)
		{
			e.i+=nodos.size()*Math.log(nodos.size());
			
			List<Integer> ret = new ArrayList<Integer>();
			List<Integer> menores = new ArrayList<Integer>();
			List<Integer> mayores = new ArrayList<Integer>();
			if(nodos.size() > 0)
			{
				int pivot = nodos.get(0);
				int i = 1;
				
				while(i < nodos.size()){
					if(g.DameVecinos(pivot).size() > g.DameVecinos(nodos.get(i)).size())
					{
						menores.add(nodos.get(i));
					}
					else
					{
						mayores.add(nodos.get(i));
					}
					i++;
				}
				
				ret.addAll(Ordenar(menores,g));
				ret.add(pivot);
				ret.addAll(Ordenar(mayores,g));
				
				return ret;
			}
			else
			{
				return nodos;
			}	
		}

		public static List<Integer> SacarAislados(int n, List<Integer> aislados)
		{
			List<Integer> ret = new ArrayList<Integer>(); ++e.i;
			int i = 1; ++e.i;
			
			while (i <= n)
			{
				++e.i;
				e.i+=aislados.size();
				if (!aislados.contains(i))
				{					
					ret.add(i); ++e.i;					
				}
				++i;				
			}

			++e.i;
			return ret;
		}

	}
}
