package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafo;
import utilidades.Recubrimiento;

public abstract class GRASP
{
	private static Estadisticas e;
	
	public static Recubrimiento Ejecutar(Grafo g, 
			int iteraciones_max, int iteraciones_sin_cambio, 
			int porcentaje_cuantos_saco, int porcentaje_cuantos_agrego,
			Estadisticas est)
	{
		e = est;
		Recubrimiento solucion = null;
		do
		{
			solucion = SolucionGolosaAzarosa(g);
			BusquedaLocal.Ejecutar(g, porcentaje_cuantos_saco, porcentaje_cuantos_agrego, e);
		}
		while(!CondicionesDeParada());
		
		return solucion;
	}
	
	private static boolean CondicionesDeParada()
    {

	    return false;
    }

	private static Recubrimiento SolucionGolosaAzarosa(Grafo g)
	{
		
		return null;
	}
	
}
