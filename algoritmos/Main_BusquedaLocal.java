package algoritmos;

import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class Main_BusquedaLocal
{
	public static void main(String[] args)
	{
		
		Estadisticas est_bl = new Estadisticas();
		Grafico grafico = new Grafico("BusquedaLocal","busquedalocal");
		Recubrimiento recubrimiento;
		Grafo g = Parser.LeerGrafoPy(args[0]);
		//Grafo g = Parser.LeerGrafo("aleatorio1.in");
		
		ParametrosBL param = new ParametrosBL();
		param.porcentaje_cuantos_agrego = Integer.parseInt(args[4]);
		param.porcentaje_cuantos_saco = Integer.parseInt(args[3]);
		
		System.out.println("Grafo: " + g.toString());
		recubrimiento = BusquedaLocal.Ejecutar(g,param,null,est_bl);
		Punto2D punto = new Punto2D(g.DameNodos(),est_bl.i);
		grafico.Agregar(punto);
		Parser.EscribirGraficoPy(grafico,args[2]);
		Parser.EscribirResultadoPy(recubrimiento, args[1]);
	}
}
