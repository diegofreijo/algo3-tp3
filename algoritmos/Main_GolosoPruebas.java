package algoritmos;
import java.util.ArrayList;
import java.util.List;

import utilidades.Cronometro;
import utilidades.Estadisticas;
import utilidades.Grafico;
import utilidades.Grafo;
import utilidades.Parser;
import utilidades.Punto2D;
import utilidades.Recubrimiento;

public class Main_GolosoPruebas {

	public static void main(String[] args) {
		
		System.out.println("Leyendo grafos");
		List<Grafo> aleatorios = Parser.LeerGrafos("aleatorio", "" ,100);
		//List<Grafo> completos = Parser.LeerGrafos("completo", 50);
		
		Recubrimiento recubrimiento;
		
		List<Recubrimiento> Recubrimientos = new ArrayList<Recubrimiento>();
		List<Recubrimiento> RecubrimientosExactos = new ArrayList<Recubrimiento>();
		
		Estadisticas est_goloso = new Estadisticas();
		Estadisticas est_exacto = new Estadisticas();
		Grafico graficoGoloso = new Grafico("Goloso","instruccionesGolosoNodos");
		Grafico graficoExacto = new Grafico("Exacto","InstruccionesExactoxNodo");
		Grafico graficoGvsE = new Grafico("Goloso","GvsExnodos");
		Cronometro crono = new Cronometro();
		int i = 1;
		for(Grafo g : aleatorios)
		{
			est_goloso.i = 0;
			est_exacto.i = 0;
			System.out.println("Grafo: " + i);
			i++;
			//Calcular Densidad para grafico
			//int totalposibles = g.DameNodos()*(g.DameNodos()-1)/2;
			//Integer densidad = g.DameEjes().size()*100/totalposibles;
			//System.out.println("Densidad: " + densidad);
			
			System.out.println("Ejecutando: Goloso");
			recubrimiento = Goloso.Ejecutar(g, est_goloso);
			Recubrimientos.add(recubrimiento);
			//Punto2D punto = new Punto2D(g.DameNodos(),est_goloso.i);
			//graficoGoloso.Agregar(punto);
			int tamaņogoloso = recubrimiento.nodos.size();
					
			System.out.println("Ejecutando: Exacto");
			recubrimiento = Exacto.Ejecutar(g,est_exacto);
			RecubrimientosExactos.add(recubrimiento);
			Punto2D puntoExacto = new Punto2D(g.DameNodos(),est_exacto.i);
			graficoExacto.Agregar(puntoExacto);
			Punto2D puntoGvsE = new Punto2D(g.DameNodos(),tamaņogoloso-recubrimiento.nodos.size());
			graficoGvsE.Agregar(puntoGvsE);
		}
		//Parser.Escribir(est_goloso);
		//Parser.Escribir(est_exacto);
		//Parser.EscribirGrafico(graficoGoloso);
		Parser.EscribirGrafico(graficoExacto);
		Parser.EscribirGrafico(graficoGvsE);
		
		
		/*int diferencias = 0;
		i = 0;
		
		while(i < Recubrimientos.size()){	
			int sumar = Recubrimientos.get(i).nodos.size() - RecubrimientosExactos.get(i).nodos.size();
			diferencias += sumar;
			i++;
		}
		
		System.out.println("Diferencias con Exactos:");
		System.out.println(diferencias);
		System.out.println("Promedio por grafo:");
		float dif = diferencias/Recubrimientos.size();
		System.out.println(dif);*/
		
		System.out.println("Fin de las pruebas");	
		System.out.println(crono.VerSegundos());
	}

}
