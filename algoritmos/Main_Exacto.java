package algoritmos;

import java.util.List;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Recubrimiento;

public class Main_Exacto {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Ejecutando: Exacto");
		Estadisticas est_exacto = new Estadisticas("exacto");
		Recubrimiento recubrimiento;
		int cant_grafos_aleatorios = 11;
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", cant_grafos_aleatorios);
		//Grafo g = Parser.LeerGrafo("aleatorio1");
		
		for(Grafo g : aleatorios)
		{
			System.out.println("Grafo: " + g.toString());
			recubrimiento = Exacto.Ejecutar(g);
			System.out.println("long = " + recubrimiento.nodos.size());
			est_exacto.GuardarResultado(g.DameNodos());
			est_exacto.GuardarRecubrimiento(recubrimiento);
		}
		Parser.Escribir(est_exacto);
		
	}

}
