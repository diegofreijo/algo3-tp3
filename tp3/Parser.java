package tp3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilidades.Eje;
import utilidades.Grafo;

public abstract class Parser
{
	static String fs = System.getProperty("file.separator");
	static String ruta_proyecto = System.getProperty("java.class.path");
	
	static String ruta_instancias = ruta_proyecto + fs + "in" + fs + "Tp2Ej1.in";
	static String ruta_consultas = ruta_proyecto + fs + "in" + fs + "Tp2Ej4.in";
	static String ruta_resultados = ruta_proyecto + fs + "out" + fs + "Tp2Ej4.out";
	static String ruta_diferencias = ruta_proyecto + fs + "dat" + fs + "diferencias.txt";
	static String ruta_armado = ruta_proyecto + fs + "dat" + fs + "Tp2(armado).dat";
	static String ruta_consulta = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta).dat";
	static String ruta_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(fusion).dat";
	static String ruta_consulta_mas_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta_mas_fusion).dat";
	static String ruta_fb = ruta_proyecto + fs + "dat" + fs + "Tp2(fb).dat";
	
	// Levanta un grafo del archivo de entrada
	public static Grafo LeerGrafo(String ruta)
	{
		//Grafo ret = new Grafo();
		int n = 0, m = 0;
		String nombre = "";
		List<Eje> ejes = new ArrayList<Eje>();
		
		try
	    {
	        BufferedReader in = new BufferedReader(new FileReader(ruta));
	    
	        // Leo el encabezado, los datos del grafo
	        char[] ca = LeerLinea(in).trim().toCharArray();
	        String buffer = "";
	        int i;
	        for(i = 2; ca[i] != ' '; ++i)		// Leo el nombre
	        {
        		nombre += ca[i];
	        }
	        
	        for(++i; ca[i] != ' '; ++i)			// Leo n
	        {
	        	buffer += ca[i];
	        }
	        n = Integer.valueOf(buffer);
	        buffer = "";
	        
	        for(++i; i < ca.length; ++i)		// Leo m
	        {
	        	buffer += ca[i];
	        }
	        m = Integer.valueOf(buffer);
	        buffer = "";
	        
	        Eje e;
	        for(i = 0; i < m; ++i)
	        {
	        	ca = LeerLinea(in).trim().toCharArray();
	        	e = new Eje();
	        	
	        	// Leo ambos nodos y creo el eje
	        	int j;
	        	buffer = "";
	        	for(j = 2; ca[j] != ' '; ++j)
		        {
		        	buffer += ca[j];
		        }
		        e.x = Integer.valueOf(buffer);
		        buffer = "";
		        for(++j; j < ca.length; ++j)
		        {
		        	buffer += ca[j];
		        }
		        e.y = Integer.valueOf(buffer);
		        
		        // Y lo agrego a la lista de ejes
		        ejes.add(e);
	        }
	        
	        in.close();
	    } 
	    catch (IOException e)
	    {
	    	System.out.println("Error leyendo el grafo de entrada: ");
	    	e.printStackTrace();
	    }
	    
	    // Ahora que tengo todo lo que necesito, me armo el grafo y lo devuevlo
	    Grafo G = new Grafo(n, ejes);
	    
	    return G;
	}

	// Lee la siguiente linea de in que no sea un comentario
	private static String LeerLinea(BufferedReader in) throws IOException
    {
		String linea;
		do
		{
			linea = in.readLine();
		}
		while(linea.toCharArray()[0] == 'c');
		
		return linea;
    }
	
	// Escribe el resultado de una instancia.
	public static void EscribirResultados(List<ResultadoConsulta> resultados)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_resultados, true));
			if(resultados.size() == 1)
			{
				out.write(resultados.get(0).toString() + "0");
			}
			else
			{
				out.write(resultados.get(0).toString());
				for(int i = 1; i < resultados.size(); ++i)
				{
					out.write("1\n" + resultados.get(i).toString());
				}
				out.write("0\n");
			}
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo los resultados: ");
	    	e.printStackTrace();
		}
	}
	
	// Escribe las diferencias encontradas entre el algo eficiente y el de FB.
	public static void EscribirDiferencias(Diferencias diferencias)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_diferencias, true));
			out.write(diferencias.toString());
	        out.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo las diferencias: ");
	    	e.printStackTrace();
		}
	}
	
	// Escribe la cantidad de instrucciones ejecutadas para una instancia
	public static void EscribirEstadisticas(Estadisticas es)
    {
		try
		{
			// Cantidad de operaciones de armado en funcion de la cantidad de imagenes
			BufferedWriter out = new BufferedWriter(new FileWriter(ruta_armado, true));
	        out.write(es.cant_img + " " + es.armado + "\n");
	        out.close();
	        
	        // Cantidad de operaciones de consulta en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_consulta, true));
	        for(Long op: es.consultas)
	        {
	        	out.write(es.cant_img + " " + op + "\n");
	        }
	        out.close();
	        
	        // Cantidad de operaciones de fusion en funcion del producto de la cantidad de intervalos de ambos ejes 
	        out = new BufferedWriter(new FileWriter(ruta_fusion, true));
	        for(Long op: es.fusiones)
	        {
	        	out.write(es.cant_img + " " + op + "\n");
	        }
	        out.close();
	        
	        // Suma de las 2 anteriores en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_consulta_mas_fusion, true));
	        for(int i = 0; i < es.consultas.size(); ++i)
	        {
	        	out.write(es.cant_img + " " + (es.consultas.get(i) + es.fusiones.get(i)) + "\n");
	        }
	        out.close();
	        
	        // Cantidad de operaciones del algoritmo de fuerza bruta en funcion de la cantidad de imagenes
	        out = new BufferedWriter(new FileWriter(ruta_fb, true));
	        for(Long op: es.fbs)
	        {
	        	out.write(es.cant_img + " " + op + "\n");
	        }
	        out.close();
	        
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo las estadisticas: ");
	    	e.printStackTrace();
		}
    }
	
	// Borra todos los archivos de salida de datos
	public static void LimpiarArchivos()
	{
		File f = new File(ruta_resultados); f.delete();
		f = new File(ruta_diferencias);	f.delete();
		f = new File(ruta_armado); f.delete();
		f = new File(ruta_consulta); f.delete();
		f = new File(ruta_fusion); f.delete();
		f = new File(ruta_consulta_mas_fusion); f.delete();
		f = new File(ruta_fb); f.delete();
	}
}