package algoritmos;

import java.util.List;

import tp3.Parser;
import utilidades.Grafo;

public class Main_BusquedaLocal
{
	public static void main(String[] args)
	{
		Grafo g = Parser.LeerGrafo("in/ej.in");
		
		System.out.println("Leido:\n" + g);
		
		List<Integer> recubrimiento = BusquedaLocal.Ejecutar(g);
		
		System.out.println("Busqueda local: " + recubrimiento.toString());		
	}
}
