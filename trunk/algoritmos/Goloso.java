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
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados());
		
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(),e);
		
		System.out.println("Ejes: " + g.DameEjes());		
		
		while(solucion.EsRecubrimiento(g)){
			Integer nodoMayor = NodoMayorGrado(nodos,g);
			solucion.nodos.add(nodoMayor);
			System.out.println("Ejes Cubiertos: " + g.DameVecinos(nodoMayor).toString());		
			nodos.remove(nodoMayor);
		}
		
		return solucion;
		
	}

	private static int NodoMayorGrado(List<Integer> nodos, Grafo g) {
		
		int i = 0;
		
		int ret = nodos.get(1);
		
		while(i < nodos.size()){
			if(g.DameVecinos(ret).size() < g.DameVecinos(nodos.get(i)).size()){
				ret = nodos.get(i);
			}
			i++;
		}
		
		return ret;
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
}
