import subprocess
import time
import sys

print "Algoritmos"
print "------------"
print "1. Exacto"
print "2. Goloso"
print "3. Busqueda Local"
print "4. GRASP"

error = "false"
string = 'Elija el algoritmo a correr: '
a = raw_input(string)
algo = int(a)

string = 'Ingrese el nombre del archivo a probar'
caso = raw_input(string)

if algo < 1:
    print "Debe ingresar un numero entre 1 y 4"
    error = true
elif algo > 4:
    print "Debe ingresar un numero entre 1 y 4"
    error = true

if(error == "false"):
    if algo == 1:
        print "Corriendo Algoritmo Exacto"
        tiempoInicial = time.time()
        #Aca va la parte del subprocess del algoritmo
        time.sleep(1)
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open("../../dat/Tp3(busqueda_local).dat","w")
        time.sleep(2)
    if algo == 2:
        print "Corriendo Goloso"
        tiempo = time()
        #Aca va la parte del subprocess del algoritmo
    if algo == 3:
        print "Corriendo Busqueda Local"
        tiempo = time()
        #Aca va la parte del subprocess del algoritmo
    if algo == 4:
        print "Corriendo GRASP"
        tiempo = time()
        #Aca va la parte del subprocess del algoritmo








    