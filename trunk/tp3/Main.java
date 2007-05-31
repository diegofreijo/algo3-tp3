package tp3;

import utilidades.Grafo;

public class Main
{
	public static void main(String[] args)
	{
		Grafo g = Parser.LeerGrafo("in/ej.in");
		
		for(int i = 1; i <= g.DameNodos(); ++i)
		{
			System.out.println(g.DameVecinos(i));
		}
		
		System.out.println(g.DameAislados());
	}
}
