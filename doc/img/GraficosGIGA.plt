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
