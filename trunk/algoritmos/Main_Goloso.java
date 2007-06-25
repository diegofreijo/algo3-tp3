package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class Main_Goloso {

public static void main(String[] args) {
		
		System.out.println("Ejecutando: Goloso");
		Estadisticas est_goloso = new Estadisticas();
		Grafico grafico = new Grafico("Goloso","goloso");
		Recubrimiento recubrimiento;
		//Grafo g = Parser.LeerGrafo(args[0]);
		Grafo g = Parser.LeerGrafo("aleatorio1.in");
		
		System.out.println("Grafo: " + g.toString());
		recubrimiento = Goloso.Ejecutar(g,est_goloso);
		Punto2D punto = new Punto2D(g.DameNodos(),est_goloso.i);
		grafico.Agregar(punto);
		Parser.EscribirGrafico(grafico);
		//Parser.EscribirResultado(recubrimiento, args[0]);
		Parser.EscribirResultado(recubrimiento, "aleatorio1.in");
	}
	
}
