import subprocess
import time

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

if algo < 1:
    print "Debe ingresar un numero entre 1 y 4"
    error = true
elif algo > 4:
    print "Debe ingresar un numero entre 1 y 4"
    error = true

print time.time()

if(error == "false"):
    if algo == 1:
        print "Corriendo Algoritmo Exacto"
        time.sleep(1)
        tiempo = time.time()
        print tiempo
        #Aca va la parte del subprocess del algoritmo
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








    