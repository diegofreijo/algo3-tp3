package algoritmos;

public class ParametrosBL implements Comparable<ParametrosBL>
{
	public boolean mezclar_nodos_busqueda_vecino = false;
	public int porcentaje_cuantos_saco;
	public int porcentaje_cuantos_agrego;
	
	public String toString()
	{
		return "(" + porcentaje_cuantos_agrego + "," + porcentaje_cuantos_saco + ")";
	}

	public int compareTo(ParametrosBL p)
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
