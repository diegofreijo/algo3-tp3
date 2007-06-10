package algoritmos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public class GolosoRandom {

	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g, Estadisticas est){
		
		e = est;
		
		List<Integer> nodos = SacarAislados(g.DameNodos(),g.DameAislados());
		
		Recubrimiento solucion = new Recubrimiento(g.DameNodos(),e);
		
		System.out.println("Ejes: " + g.DameEjes());		
		
		while(solucion.EsRecubrimiento(g)){
			++e.i;
			Integer nodoMayor = NodoMayorGrado(nodos,g);
			++e.i;
			solucion.nodos.add(nodoMayor);
			System.out.println("Ejes Cubiertos: " + g.DameVecinos(nodoMayor).toString());
			++e.i;
			nodos.remove(nodoMayor);
		}
		
		return solucion;
		
	}

	private static int NodoMayorGrado(List<Integer> nodos, Grafo g) {
		
		int i = 0;
		
		List<Integer> temp = new ArrayList<Integer>();
		++e.i;
		int mayor = nodos.get(1);
		
		while(i < nodos.size()){
			++e.i;
			if(g.DameVecinos(mayor).size() < g.DameVecinos(nodos.get(i)).size()){
				++e.i;
				mayor = nodos.get(i);++e.i;
				temp.clear();++e.i;
				temp.add(nodos.get(i));++e.i;
			} else if (g.DameVecinos(mayor).size() == g.DameVecinos(nodos.get(i)).size()) {
				++e.i;
				temp.add(nodos.get(i));++e.i;
			}
			++e.i;
			i++;
		}
		
		Random rnd = new Random(temp.size() + 1235689 / 10 * 8);
		
		int ret = rnd.nextInt(temp.size());++e.i;
		
		return temp.get(ret);
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
