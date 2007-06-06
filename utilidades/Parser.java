package utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Parser
{
	private static String fs = System.getProperty("file.separator");
	private static String ruta_proyecto = System.getProperty("java.class.path");
	
	private static String ruta_in = ruta_proyecto + fs + "in";
	private static String ruta_dat = ruta_proyecto + fs + "dat";
	private static String ruta_out = ruta_proyecto + fs + "out";
	private static String nombre_dat = "Tp3";
	private static String nombre_out = "Tp3";
	
	
	/*
	private static String ruta_instancias = ruta_proyecto + fs + "in" + fs + "Tp2Ej1.in";
	private static String ruta_consultas = ruta_proyecto + fs + "in" + fs + "Tp2Ej4.in";
	private static String ruta_resultados = ruta_proyecto + fs + "out" + fs + "Tp2Ej4.out";
	private static String ruta_diferencias = ruta_proyecto + fs + "dat" + fs + "diferencias.txt";
	private static String ruta_armado = ruta_proyecto + fs + "dat" + fs + "Tp2(armado).dat";
	private static String ruta_consulta = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta).dat";
	private static String ruta_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(fusion).dat";
	private static String ruta_consulta_mas_fusion = ruta_proyecto + fs + "dat" + fs + "Tp2(consulta_mas_fusion).dat";
	private static String ruta_fb = ruta_proyecto + fs + "dat" + fs + "Tp2(fb).dat";
	*/
	
	
	// Lee @instancias grafos del tipo @tipo, por lo que ya tienen que haber sido generados con el script
	public static List<Grafo> LeerGrafos(String tipo, int instancias)
	{
		List<Grafo> grafos = new ArrayList<Grafo>(instancias);
		String archivo_actual = "";
		
		for(Integer i = 1; i <= instancias; ++i)
		{
			archivo_actual = ruta_in + fs + tipo + fs + tipo + i.toString() + ".in";
			grafos.add(LeerGrafo(archivo_actual));
		}
		
		return grafos;
	}
	
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
	/*public static void EscribirResultados(List<ResultadoConsulta> resultados)
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
	}*/
	
	// Escribe las diferencias encontradas entre el algo eficiente y el de FB.
	/*public static void EscribirDiferencias(Diferencias diferencias)
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
	}*/
	
	// Escribe la cantidad de instrucciones ejecutadas para una instancia y los tamaños de los recubrimientos
	public static void Escribir(Estadisticas est)
    {
		try
		{
			// Genero el nombre del archivo dat en funcion del nombre del algoritmo
			String arhivo = nombre_dat + "(" + est.NombreAlgoritmo() + ").dat";
			
			// Guardo los valores en las estadisticas
			BufferedWriter salida = new BufferedWriter(new FileWriter(ruta_dat + fs + arhivo, false));
	        salida.write(est.Puntos());
	        salida.close();
	        
	        
	        // Genero el nombre del archivo out en funcion del nombre del algoritmo
			arhivo = nombre_out + "(" + est.NombreAlgoritmo() + ").out";
			
			// Guardo los valores en las estadisticas
			salida = new BufferedWriter(new FileWriter(ruta_out + fs + arhivo, false));
	        salida.write(est.Recubrimientos());
	        salida.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo estadisticas/recubrimientos: ");
	    	e.printStackTrace();
		}
    }
	
	// Borra todos los archivos de salida de datos
	/*public static void LimpiarArchivos(List<String> tipos)
	{
		File f;
		
		for(String tipo : tipos)
		{
			// Borro el dat
			f = new File(ruta_dat + "/" + nombre_dat + "(" + tipo + ").dat");
			f.delete();
		}
	}*/
}