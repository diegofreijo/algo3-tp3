package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class Main_Exacto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Ejecutando: Exacto");
		Estadisticas est_exacto = new Estadisticas();
		Grafico grafico = new Grafico("Exacto","exacto");
		Recubrimiento recubrimiento;
		Grafo g = Parser.LeerGrafoPy(args[0]);
		//Grafo g = Parser.LeerGrafo("aleatorio1.in");
		
		System.out.println("Grafo: " + g.toString());
		recubrimiento = Exacto.Ejecutar(g,est_exacto);
		Punto2D punto = new Punto2D(g.DameNodos(),est_exacto.i);
		grafico.Agregar(punto);
		Parser.EscribirGraficoPy(grafico,args[2]);
		Parser.EscribirResultadoPy(recubrimiento, args[1]);
		//Parser.EscribirResultado(recubrimiento, "aleatorio1.in");
	}

}
