package algoritmos;

import java.util.ArrayList;
import java.util.List;

import utilidades.Eje;
import utilidades.Grafo;

public abstract class Goloso {
	
	public static List<Integer> Ejecutar(Grafo g){
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados());
		
		List<Integer> solucion = new ArrayList<Integer>();
		
		System.out.println("Ejes: " + g.DameEjes());		
		
		while(!EsRecubrimiento(solucion, g)){
			Integer nodoMayor = NodoMayorGrado(nodos,g);
			solucion.add(nodoMayor);
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

	private static List<Integer> SacarAislados(int n, List<Integer> aislados) {
		
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
