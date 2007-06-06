package algoritmos;

import java.util.List;

import utilidades.Grafo;
import utilidades.Parser;

public class Main_GolosoMain {

	public static void main(String[] args) {
		
		Grafo g = Parser.LeerGrafo("in/ej.in");
		
		System.out.println("Leido:\n" + g);
		
		List<Integer> recubrimiento = Goloso.Ejecutar(g);
		
		System.out.println("Goloso: " + recubrimiento.toString());		

	}

}
