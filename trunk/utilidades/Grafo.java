package utilidades;

import java.util.ArrayList;
import java.util.List;

public class Grafo
{
	private int nodos;
	private List<Eje> ejes;
	private List<List<Integer>> adyacencias;
	private List<Integer> aislados;

	public Grafo(int nodos, List<Eje> ejes)
	{
		this.nodos = nodos;
		this.ejes = ejes;

		List<Integer> aislados = new ArrayList<Integer>();
		List<List<Integer>> ady = new ArrayList<List<Integer>>();

		List<Integer> temp = new ArrayList<Integer>();
		int i = 1;
	
		while (i <= nodos)
		{
			int k = 0;
			temp = new ArrayList<Integer>();

			while (k < ejes.size())
			{
				if (ejes.get(k).x == i)
				{
					temp.add(ejes.get(k).y);
				} else if (ejes.get(k).y == i)
				{
					temp.add(ejes.get(k).x);
				}
				k++;
			}
			if(temp.size() == 0){
				aislados.add(i);
			}
			ady.add(temp);
			i++;
		}

		this.adyacencias = ady;
		this.aislados = aislados;
	}


	public int DameNodos()
	{
		return this.nodos;
	}

	public List<Eje> DameEjes()
	{
		return this.ejes;
	}

	public List<Integer> DameVecinos(int nodo)
	{
		return this.adyacencias.get(nodo-1);
	}
	
	public List<Integer> DameAislados()
	{
		return this.aislados;
	}
	
	public String toString()
	{
		return "Grafo de " + nodos + " nodos: " + adyacencias;
	}
}
