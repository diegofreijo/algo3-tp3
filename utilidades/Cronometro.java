package utilidades;

public class Cronometro
{
	long inicio;
	
	public Cronometro()
	{
		inicio = System.currentTimeMillis();
	}
	
	public long VerSegundos()
	{
		return (System.currentTimeMillis() - inicio) / 1000;
	}
	
	public long VerMinutos()
	{
		return (System.currentTimeMillis() - inicio) / 60;
	}	
}
