package grafo;

import java.util.ArrayList;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Eje a = new Eje();
		a.x = 1;
		a.y = 2;
		
		Eje b = new Eje();
		b.x = 3;
		b.y = 6;
		
		Eje c = new Eje();
		c.x = 3;
		c.y = 8;
		
		List<Eje> lista = new ArrayList<Eje>();
		lista.add(a);
		lista.add(b);
		lista.add(c);
	
		Grafo grafo = new Grafo(8,lista);
		
		System.out.println(grafo.DameVecinos(5));
		System.out.println(grafo.DameVecinos(3));
		System.out.println(grafo.DameVecinos(8));
	}

}
