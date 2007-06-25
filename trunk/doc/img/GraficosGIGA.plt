#########################################################
# Graficos:
#########################################################

set style data points
set term png

# Busqueda Local:
	# Comparacion de parametros (3D)
		set xlabel "% cuantos agrego"
		set ylabel "% cuantos saco"
		set zlabel "Puntaje"
		set output "BusquedaLocal(comparacion_parametros).png"
		splot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_parametros).dat' tit 'Puntajes'

	# Cantidad de instrucciones en funcion de los ejes del mejor parametro
		set xlabel "Densidad del grafo"
		set ylabel "Cantidad de instrucciones"
		set yrange [0:450000]
		set output "BusquedaLocal(instrucciones_ejes).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_ejes).dat' tit 'Práctica', 64000*x tit 'Teórica[40^3 * m]'
		
	# Cantidad de instrucciones en funcion de los nodos del mejor parametro
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set yrange [0:600000]
		set output "BusquedaLocal(instrucciones_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_nodos).dat' tit 'Práctica', x*x*x*600 tit 'Teórica[n^3 * 600]'
		reset
		
	# Comparacion con exacto del mejor parametro en funcion de los ejes
		set xlabel "Densidad del grafo"
		set ylabel "Diferencias con el exacto"
		set output "BusquedaLocal(comparacion_exacto_ejes).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_exacto_ejes).dat' tit 'Diferencias'
		
	# Comparacion con exacto del mejor parametro en funcion de los nodos
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "BusquedaLocal(comparacion_exacto_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_exacto_nodos).dat' tit 'Diferencias'

# GRASP:
	# Comparacion de parametros (porcentaje_goloso)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "GRASP(comparacion_parametros).dat.png"
		plot '..\..\dat\GRASP\GRASP(comparacion_parametros).dat' tit 'Diferencias'
		
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
