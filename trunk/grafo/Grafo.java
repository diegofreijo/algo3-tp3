package grafo;

import java.util.List;

public class Grafo {

	public int nodos;
	public List<Eje> ejes;
	public List<List<Eje>> adyacencias;
	
	public Grafo(int nodos,List<Eje> ejes){
		
		this.nodos = nodos;
		this.ejes = ejes;
		
		
		
		
	}
	
	
	public int DameNodos(){
		return this.nodos;
	}
	
	public List<Eje> DameEjes(){
		return this.ejes;
	}
	
	public List<Eje> DameVecinos(int nodo){
		return null;
	}
	
}
