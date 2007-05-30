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
	
def randomSinCero(a):
	ret = 0
	while ret == 0:
		ret = random.randrange(a+1)
	return ret

nodos = sys.argv[1]
nodos = int(nodos)
densidad = sys.argv[2]
densidad = int(densidad)

totalEjesPosibles = (nodos)*(nodos-1)/2
ejesGrafo = totalEjesPosibles*densidad/100
ejesGrafo = int(ejesGrafo)

densidadReal = str(ejesGrafo) + "/" + str(nodos*nodos)
print ejesGrafo
print nodos*nodos
print densidadReal
densidadTotal = str(totalEjesPosibles) + "/" + str(nodos*nodos)
print densidadTotal

arch = open("grafoAleatorio.in", 'w')

a = []
while len(a) < ejesGrafo:
	nodo1 = randomSinCero(nodos)
	nodo2 = randomSinCero(nodos)
	while nodo1 == nodo2:
		nodo2 = randomSinCero(nodos)
	b = nodo1, nodo2
	if pertenece(a,b) or noreverso(a,b):
		a = a
	else:
		a.append(b)


y = []
    
for x in a:
    eje = "e " + str(x[0]) + " " + str(x[1]) + '\n'
    y.append(eje)
    

#c = "c " + "Grafo Aleatorio Densidad = " + str(densidadReal) + " de un " + str(densidadTotal) + " máximo"
c = "c " + "Grafo Aleatorio Densidad = " + str(ejesGrafo) + "/" + str(totalEjesPosibles)
s = "p edge " + str(nodos) + " " + str(ejesGrafo)
arch.write(c)
arch.write('\n')
arch.write(s)
arch.write('\n')
arch.writelines(y)
arch.close()

