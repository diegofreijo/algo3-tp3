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
		return 
			"(" + porcentaje_cuantos_agrego + "," + porcentaje_cuantos_saco + ";" + 
			iteraciones_max + "," + iteraciones_sin_cambio + "," + porcentaje_goloso + 
		")";
	}

	public int compareTo(ParametrosGRASP p)
	{		
		if( porcentaje_cuantos_agrego == p.porcentaje_cuantos_agrego &&
			porcentaje_cuantos_saco == p.porcentaje_cuantos_saco &&
			iteraciones_max == p.iteraciones_max && iteraciones_sin_cambio == p.iteraciones_sin_cambio && porcentaje_goloso == p.porcentaje_goloso)
		{
			return 0;
		}
		else if(porcentaje_cuantos_agrego < p.porcentaje_cuantos_agrego)
		{
			return -1;
		}
		else if(porcentaje_cuantos_saco < p.porcentaje_cuantos_saco)
		{
			return -1;
		}
		else if(iteraciones_max < p.iteraciones_max)
		{
			return -1;
		}
		else if(iteraciones_sin_cambio < p.iteraciones_sin_cambio)
		{
			return -1;
		}
		else if(porcentaje_goloso < p.porcentaje_goloso)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}
