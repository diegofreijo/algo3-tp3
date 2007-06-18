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
		int cant_grafos_aleatorios = 486;
		//int cant_grafos_completos = 5;
		
		// Busqueda local
		int porcentaje_cuantos_saco = 10;
		int porcentaje_cuantos_agrego = 00;
		
		// Fin Parametros
		///////////////////////////////////////////////////////////////////
		
				
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", cant_grafos_aleatorios);
		//List<Grafo> completos = Parser.LeerGrafos("completo", cant_grafos_completos);
		
		Recubrimiento rec_exacto, rec_busqueda_local;
		
		
		// Exacto
		//System.out.println("Ejecutando: Busqueda Local");
		Estadisticas est = new Estadisticas("busqueda_local");
		for(Grafo g : aleatorios)
		{
			rec_exacto = Exacto.Ejecutar(g,est);
			rec_busqueda_local = BusquedaLocal.Ejecutar(g, porcentaje_cuantos_saco, porcentaje_cuantos_agrego, est);
			
			if(rec_exacto.nodos.size() != rec_busqueda_local.nodos.size())
			{
				System.out.println("Exacto: " + rec_exacto.nodos.size() + " --> BL: " + rec_busqueda_local.nodos.size());
				System.out.println("Grafo: " + g);
				System.out.println("Rec Exacto: " + rec_exacto);
				System.out.println("Rec BL: " + rec_busqueda_local);
				System.out.println("===========================================================");
			}
			else
			{
				//System.out.println("Ambos dieron " + rec_busqueda_local.nodos.size());
			}
		}
		
		
		
		
/*		// Busqueda Local
		//System.out.println("Ejecutando: Busqueda Local");
		//Estadisticas est_busqueda_local = new Estadisticas("busqueda_local");
		for(Grafo g : aleatorios)
		{
			
			//System.out.println("Busqueda local: " + recubrimiento);
			//System.out.println("long = " + recubrimiento.nodos.size());
			//est_busqueda_local.GuardarResultado(g.DameNodos());
			//est_busqueda_local.GuardarRecubrimiento(recubrimiento);
		}
		//Parser.Escribir(est_busqueda_local);
		
	*/	
		
		System.out.println("Fin de las pruebas");
	}
}
