package algoritmos;

public class ParametrosGRASP implements Comparable<ParametrosGRASP>
{
	public int iteraciones_max;
	public int iteraciones_sin_cambio;
	public int porcentaje_goloso;
	public int porcentaje_cuantos_saco;
	public int porcentaje_cuantos_agrego;
	
	
	public String toString()
	{
		return "(" + porcentaje_cuantos_agrego + "," + porcentaje_cuantos_saco + ")";
	}

	public int compareTo(ParametrosGRASP p)
	{		
		if( porcentaje_cuantos_agrego == p.porcentaje_cuantos_agrego &&
			porcentaje_cuantos_saco == p.porcentaje_cuantos_saco )
		{
			return 0;
		}
		else
		{
			if(porcentaje_cuantos_agrego < p.porcentaje_cuantos_agrego ||
				(porcentaje_cuantos_agrego == p.porcentaje_cuantos_agrego && porcentaje_cuantos_saco < p.porcentaje_cuantos_saco))
			{
				return -1;
			}
			else
			{
				return 1;
			}
		}
	}
}
