package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Recubrimiento;

public abstract class Correctitud_BL
{
	public static void main(String[] args)
	{
		Estadisticas e = new Estadisticas();
		ParametrosBL parametros = new ParametrosBL();
		parametros.porcentaje_cuantos_agrego = 40;
		parametros.porcentaje_cuantos_saco = 70;
		
		Grafo g = Parser.LeerGrafo("prueba2.in");
		
		BusquedaLocal.Ejecutar(g, parametros, null, e);
	}
}
