package algoritmos;

import java.util.ArrayList;
import java.util.List;

import utilidades.Eje;
import utilidades.Estadisticas;
import utilidades.Grafo;

import utilidades.Recubrimiento;

public class Exacto {

	public static Recubrimiento Ejecutar(Grafo g){
		
		List<Integer> ret = new ArrayList<Integer>();
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados());
		
		List<Integer> min = SacarAislados(g.DameNodos(),g.DameAislados());
		
		List<Integer> sol = new ArrayList<Integer>();
		
		ret =  AlgoExacto(nodos,g,sol,min);
		
		Estadisticas est = new Estadisticas("exacto");
		
		Recubrimiento rec = new Recubrimiento(ret.size(),est);
		
		return rec;
	}

	private static List<Integer> AlgoExacto(List<Integer> nodos, Grafo g, List<Integer> sol, List<Integer> min) {
		
		if(EsRecubrimiento(sol,g)){
			if(sol.size() < min.size()){
				min = sol;
			}
			return min;
		} else {
			if(!nodos.isEmpty()){
				Integer nodo = nodos.get(0);
				nodos.remove(nodo);
				List<Integer> temp = sol;
				temp.add(nodo);
				List<Integer> sin = AlgoExacto(nodos,g,sol,min);
				List<Integer> con = AlgoExacto(nodos,g,temp,min);
				if (sin.size() < con.size()){
					return sin;
				} else {
					return con;
				}
			} else {
				
				return min;
			}
		}
	}
	
	public static List<Integer> SacarAislados(int n, List<Integer> aislados) {
		
		List<Integer> ret = new ArrayList<Integer>();
		int i = 1;
		
		while(i <= n){
			if(!aislados.contains(i)){
				ret.add(i);
			}
			i++;
		}
		
		return ret;
	}
	
	private static boolean EsRecubrimiento(List<Integer> solucion, Grafo g)
    {
		for(Eje e : g.DameEjes())
		{
			if(!solucion.contains(Integer.valueOf(e.x)) && !solucion.contains(Integer.valueOf(e.y)))
			{
				return false;
			}
		}
		
	    return true;
    }

}
