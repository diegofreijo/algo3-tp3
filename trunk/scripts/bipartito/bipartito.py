import random
import sys
import math
def pertenece (a,b):
	ret = False
	if a.count(b):
		ret = True
	return ret

def noreverso(a,b):
	ret = False
	c = b[1], b[0]
	ret = pertenece(a,c)
	return ret
	
def randomSinCero(a,b):
	ret = 0
	while ret == 0:
		ret = random.randrange(a+1,b+1)
	return ret

nodos = 4
cantidad = 1
ejesGrafo = 3
a = []
while cantidad < 5:
	arch = open("../in/bipartito/bipartito"+str(cantidad)+".in", 'w')
	cantidad = cantidad + 1
	a = []
	while len(a) < ejesGrafo:
		nodo1 = randomSinCero(0,nodos/2)
		nodo2 = randomSinCero(nodos/2,nodos)
		b = nodo1, nodo2
		if pertenece(a,b):
			a = a
		else:
			a.append(b)

	y = []
		
	for x in a:
		eje = "e " + str(x[0]) + " " + str(x[1]) + '\n'
		y.append(eje)

	c = "c " + "Grafo Bipartito = " + str(ejesGrafo) 
	s = "p edge " + str(nodos) + " " + str(ejesGrafo)
	arch.write(c)
	arch.write('\n')
	arch.write(s)
	arch.write('\n')
	arch.writelines(y)
	arch.close()
