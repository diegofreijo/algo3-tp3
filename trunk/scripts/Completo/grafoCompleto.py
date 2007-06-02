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
		ret = random.randrange(a)
	return ret

#Grafo Completo

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
	arch=open("../../in/Completo/Completo"+str(j+1)+".in", 'w')
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



        
