package algoritmos;

import java.util.List;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Recubrimiento;

public class Main_GolosoRandom {

public static void main(String[] args) {
	
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", 50);
		//List<Grafo> completos = Parser.LeerGrafos("completo", 50);
		
		Recubrimiento recubrimiento;
		
		// Busqueda Local
		System.out.println("Ejecutando: GreedyRandom");
		Estadisticas est_greedyRandom = new Estadisticas("Greedy Random");
		for(Grafo g : aleatorios)
		{
			recubrimiento = GolosoRandom.Ejecutar(g, est_greedyRandom);
			System.out.println("long = " + recubrimiento.nodos.size());
			est_greedyRandom.GuardarResultado(g.DameNodos());
			est_greedyRandom.GuardarRecubrimiento(recubrimiento);
		}
		Parser.Escribir(est_greedyRandom);
		
		System.out.println("Fin de las pruebas");		

	}
	
}
