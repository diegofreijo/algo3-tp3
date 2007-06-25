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
	plot '..\..\dat\Exacto\Exacto(InstruccionesExactoxNodo).dat' tit 'Cantidad de instrucciones en la práctica'
	
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
	
# Busqueda Local:
	# Comparacion de parametros (3D)
		set xlabel "% cuantos agrego"
		set ylabel "% cuantos saco"
		set zlabel "Puntaje"
		#set xrange [0:100]
		#set yrange [0:100]
		#set zrange [50:500]
		set output "BusquedaLocal(comparacion_parametros).png"
		splot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_parametros).dat' tit 'Puntajes'

	# Cantidad de instrucciones en funcion de los ejes del mejor parametro
		set xlabel "Densidad del grafo"
		set ylabel "Cantidad de instrucciones"
		set output "BusquedaLocal(instrucciones_ejes).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_ejes).dat' tit 'Cantidad de instrucciones en la práctica'
		
	# Cantidad de instrucciones en funcion de los nodos del mejor parametro
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "BusquedaLocal(instrucciones_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_nodos).dat' tit 'Cantidad de instrucciones en la práctica'

	# Comparacion con exacto del mejor parametro
		set xlabel "Densidad del grafo"
		set ylabel "Diferencias con el exacto"
		set output "BusquedaLocal(comparacion_exacto).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_exacto).dat' tit 'Diferencias'

# GRASP:
	# Comparacion de parametros (no es un grafico a menos que sepas como graficar en 5D)
	# Cantidad de instrucciones del mejor parametro
	# Comparacion con exacto
	
# Finales:
	# Comparacion de las 3 heuristicas:
		# Aleatorio (en funcion de la densidad)
		# Bipartito (en funcion de la densidad)
		# Rueda (en funcion de la cantidad de nodos)
		# Completo (en funcion de la cantidad de nodos


set output
set terminal win
