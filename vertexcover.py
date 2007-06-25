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

string = 'Ingrese la ruta del archivo a probar: '
caso = raw_input(string)

string = 'Ingrese la ruta del archivo de salida: '
archout = raw_input(string)

string = 'Ingrese la ruta del archivo de datos: '
archdat = raw_input(string)
print archdat

nombreCaso = caso.partition(".")
nombreCaso = caso[0]

if(error == "false"):
    if algo == 1:
        print "Corriendo Algoritmo Exacto"
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar exacto.jar " + caso + " " +archout+ " " + archdat, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open(archdat,"r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open(archout,"r")
        resultado = out.readline()
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + resultado
    if algo == 2:
        print "Corriendo Goloso"
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar goloso.jar " + caso + " " +archout+ " " + archdat, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open(archdat,"r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open(archout,"r")
        resultado = out.readline()
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + resultado
    if algo == 3:
        listo = 0
        while listo == 0:
            meto = raw_input("Cuantos nodos quiere agregar?"  )
            saco = raw_input("Cuantos nodos quiere sacar?"  )
            if saco <= meto:
                listo = 0
                print "La cantidad que saca tiene que ser mayor a la que ingresa"
            else:
                listo = 1
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar busquedalocal.jar " + caso + " " +archout+ " " + archdat + " " + saco + " " + meto, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open(archdat,"r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron: " + instrucciones
        out = open(archout,"r")
        resultado = out.readline()
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue: " + resultado
    if algo == 4:
        listo = 0
        while listo == 0:
            it_max = raw_input("Cuantas iteraciones maximas quiere realizar? " )
            it_sc = raw_input("Cuantas iteraciones sin cambio quiere permitir? ")
            meto = raw_input("Cuantos nodos quiere agregar? " )
            saco = raw_input("Cuantos nodos quiere sacar? " )
            if saco <= meto:
                listo = 0
                print "La cantidad que saca tiene que ser mayor a la que ingresa"
            else:
                listo = 1
            porcentaje_goloso = raw_input("Cual quiere que sea la permisividad en el goloso?" )
        print "Corriendo GRASP"
        tiempoInicial = time.time()
        proc = subprocess.Popen("java -jar grasp.jar " + caso + " " +archout+ " " +archdat+ " " + it_max + " " + it_sc + " " + meto + " " + saco + " " + porcentaje_goloso, shell=True)
        proc.wait()
        tiempoFinal = time.time()
        print "El algoritmo tardo(en ms):" + str(tiempoFinal - tiempoInicial)
        dat = open(archdat,"r")
        instrucciones = dat.readline()
        instrucciones = instrucciones.partition(" ")
        instrucciones = instrucciones[2]
        print "La cantidad de instrucciones basicas fueron:" + instrucciones
        out = open(archout,"r")
        resultado = out.readline()
        print "La cantidad de nodos necesarios para realizar el recubrimiento fue:" + resultado








    