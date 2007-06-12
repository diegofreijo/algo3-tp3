import random
import sys
import math


#Grafo Completo: parametros 1) cant de nodos 2) cant de instancias

nodos = sys.argv[1]
nodos = int(nodos)
nodos = nodos - 1

instancias = sys.argv[2]
instancias = int(instancias)

j=0

while j < instancias:
	i=1
	a = []
	nodos = nodos + 1
	arch=open("../../in/completo/completo"+str(j+1)+".in", 'w')
	while i <= nodos:
		k = i+1
		while k <= nodos:
			eje = "e" + " " + str(i) + " " + str(k) + '\n'
			a.append(eje)
			k = k+1
		i = i+1

	c = "c " + "Grafo Completo"
	s = "p edge " + str(nodos) + " " + str(len(a))
	arch.write(c)
	arch.write('\n')
	arch.write(s)
	arch.write('\n')
	arch.writelines(a)
	arch.close()
	j = j+1

arch.close()



        
