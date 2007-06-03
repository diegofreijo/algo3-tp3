package tp3;

import java.util.List;
import utilidades.*;
import algoritmos.*;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", 5);
		
		Recubrimiento recubrimiento;
		
		
		
		// Busqueda Local
		System.out.println("Ejecutando: Busqueda Local");
		Estadisticas est_busqueda_local = new Estadisticas("busqueda_local");
		for(Grafo g : aleatorios)
		{
			recubrimiento = BusquedaLocal.Ejecutar(g, est_busqueda_local);
			est_busqueda_local.GuardarResultado(g.DameNodos());
			est_busqueda_local.GuardarRecubrimiento(recubrimiento);
		}
		Parser.Escribir(est_busqueda_local);
		
		
		
		System.out.println("Fin de las pruebas");
	}
}
