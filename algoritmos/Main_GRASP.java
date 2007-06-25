package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class Main_GRASP
{
	public static void main(String[] args)
	{
		Estadisticas est_grasp = new Estadisticas();
		Grafico grafico = new Grafico("GRASP","grasp");
		Recubrimiento recubrimiento;
		Grafo g = Parser.LeerGrafoPy(args[0]);
		//Grafo g = Parser.LeerGrafo("aleatorio1.in");
		
		System.out.println("Grafo: " + g.toString());
		
		ParametrosGRASP mejor = new ParametrosGRASP();
		mejor.iteraciones_max = Integer.parseInt(args[3]);
		mejor.iteraciones_sin_cambio = Integer.parseInt(args[4]);
		mejor.porcentaje_cuantos_agrego = Integer.parseInt(args[5]);
		mejor.porcentaje_cuantos_saco = Integer.parseInt(args[6]);
		mejor.porcentaje_goloso = Integer.parseInt(args[7]);
		
		recubrimiento = GRASP.Ejecutar(g,mejor,est_grasp);
		Punto2D punto = new Punto2D(g.DameNodos(),est_grasp.i);
		grafico.Agregar(punto);
		Parser.EscribirGraficoPy(grafico,args[2]);
		Parser.EscribirResultadoPy(recubrimiento, args[1]);
		//Parser.EscribirResultado(recubrimiento, "aleatorio1.in");
	}
}
