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

if algo < 1:
    print "Debe ingresar un numero entre 1 y 4"
    error = true
elif algo > 4:
    print "Debe ingresar un numero entre 1 y 4"
    error = true

string = 'Ingrese el nombre del archivo a probar: '
caso = raw_input(string)

nombreCaso = caso.partition(".")
nombreCaso = caso[0]

if(error == "false"):
    if algo == 1:
        print "Corriendo Algoritmo Exacto"
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar exacto.jar " + caso, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open("dat/Exacto/Exacto(exacto).dat","r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open("out/"+nombreCaso+".out","r")
        resultado = out.readline()
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + resultado
    if algo == 2:
        print "Corriendo Goloso"
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar goloso.jar " + caso, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open("dat/Goloso/Goloso(goloso).dat","r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open("out/Tp3(goloso).out","r")
        resultado = out.readline()
        resultado = resultado.partition(" ")
        resultado = resultado[2]
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + instrucciones
    if algo == 3:
        listo = 0
        while listo == 0:
            meto = raw_input("Cuantos nodos quiere agregar?" )
            saco = raw_input("Cuantos nodos quiere sacar?" )
            if saco <= meto:
                listo = 0
                print "La cantidad que saca tiene que ser mayor a la que ingresa"
            else:
                listo = 1
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar busqueda_local.jar " + caso + " " + saco + " " + meto, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open("dat/Tp3(busqueda_local).dat","r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open("out/Tp3(busqueda_local).out","r")
        resultado = out.readline()
        resultado = resultado.partition(" ")
        resultado = resultado[2]
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + instrucciones
    if algo == 4:
        print "Corriendo GRASP"
        tiempo = time()
        #Aca va la parte del subprocess del algoritmo








    