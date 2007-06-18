package algoritmos;

import java.util.ArrayList;
import java.util.List;
import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public class Exacto {

	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g,Estadisticas est){
		
		e = est;
		
		Recubrimiento ret = new Recubrimiento(g.DameNodos(),e);
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados()).nodos;
		
		Recubrimiento min = SacarAislados(g.DameNodos(),g.DameAislados());
		
		Recubrimiento sol = new Recubrimiento(g.DameNodos(),e);
		
		ret =  AlgoExacto(nodos,g,sol,min);
		
		//System.out.println("Exacto: " + ret.toString());	
		
		return ret;
	}

	private static Recubrimiento AlgoExacto(List<Integer> nodos, Grafo g, Recubrimiento sol, Recubrimiento min) {
		
		if(sol.EsRecubrimiento(g)){
			++e.i;
			if(sol.nodos.size() < min.nodos.size()){
				++e.i;
				min = sol;++e.i;
			}
			return min;
		} else {
			List<Integer> nodostemp = new ArrayList<Integer>(nodos);
			if(!nodostemp.isEmpty()){
				++e.i;
				Recubrimiento temp = new Recubrimiento(sol);
				Integer nodo = nodostemp.get(0);++e.i;
				nodostemp.remove(nodo);++e.i;
				Recubrimiento sin = AlgoExacto(nodostemp,g,temp,min);
				temp.nodos.add(nodo);++e.i;
				Recubrimiento con = AlgoExacto(nodostemp,g,temp,min);
				if (sin.nodos.size() < con.nodos.size()){
					++e.i;
					return sin;
				} else {
					++e.i;
					return con;
				}
			} else {
				++e.i;
				return min;
			}
		}
	}
	
	public static Recubrimiento SacarAislados(int n, List<Integer> aislados) {
		
		Recubrimiento ret = new Recubrimiento(n-aislados.size()+1,e);
		int i = 1;
		
		while(i <= n){
			++e.i;
			if(!aislados.contains(i)){
				ret.nodos.add(i);++e.i;
			}
			++e.i;
			i++;
		}
		
		return ret;
	}

}
