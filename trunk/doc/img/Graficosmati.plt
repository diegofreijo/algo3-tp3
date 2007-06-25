#########################################################
# Graficos:
#########################################################

set style data points
set term png

# Exacto:
	# Cantidad de instrucciones
	set xlabel "Densidad del grafo"
	set ylabel "Cantidad de instrucciones"
	set output "Exacto(InstruccionesExactoxNodo).png"
	plot '..\..\dat\Exacto\Exacto(Instrucciones).dat' tit 'Cantidad de instrucciones en la práctica'
	# Cantidad de instrucciones x nodos
	set xlabel "Nodos del grafo"
	set ylabel "Cantidad de instrucciones"
	set output "Exacto(InstruccionesExactoxNodo).png"
	plot '..\..\dat\Exacto\Exacto(InstruccionesExactoxNodo).dat' tit 'Cantidad de instrucciones en la práctica', 4*((2**x)*x) tit 'Teorica [2^n*n]'	
# Goloso:
	# Comparacion exacto
		set xlabel "Densidad del grafo"
		set ylabel "Diferencias con el exacto"
		set output "Goloso(GvsE).png"
		plot '..\..\dat\Goloso\Goloso(GvsE).dat' tit 'Diferencias'
	
	# Comparacion exacto por nodos
		set xlabel "Nodos del grafo"
		set ylabel "Diferencias con el exacto"
		set output "Goloso(GvsExnodos).png"
		plot '..\..\dat\Goloso\Goloso(GvsExnodos).dat' tit 'Diferencias'
		
	# Cantidad de instrucciones
		set xlabel "Densidad del grafo"
		set ylabel "Cantidad de instrucciones"
		set output "Goloso(instrucciones).png"
		plot '..\..\dat\Goloso\Goloso(iteracionesGoloso).dat' tit 'Cantidad de instrucciones en la práctica', 2*(20**2+20*x+x) tit 'Teorica ajustada'
	
	# Cantidad de instrucciones por nodos
		set xlabel "Nodos del grafo"
		set ylabel "Cantidad de instrucciones"
		set output "Goloso(instruccionesXnodo).png"
		plot '..\..\dat\Goloso\Goloso(instruccionesGolosoNodos).dat' tit 'Cantidad de instrucciones en la práctica', 8*(x**2+x) tit 'Teorica Ajustada [8(n^2+n)]', x**2+x tit 'Teorica [n^2+n]'


set output
set terminal win
