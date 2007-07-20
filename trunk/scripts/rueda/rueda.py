import sys
#Grafo Rueda: parametros 1)cant nodos 2) cant instancias

nodos = sys.argv[1]
nodos = int(nodos)

instancias = sys.argv[2]
instancias = int(instancias)

j=1

while j < nodos:
    i=1
    a = []
    arch=open("../../in/Rueda/Rueda"+str(j+1)+".in", 'w')
    while i < j-1:
        eje = "e" + " " + str(i) + " " + str(i+1) + '\n'
        a.append(eje)
        i = i+1
    i=1
    while i <= j-1:
        eje = "e" + " " + str(i) + " " + str(j) + '\n'
        a.append(eje)
        i = i+1
    c = "c " + "Grafo Rueda"
    s = "p edge " + str(j) + " " + str(len(a))
    arch.write(c)
    arch.write('\n')
    arch.write(s)
    arch.write('\n')
    arch.writelines(a)
    arch.close()
    j=j+1