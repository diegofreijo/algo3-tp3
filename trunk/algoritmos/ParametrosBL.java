package algoritmos;

import java.util.Comparator;

public class ParametrosBL
{
	public boolean mezclar_nodos_busqueda_vecino = false;
	public int porcentaje_cuantos_saco;
	public int porcentaje_cuantos_agrego;
	
	public String toString()
	{
		return "(" + porcentaje_cuantos_agrego + "," + porcentaje_cuantos_saco + ")";
	}
}
