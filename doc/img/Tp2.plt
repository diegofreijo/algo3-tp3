set style data points
set term png



set xlabel "Cantidad de im�genes (n)"
set ylabel "Operaciones b�sicas"
set output "Tp2(armado).png"
plot '..\..\dat\Tp2(armado).dat' tit 'Pr�ctica', x * log(x)/log(2) tit 'Te�rica [n*log(n)]', 10 * x * log(x)/log(2) tit 'Te�rica ajustada [10*n*log(n)]'


set xlabel "Cantidad de im�genes (n)"
set ylabel "Operaciones b�sicas"
set output "Tp2(consulta).png"
plot '..\..\dat\Tp2(consulta).dat' tit 'Pr�ctica', x + log(x)/log(2) tit 'Te�rica [log(n)+n]', 3*log(x)/log(2) + 3*x tit 'Te�rica ajustada [3(log(n)+n)]'


set xlabel "Cantidad de im�genes (n)"
set ylabel "Operaciones b�sicas"
set output "Tp2(fusion).png"
plot '..\..\dat\Tp2(fusion).dat' tit 'Pr�ctica', x tit 'Te�rica [k]', 6*x tit 'Te�rica ajustada [6k]'


set xlabel "Cantidad de im�genes (n)"
set ylabel "Operaciones b�sicas"
set output "Tp2(fb).png"
plot '..\..\dat\Tp2(consulta_mas_fusion).dat' tit 'Con fusi�n', '..\..\dat\Tp2(consulta).dat' tit 'Sin fusi�n', '..\..\dat\Tp2(fb).dat' tit 'Fuerza Bruta'



set output
set terminal win