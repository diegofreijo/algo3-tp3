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
	private static int iteraciones_sin_cambios = 0;
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
		Recubrimiento solucion = null;
		Recubrimiento sol_anterior = null;
		int iteracion_actual = 0;
		
		do
		{
			++iteracion_actual;
			sol_anterior = solucion;
			solucion = SolucionGolosaAzarosa(g);
			solucion = BusquedaLocal.Ejecutar(g, parametros_bl, solucion, e);
		}
		while (!CondicionesDeParada(sol_anterior, solucion, iteracion_actual));

		return solucion;
	}

	private static boolean CondicionesDeParada(Recubrimiento sol_anterior, Recubrimiento solucion, int iteracion_actual)
	{
		if(sol_anterior != null && sol_anterior.compareTo(solucion) == 0)
		{
			++iteraciones_sin_cambios;
		}
		else
		{
			iteraciones_sin_cambios = 0;
		}
		
		//System.out.print(iteraciones_sin_cambios + " ");
		
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
