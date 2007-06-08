package algoritmos;

import java.util.List;

import utilidades.Estadisticas;
import utilidades.Grafo;

import utilidades.Recubrimiento;

public class Exacto {

	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g){
		
		e = new Estadisticas("exacto");
		
		Recubrimiento ret = new Recubrimiento(g.DameNodos(),e);
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados()).nodos;
		
		Recubrimiento min = SacarAislados(g.DameNodos(),g.DameAislados());
		
		Recubrimiento sol = new Recubrimiento(g.DameNodos(),e);
		
		ret =  AlgoExacto(nodos,g,sol,min);
		
		System.out.println("Exacto: " + ret.toString());	
		
		return ret;
	}

	private static Recubrimiento AlgoExacto(List<Integer> nodos, Grafo g, Recubrimiento sol, Recubrimiento min) {
		
		if(sol.EsRecubrimiento(g)){
			if(sol.nodos.size() < min.nodos.size()){
				min = sol;
			}
			return min;
		} else {
			if(!nodos.isEmpty()){
				Recubrimiento temp = new Recubrimiento(sol);
				Integer nodo = nodos.get(0);
				nodos.remove(nodo);
				Recubrimiento sin = AlgoExacto(nodos,g,temp,min);
				temp.nodos.add(nodo);
				Recubrimiento con = AlgoExacto(nodos,g,temp,min);
				if (sin.nodos.size() < con.nodos.size()){
					return sin;
				} else {
					return con;
				}
			} else {
				
				return min;
			}
		}
	}
	
	public static Recubrimiento SacarAislados(int n, List<Integer> aislados) {
		
		Recubrimiento ret = new Recubrimiento(n-aislados.size()+1,e);
		int i = 1;
		
		while(i <= n){
			if(!aislados.contains(i)){
				ret.nodos.add(i);
			}
			i++;
		}
		
		return ret;
	}

}
