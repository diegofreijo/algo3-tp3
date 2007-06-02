package algoritmos;

import java.util.List;

import tp3.Parser;
import utilidades.Grafo;

public class Main_GolosoMain {

	public static void main(String[] args) {
		
		Grafo g = Parser.LeerGrafo("in/ej.in");
		
		System.out.println("Leido:\n" + g);
		
		List<Integer> recubrimiento = Goloso.Ejecutar(g);
		
		System.out.println("Goloso: " + recubrimiento.toString());		

	}

}
