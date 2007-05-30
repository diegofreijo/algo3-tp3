import sys
#Grafo Rueda

nodos = familia = sys.argv[1]
nodos = int(nodos)

arch=open("grafoRueda.in", 'w')

i=1
a = []
while i < nodos-1:
    eje = "e" + " " + str(i) + " " + str(i+1) + '\n'
    a.append(eje)
    i = i+1

i=1
while i <= nodos-1:
    eje = "e" + " " + str(i) + " " + str(nodos) + '\n'
    a.append(eje)
    i = i+1
    
c = "c " + "Grafo Rueda"
s = "p edge " + str(nodos) + " " + str(len(a))
arch.write(c)
arch.write('\n')
arch.write(s)
arch.write('\n')
arch.writelines(a)
arch.close()