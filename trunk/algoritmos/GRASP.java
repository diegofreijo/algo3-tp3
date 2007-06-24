package algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class GRASP
{
	public static int iteracion_SC;
	private static Estadisticas e;

	public static Recubrimiento Ejecutar(Grafo g, ParametrosGRASP p , Estadisticas est)
	{
		e = est;
		iteracion_SC = 0;
		Recubrimiento solucion = null;
		Recubrimiento solant = null;
		int k = 0;
		do
		{
			solant = new Recubrimiento(solucion);
			solucion = SolucionGolosaAzarosa(g,p.porcentaje_goloso);
			ParametrosBL parametros = new ParametrosBL();
			parametros.porcentaje_cuantos_agrego = p.porcentaje_cuantos_agrego;
			parametros.porcentaje_cuantos_saco	 = p.porcentaje_cuantos_saco;
			solucion = BusquedaLocal.Ejecutar(g, parametros, p, e);
		}
		while (!CondicionesDeParada(solant,solucion,p.iteraciones_max,p.iteraciones_sin_cambio,k));

		return solucion;
	}

	private static boolean CondicionesDeParada(Recubrimiento solant, Recubrimiento solucion, int i, int j, int k)
	{
		if(solant == solucion){
			iteracion_SC++;
		}
		
		if(iteracion_SC == j){
			return false;
		}
		
		if(k == i){
			return false;
		}
		
		return true;
	}

	private static Recubrimiento SolucionGolosaAzarosa(Grafo g, int porcentaje_goloso)
	{
		return GolosoRandom.Ejecutar(g,porcentaje_goloso);
	}
	
	public static class GolosoRandom
	{
		//private static Estadisticas e;

		public static Recubrimiento Ejecutar(Grafo g, int porcentaje_goloso)
		{

			Estadisticas est = new Estadisticas();
			
			e = est;

			List<Integer> nodos = SacarAislados(g.DameNodos(), g.DameAislados());

			Recubrimiento solucion = new Recubrimiento(g.DameNodos(), e);

			System.out.println("Ejes: " + g.DameEjes());

			while (!solucion.EsRecubrimiento(g))
			{
				++e.i;
				Integer nodoParaAgregar= NodoAgregar(nodos, porcentaje_goloso,g);
				++e.i;
				solucion.nodos.add(nodoParaAgregar);
				System.out.println("Ejes Cubiertos: " + g.DameVecinos(nodoParaAgregar).toString());
				++e.i;
				nodos.remove(nodoParaAgregar);
			}

			return solucion;

		}

		private static int NodoAgregar(List<Integer> nodos, int porcentaje_goloso, Grafo g)
		{
			List<Integer> nodosTemp = new ArrayList<Integer>();
			List<Integer> nodosFin = new ArrayList<Integer>();

			nodosTemp = Ordenar(nodos,g);
			int i = 0;
			
			int vecinosPermitidos = (g.DameVecinos(nodosTemp.get(nodosTemp.size())-1).size()*porcentaje_goloso);
			
			while(i < nodosTemp.size()){
				if(g.DameVecinos(nodosTemp.get(i)).size() >= vecinosPermitidos){
					nodosFin.add(nodosTemp.get(i));
				}
				i++;
			}
			
			Random rnd = new Random(nodosFin.size() + 1235689 / 10 * 8);

			int ret = rnd.nextInt(nodosFin.size());
			++e.i;

			return nodosFin.get(ret);
		}
		
		public static List<Integer> Ordenar(List<Integer> nodos, Grafo g){
			
			List<Integer> ret = new ArrayList<Integer>();
			List<Integer> menores = new ArrayList<Integer>();
			List<Integer> mayores = new ArrayList<Integer>();
			if(nodos.size() > 0){
				int pivot = nodos.get(0);
				int i = 1;
				
				while(i < nodos.size()){
					if(g.DameVecinos(pivot).size() > g.DameVecinos(nodos.get(i)).size()){
						menores.add(nodos.get(i));
					} else {
						mayores.add(nodos.get(i));
					}
					i++;
				}
				
				ret.addAll(Ordenar(menores,g));
				ret.add(pivot);
				ret.addAll(Ordenar(mayores,g));
				
				return ret;
			} else {
				return nodos;
			}
				
		}

		public static List<Integer> SacarAislados(int n, List<Integer> aislados)
		{
			List<Integer> ret = new ArrayList<Integer>();
			int i = 1;
			++e.i;

			while (i <= n)
			{
				++e.i;
				if (!aislados.contains(i))
				{
					++e.i;
					ret.add(i);
					++e.i;
				}
				i++;
				++e.i;
			}

			return ret;
		}

	}
}
