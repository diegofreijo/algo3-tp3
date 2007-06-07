package algoritmos;

import java.util.List;

import utilidades.Grafo;
import utilidades.Parser;

public class Main_Exacto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Grafo g = Parser.LeerGrafo("in/ej2.in");
		
		System.out.println("Leido:\n" + g);
		
		List<Integer> recubrimiento = Exacto.Ejecutar(g);
		
		System.out.println("Exacto: " + recubrimiento.toString());		

	}

}
