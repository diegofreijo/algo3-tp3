package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {

	private int nodos;
	private List<Eje> ejes;
	private List<List<Integer>> adyacencias;
	
	public Grafo(int nodos,List<Eje> ejes){
		
		this.nodos = nodos;
		this.ejes = ejes;
		
		List<List<Integer>> ady = new ArrayList<List<Integer>>();
		ady.add(0, null);
		
		List<Integer> temp = new ArrayList<Integer>();
		int i = 1;
		
		while(i <= nodos){		
			int k = 0;
			temp = new ArrayList<Integer>();
		
			while(k < ejes.size()){			
				if(ejes.get(k).x == i){
					temp.add(ejes.get(k).y);
				}			
				if(ejes.get(k).y == i){
					temp.add(ejes.get(k).x);
				}
				k++;
			}
			ady.add(i, temp);
			i++;
		}
		
		this.adyacencias = ady;
	}
	
	
	public int DameNodos(){
		return this.nodos;
	}
	
	public List<Eje> DameEjes(){
		return this.ejes;
	}
	
	public List<Integer> DameVecinos(int nodo){
		return this.adyacencias.get(nodo);
	}
	
}
