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
		#set yrange [0:450000]
		set output "BusquedaLocal(instrucciones_ejes).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_ejes).dat' tit 'Práctica (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_instrucciones_ejes).dat' tit 'Práctica (Goloso)', 102400000*x/(40*39/2)  tit 'Teórica [40^5 * m / (40*39/2)]'#, 240*102400000*x/(40*39/2)  tit 'Teórica ajustada [240 * Teórica]'
		
	# Cantidad de instrucciones en funcion de los nodos del mejor parametro
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set yrange [0:500000]
		set output "BusquedaLocal(instrucciones_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_nodos).dat' tit 'Práctica (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_instrucciones_nodos).dat' tit 'Práctica (Goloso)', x*x*x*x*x*600 tit 'Teórica[n^5 * 600]'
		reset
		
	# Comparacion con exacto del mejor parametro en funcion de los ejes
		set xlabel "Densidad del grafo"
		set ylabel "Diferencias con el exacto"
		set output "BusquedaLocal(comparacion_exacto_ejes).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_exacto_ejes).dat' tit 'Diferencias (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_comparacion_exacto_ejes).dat' tit 'Diferencias (Goloso)'
		
	# Comparacion con exacto del mejor parametro en funcion de los nodos
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "BusquedaLocal(comparacion_exacto_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(comparacion_exacto_nodos).dat' tit 'Diferencias (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_comparacion_exacto_nodos).dat' tit 'Diferencias (Goloso)'

# GRASP:
	
	# Cantidad de instrucciones del mejor parametro por densidad
		set xlabel "Densidad"
		set ylabel "Cantidad de instrucciones"
		set output "GRASP(instrucciones_ejes).png"
		plot '..\..\dat\GRASP\GRASP(instrucciones_ejes).dat' tit 'Cantidad de instrucciones', 64000*40*40*x/(40*39/2)  tit 'Teórica [40^5 * m / (40*39/2)]', 240*64000*x/(40*39/2)  tit 'Teórica ajustada [240 * Teórica]'
		
	# Cantidad de instrucciones del mejor parametro por nodos
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "GRASP(instrucciones_nodos).png"
		plot '..\..\dat\GRASP\GRASP(instrucciones_nodos).dat' tit 'Cantidad de instrucciones', x*x*x*x*x*600 tit 'Teórica[n^5 * 600]'
		
	# Comparacion con exacto nodos
		set xlabel "Cantidad de nodos"
		set ylabel "Diferencias"
		set output "GRASP(comparacion_exacto_nodos).png"
		plot '..\..\dat\GRASP\GRASP(comparacion_exacto_nodos).dat' tit 'Diferencias'
		
	# Comparacion con exacto ejes
		set xlabel "Densidad del grafo
		set ylabel "Diferencias"
		set output "GRASP(comparacion_exacto_ejes).png"
		plot '..\..\dat\GRASP\GRASP(comparacion_exacto_ejes).dat' tit 'Diferencias'
	
# Finales:
	# Comparacion de las 3 heuristicas:
		# Aleatorio (en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesAleatorio).png"
		plot '..\..\dat\Aleatorio\Aleatorio(instruccionesBLAleatorio).dat' tit 'BL','..\..\dat\Aleatorio\Aleatorio(instruccionesGolosoAleatorio).dat' tit 'Goloso', '..\..\dat\Aleatorio\Aleatorio(instruccionesGRASPAleatorio).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tamaño de la solucion"
		set output "Comparacion(tamañoAleatorio).png"
		plot '..\..\dat\Aleatorio\Aleatorio(tamañoBLAleatorio).dat' tit 'BL','..\..\dat\Aleatorio\Aleatorio(tamañoGolosoAleatorio).dat' tit 'Goloso', '..\..\dat\Aleatorio\Aleatorio(tamañoGRASPAleatorio).dat' tit 'Grasp'
		
		# Bipartito ((en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesBipartito).png"
		plot '..\..\dat\Bipartito\Bipartito(instruccionesBLBipartito).dat' tit 'BL','..\..\dat\Bipartito\Bipartito(instruccionesGolosoBipartito).dat' tit 'Goloso', '..\..\dat\Bipartito\Bipartito(instruccionesGRASPBipartito).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tamaño de la solucion"
		set output "Comparacion(tamañoBipartito).png"
		plot '..\..\dat\Bipartito\Bipartito(tamañoBLBipartito).dat' tit 'BL','..\..\dat\Bipartito\Bipartito(tamañoGolosoBipartito).dat' tit 'Goloso', '..\..\dat\Bipartito\Bipartito(tamañoGRASPBipartito).dat' tit 'Grasp'
		
		# Rueda (en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesRueda).png"
		plot '..\..\dat\Rueda\Rueda(instruccionesBLRueda).dat' tit 'BL','..\..\dat\Rueda\Rueda(instruccionesGolosoRueda).dat' tit 'Goloso', '..\..\dat\Rueda\Rueda(instruccionesGRASPRueda).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tamaño de la solucion"
		set output "Comparacion(tamañoRueda).png"
		plot '..\..\dat\Rueda\Rueda(tamañoBLRueda).dat' tit 'BL','..\..\dat\Rueda\Rueda(tamañoGolosoRueda).dat' tit 'Goloso', '..\..\dat\Rueda\Rueda(tamañoGRASPRueda).dat' tit 'Grasp'
		
		
		# Completo (en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesCompleto).png"
		plot '..\..\dat\Completo\Completo(instruccionesBLCompleto).dat' tit 'BL','..\..\dat\Completo\Completo(instruccionesGolosoCompleto).dat' tit 'Goloso', '..\..\dat\Completo\Completo(instruccionesGRASPCompleto).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tamaño de la solucion"
		set output "Comparacion(tamañoCompleto).png"
		plot '..\..\dat\Completo\Completo(tamañoBLCompleto).dat' tit 'BL','..\..\dat\Completo\Completo(tamañoGolosoCompleto).dat' tit 'Goloso', '..\..\dat\Completo\Completo(tamañoGRASPCompleto).dat' tit 'Grasp'
		
set output
set terminal win
