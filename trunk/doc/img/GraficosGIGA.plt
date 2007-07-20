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
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_ejes).dat' tit 'Pr�ctica (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_instrucciones_ejes).dat' tit 'Pr�ctica (Goloso)', 102400000*x/(40*39/2)  tit 'Te�rica [40^5 * m / (40*39/2)]'#, 240*102400000*x/(40*39/2)  tit 'Te�rica ajustada [240 * Te�rica]'
		
	# Cantidad de instrucciones en funcion de los nodos del mejor parametro
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set yrange [0:500000]
		set output "BusquedaLocal(instrucciones_nodos).png"
		plot '..\..\dat\BusquedaLocal\BusquedaLocal(instrucciones_nodos).dat' tit 'Pr�ctica (Naive)', '..\..\dat\BusquedaLocal\BusquedaLocal(goloso_instrucciones_nodos).dat' tit 'Pr�ctica (Goloso)', x*x*x*x*x*600 tit 'Te�rica[n^5 * 600]'
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
		plot '..\..\dat\GRASP\GRASP(instrucciones_ejes).dat' tit 'Cantidad de instrucciones', 64000*40*40*x/(40*39/2)  tit 'Te�rica [40^5 * m / (40*39/2)]', 240*64000*x/(40*39/2)  tit 'Te�rica ajustada [240 * Te�rica]'
		
	# Cantidad de instrucciones del mejor parametro por nodos
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de instrucciones"
		set output "GRASP(instrucciones_nodos).png"
		plot '..\..\dat\GRASP\GRASP(instrucciones_nodos).dat' tit 'Cantidad de instrucciones', x*x*x*x*x*600 tit 'Te�rica[n^5 * 600]'
		
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
		set ylabel "Tama�o de la solucion"
		set output "Comparacion(tama�oAleatorio).png"
		plot '..\..\dat\Aleatorio\Aleatorio(tama�oBLAleatorio).dat' tit 'BL','..\..\dat\Aleatorio\Aleatorio(tama�oGolosoAleatorio).dat' tit 'Goloso', '..\..\dat\Aleatorio\Aleatorio(tama�oGRASPAleatorio).dat' tit 'Grasp'
		
		# Bipartito ((en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesBipartito).png"
		plot '..\..\dat\Bipartito\Bipartito(instruccionesBLBipartito).dat' tit 'BL','..\..\dat\Bipartito\Bipartito(instruccionesGolosoBipartito).dat' tit 'Goloso', '..\..\dat\Bipartito\Bipartito(instruccionesGRASPBipartito).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tama�o de la solucion"
		set output "Comparacion(tama�oBipartito).png"
		plot '..\..\dat\Bipartito\Bipartito(tama�oBLBipartito).dat' tit 'BL','..\..\dat\Bipartito\Bipartito(tama�oGolosoBipartito).dat' tit 'Goloso', '..\..\dat\Bipartito\Bipartito(tama�oGRASPBipartito).dat' tit 'Grasp'
		
		# Rueda (en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesRueda).png"
		plot '..\..\dat\Rueda\Rueda(instruccionesBLRueda).dat' tit 'BL','..\..\dat\Rueda\Rueda(instruccionesGolosoRueda).dat' tit 'Goloso', '..\..\dat\Rueda\Rueda(instruccionesGRASPRueda).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tama�o de la solucion"
		set output "Comparacion(tama�oRueda).png"
		plot '..\..\dat\Rueda\Rueda(tama�oBLRueda).dat' tit 'BL','..\..\dat\Rueda\Rueda(tama�oGolosoRueda).dat' tit 'Goloso', '..\..\dat\Rueda\Rueda(tama�oGRASPRueda).dat' tit 'Grasp'
		
		
		# Completo (en funcion de la cantidad de nodos)
		set xlabel "Cantidad de nodos"
		set ylabel "Cantidad de Instrucciones"
		set output "Comparacion(InstruccionesCompleto).png"
		plot '..\..\dat\Completo\Completo(instruccionesBLCompleto).dat' tit 'BL','..\..\dat\Completo\Completo(instruccionesGolosoCompleto).dat' tit 'Goloso', '..\..\dat\Completo\Completo(instruccionesGRASPCompleto).dat' tit 'Grasp'
		
		set xlabel "Cantidad de nodos"
		set ylabel "Tama�o de la solucion"
		set output "Comparacion(tama�oCompleto).png"
		plot '..\..\dat\Completo\Completo(tama�oBLCompleto).dat' tit 'BL','..\..\dat\Completo\Completo(tama�oGolosoCompleto).dat' tit 'Goloso', '..\..\dat\Completo\Completo(tama�oGRASPCompleto).dat' tit 'Grasp'
		
set output
set terminal win
