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
		Estadisticas est_exacto = new Estadisticas();
		Recubrimiento recubrimiento;
		int cant_grafos_aleatorios = 30;
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", "chicos", cant_grafos_aleatorios);
		//Grafo g = Parser.LeerGrafo("aleatorio1");
		
		for(Grafo g : aleatorios)
		{
			System.out.println("Grafo: " + g.toString());
			recubrimiento = Exacto.Ejecutar(g,est_exacto);
			System.out.println("long = " + recubrimiento.nodos.size());
		}
		//Parser.Escribir(est_exacto);
		
	}

}
