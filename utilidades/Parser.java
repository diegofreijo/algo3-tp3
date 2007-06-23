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
	
	// Lee @instancias grafos del tipo @tipo, por lo que ya tienen que haber sido generados con el script. El subtipo solo sirve si el tipo es "aleatorio".
	public static List<Grafo> LeerGrafos(String tipo, String subtipo, int instancias)
	{
		List<Grafo> grafos = new ArrayList<Grafo>(instancias);
		String archivo_actual = "";
		
		for(Integer i = 1; i <= instancias; ++i)
		{
			archivo_actual = ruta_in + fs + tipo + fs + (tipo == "aleatorio" ? fs + subtipo : "") + tipo + i.toString() + ".in";
			grafos.add(LeerGrafo(archivo_actual));
		}
		
		return grafos;
	}
	
	// Levanta un grafo del archivo de entrada
	private static Grafo LeerGrafo(String ruta)
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
	
	// Escribe el dat de un grafico
	public static void EscribirGrafico(Grafico grafico)
    {
		try
		{
			// Genero el nombre del archivo dat en funcion del nombre del algoritmo
			String arhivo = ruta_dat + fs + grafico.Algoritmo() + fs + grafico.Algoritmo() + "(" + grafico.Nombre() + ").dat";
			
			// Guardo los valores en las estadisticas
			BufferedWriter salida = new BufferedWriter(new FileWriter(arhivo, false));
	        salida.write(grafico.GenerarDat());
	        salida.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo el grafico " + grafico.Nombre() + ": ");
	    	e.printStackTrace();
		}
    }
	
	// Escribe los resultados de una prueba que es solamente texto
	public static void EscribirPlano(String algoritmo, String nombre, String resultados)
    {
		try
		{
			// Genero el nombre del archivo txt en funcion de lo que me pasaron
			String arhivo = ruta_dat + fs + algoritmo + fs + algoritmo + "(" + nombre + ").txt";
			
			// Guardo los valores en las estadisticas
			BufferedWriter salida = new BufferedWriter(new FileWriter(arhivo, false));
	        salida.write(resultados);
	        salida.close();
		}
		catch (IOException e)
		{
	    	System.out.println("Error escribiendo los resultados '" + nombre + "': ");
	    	e.printStackTrace();
		}
    }
}
