package algoritmos;

import java.util.ArrayList;
import java.util.List;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class Goloso {
	
	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g, Estadisticas est){
		
		e = est;
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados());++e.i;
		
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(),e);++e.i;
		
		System.out.println("Ejes: " + g.DameEjes());		
		
		while(solucion.EsRecubrimiento(g)){
			++e.i;
			Integer nodoMayor = NodoMayorGrado(nodos,g);++e.i;
			solucion.nodos.add(nodoMayor);++e.i;
			System.out.println("Ejes Cubiertos: " + g.DameVecinos(nodoMayor).toString());		
			nodos.remove(nodoMayor);++e.i;
		}
		
		return solucion;
		
	}

	private static int NodoMayorGrado(List<Integer> nodos, Grafo g) {
		
		int i = 0;
		
		int ret = nodos.get(0);++e.i;
		
		while(i < nodos.size()){
			++e.i;
			if(g.DameVecinos(ret).size() < g.DameVecinos(nodos.get(i)).size()){
				++e.i;
				ret = nodos.get(i);++e.i;
			}
			i++;++e.i;
		}
		
		return ret;
	}

	public static List<Integer> SacarAislados(int n, List<Integer> aislados) {
		
		List<Integer> ret = new ArrayList<Integer>();
		int i = 1;++e.i;
		
		while(i <= n){
			++e.i;
			if(!aislados.contains(i)){
				++e.i;
				ret.add(i);++e.i;
			}
			i++;++e.i;
		}
		
		return ret;
	}
}
