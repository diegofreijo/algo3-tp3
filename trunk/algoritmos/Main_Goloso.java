package algoritmos;

import java.util.List;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Recubrimiento;

public class Main_Goloso {

	public static void main(String[] args) {
		
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", 50);
		//List<Grafo> completos = Parser.LeerGrafos("completo", 50);
		
		Recubrimiento recubrimiento;
		
		// Busqueda Local
		System.out.println("Ejecutando: Goloso");
		Estadisticas est_goloso = new Estadisticas("Goloso");
		for(Grafo g : aleatorios)
		{
			recubrimiento = Goloso.Ejecutar(g, est_goloso);
			System.out.println("long = " + recubrimiento.nodos.size());
			est_goloso.GuardarResultado(g.DameNodos());
			est_goloso.GuardarRecubrimiento(recubrimiento);
		}
		Parser.Escribir(est_goloso);
		
		System.out.println("Fin de las pruebas");	

	}

}
