package tp3;

import java.util.List;
import utilidades.*;
import algoritmos.*;

public class Main
{
	public static void main(String[] args)
	{
		///////////////////////////////////////////////////////////////////
		// Parametros
		int cant_grafos_aleatorios = 1;
		
		// Busqueda local
		int porcentaje_cuantos_saco = 20;
		int porcentaje_cuantos_agrego = 10;
		
		// Fin Parametros
		///////////////////////////////////////////////////////////////////
		
				
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", cant_grafos_aleatorios);
		
		Recubrimiento recubrimiento;
		
		
		
		// Busqueda Local
		System.out.println("Ejecutando: Busqueda Local");
		Estadisticas est_busqueda_local = new Estadisticas("busqueda_local");
		for(Grafo g : aleatorios)
		{
			recubrimiento = BusquedaLocal.Ejecutar(g, porcentaje_cuantos_saco, porcentaje_cuantos_agrego, est_busqueda_local);
			System.out.println("Busqueda local: " + recubrimiento + " (long = " + recubrimiento.nodos.size() + ")");
			est_busqueda_local.GuardarResultado(g.DameNodos());
			est_busqueda_local.GuardarRecubrimiento(recubrimiento);
		}
		Parser.Escribir(est_busqueda_local);
		
		
		
		System.out.println("Fin de las pruebas");
	}
}
