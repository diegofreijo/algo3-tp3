package tp3;

import java.util.List;

import algoritmos.BusquedaLocal;
import algoritmos.GRASP;
import algoritmos.Goloso;
import algoritmos.ParametrosBL;
import algoritmos.ParametrosGRASP;

import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class MainPruebas
{
	public static void main(String[] args)
	{		
		String Tipo = "aleatorio";
		
		System.out.println("Leyendo grafos...");
		List<Grafo> grafos = Parser.LeerGrafos(Tipo, "grandes_nodos", 100);

		Grafico instruccionesGoloso = new Grafico(Tipo,"instruccionesGoloso"+Tipo);
		Grafico tamañoSolGoloso = new Grafico(Tipo,"tamañoGoloso"+Tipo);
		
		Grafico instruccionesBL = new Grafico(Tipo,"instruccionesBL"+Tipo);
		Grafico tamañoSolBL = new Grafico(Tipo,"tamañoBL"+Tipo);
		
		Grafico instruccionesGRASP = new Grafico(Tipo,"instruccionesGRASP"+Tipo);
		Grafico tamañoSolGRASP = new Grafico(Tipo,"tamañoGRASP"+Tipo);
		
		Estadisticas est_goloso = new Estadisticas();
		Estadisticas est_bl = new Estadisticas();
		Estadisticas est_grasp = new Estadisticas();
		Recubrimiento recubrimiento;
		
		Punto2D p;
		Punto2D p1;
		Punto2D p2;
		Punto2D p3;
		Punto2D p4;
		Punto2D p5;
		
		for(Grafo g : grafos)
		{			
			est_goloso.i = 0;
			recubrimiento = Goloso.Ejecutar(g,est_goloso);
			p = new Punto2D(g.DameNodos(),recubrimiento.nodos.size());
			tamañoSolGoloso.Agregar(p);
			p1 = new Punto2D(g.DameNodos(),est_goloso.i);
			instruccionesGoloso.Agregar(p1);
			
			est_bl.i = 0;
			ParametrosBL parametros = new ParametrosBL();
			parametros.porcentaje_cuantos_agrego = 4;
			parametros.porcentaje_cuantos_saco = 7;
			recubrimiento = BusquedaLocal.Ejecutar(g,parametros,null,est_bl);
			p2 = new Punto2D(g.DameNodos(),recubrimiento.nodos.size());
			tamañoSolBL.Agregar(p2);
			p3 = new Punto2D(g.DameNodos(),est_bl.i);
			instruccionesBL.Agregar(p3);
			
			est_grasp.i = 0;
			ParametrosGRASP parametrosG = new ParametrosGRASP();
			parametrosG.porcentaje_cuantos_agrego = 4;
			parametrosG.porcentaje_cuantos_saco = 7;
			parametrosG.iteraciones_max = 200000000;
			parametrosG.iteraciones_sin_cambio = 5;
			parametrosG.porcentaje_goloso = 55;
			recubrimiento = GRASP.Ejecutar(g,parametrosG,est_grasp);
			p4 = new Punto2D(g.DameNodos(),recubrimiento.nodos.size());
			tamañoSolGRASP.Agregar(p4);
			p5 = new Punto2D(g.DameNodos(),est_grasp.i);
			instruccionesGRASP.Agregar(p5);	
		}
			
		Parser.EscribirGrafico(tamañoSolGoloso);
		Parser.EscribirGrafico(tamañoSolBL);
		Parser.EscribirGrafico(tamañoSolGRASP);
		Parser.EscribirGrafico(instruccionesGoloso);
		Parser.EscribirGrafico(instruccionesBL);
		Parser.EscribirGrafico(instruccionesGRASP);
		
		System.out.println("Fin de las pruebas");
	}

}
