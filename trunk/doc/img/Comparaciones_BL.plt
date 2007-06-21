set style data points
#set term png



set xlabel "% agrego"
set ylabel "% saco"
set zlabel "Diferencias con exacto"
set xrange [0:100]
set yrange [0:100]
set zrange [50:500]
set output "Comparaciones_BL.png"
splot '..\..\dat\Comparaciones_BL.dat' tit 'Diferencias'



#set output
#set terminal win