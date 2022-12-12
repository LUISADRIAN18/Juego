package dgtic.unam.juego
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.concurrent.thread
import kotlin.random.Random
import kotlin.random.Random.Default.nextLong


//+++++++++++++++++21. Flow+++++++++++++++++
//Su función son para los casos en donde resuelve situaciones que se tenga código
//asincrono que retorne multiples valores.
fun main() {
    flow()
}
fun flow() {
    println("Flow")
    runBlocking {
        launch {
            crearSecuenciasFlow().collect {
                println("datos $it")
            }
        }
        launch {
            (1..10).forEach {
                delay(300)
                println("proceso dos")
            }
        }
    }
}
fun crearSecuenciasFlow(): Flow<Int> {
    return flow {
        (1..6).forEach {
            println("Emulando el procesamiento de datos")
            delay(2000)
            emit(it + Random.nextInt(20, 60))
        }
    }
}




//*************20. Secuencias*******************
//Es una colección enfocada en procesar y entregar valores por pasos, ejecuta el proceso
//por cada elemento, es lazy porque procesa cada elemento que se solicite. Para regresar
//el valor ocupamos el método yield
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    println("Secuencias")
    crearSecuencias().forEach {
        println("$it datos regresados")
    }
}
fun crearSecuencias():Sequence<Int>{
    return sequence {
        (1..6).forEach {
            println("Emulando el procesamiento de datos")
            Thread.sleep(2000)
            yield(it+Random.nextInt(20,60))
        }
    }
}*/

//+++++++++++++++19. WithContext+++++++++++
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        println("nombre corrutina ${Thread.currentThread().name}")
        withContext(newSingleThreadContext("personalizado con with context")) {
            println("WithContext")
            delay(2000)
            println("nombre corrutina: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        withContext(Dispatchers.Default) {
            println("WithContext")
            delay(1000)
            println("Uso del CPU: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        println("termina corrutina: ${Thread.currentThread().name}")
    }
}*/
//-----------------------------WithContext--------
//Se ejecuta la misma corrutina, sino solo le cambiamos el contexto
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        println("nombre corrutina ${Thread.currentThread().name}")
        withContext(newSingleThreadContext("personalizado con with context")) {
            println("WithContext")
            delay(2000)
            println("nombre corrutina: ${Thread.currentThread().name}")
            println("termina corrutina: ${Thread.currentThread().name}")
        }
        println("termina corrutina: ${Thread.currentThread().name}")
    }
}*/

//---------------Dispatchers Personalizados----------------------------
//Usar este despachador para interactuar con la IU y realizar trabajos rápidos
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(newSingleThreadContext("Personalizada")) {
            println("mi corrutina ${Thread.currentThread().name}")
        }
        newSingleThreadContext("segunda personalizada").use { contexto->
            launch(contexto) {
                println("corrutina ${Thread.currentThread().name}")
            }
        }
    }
}*/
//++++++++++++++++Dispatchers Main++++++++++++++++++++++
//Usar este despachador para interactuar con la IU y realizar trabajos rápidos

/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.Main) {
            println("main")
        }
    }
}*/

// +++++++++++++++++Dispatchers Default++++++++++++++++++++++Este despachador está optimizado para realizar trabajo que usa la CPU de manera
//intensiva fuera del subproceso principal.
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.Default) {
            println("default")
        }
    }
}*/
//+++++++++++++Dispatchers IO+++++++++++++++++Su contexto es
//optimizar las conexiones de bases de datos, escritura, lectura de archivos y manejo de
//red
/*fun main() {
    dispatchers()
}
fun dispatchers() {
    runBlocking {
        launch {
            println("Otro subproceso")
        }
        launch(Dispatchers.IO) {
            println("IO")
        }
    }
}*/


// ++++++++++DEFFERED++++++++++++++++++Deffered es un Job, este puede ser cancelable también porque está en segundo plano,
//pero como sabemos regresa un valor
/*fun main() {
    deffered()
}
fun deffered() {
    runBlocking {
        val deferred=async {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(6000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
            "regreso una cadena"
        }
        launch {
            while(true){
                delay(1000)
                println("Esta activo: ${deferred.isActive}")
                println("Es cancelado: ${deferred.isCancelled}")
                println("Es completado: ${deferred.isCompleted}")
                //código para cancelar
                if((1..5).shuffled().first()==3){
                    println("Cancelar el deferred")
                    deferred.cancel()
                }
                if((1..5).shuffled().first()==1){
                    println("Esperan el valor: ${deferred.await()}")
                }
            }
        }
    }
}*/

//----------------------JOB------------------------------------------------------

/*fun main() {
    job()
}
fun job() {
    runBlocking {
        val job=launch {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(6000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
        }
        launch {
            while(true){
                delay(1000)
                println("Esta activo: ${job.isActive}")
                println("Es cancelado: ${job.isCancelled}")
                println("Es completado: ${job.isCompleted}")
                //código para cancelar
                if((1..5).shuffled().first()==3){
                    println("Cancelar el job")
                    job.cancel()
                }
            }
        }
    }
}*/

// ****************Constructor Coroutine Async*************
//Se ejecuta dentro de otra coroutine o función suspendida (suspend), se ocupa cuando si
//importa que regrese un resultado.

/*fun main() {
    async()
}

fun async() {
    runBlocking {
        val result = async {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(3000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
            "regreso una cadena"
        }
        println("Esperando el resultado")
        println("Resultado:= ${result.await()}")
        println("inicia 2 ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código 2")
        println("termina 2 ${Thread.currentThread().name}")
    }
}*/

// CONSTRUCTOR COROUTINE LAUNCH-----------Se ejecuta dentro de otra coroutine o función suspendida (suspend), se ocupa cuando no
//importa que regrese un resultado.

/*fun main() {
    launch()
}
fun launch() {
    runBlocking {
        launch {
            println("inicia 1 ${Thread.currentThread().name}")
            delay(1000)
            println("Ejecución de código 1")
            println("termina 1 ${Thread.currentThread().name}")
        }
        launch {
            println("inicia 2 ${Thread.currentThread().name}")
            delay(3000)
            println("Ejecución de código 2")
            println("termina 2 ${Thread.currentThread().name}")
        }
        println("inicia 3 ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código 3")
        println("termina 3 ${Thread.currentThread().name}")
    }
}*/

// CONSTRUCTOR COROUTINE RUNBLOCKING
/*fun main() {
    bloque()
}
fun bloque() {
    runBlocking {
        println("inicia ${Thread.currentThread().name}")
        delay(2000)
        println("Ejecución de código")
        println("termina ${Thread.currentThread().name}")
    }
}*/


//-----------GLOBALSCOPE---------------Se ejecutará la coroutine siempre que la aplicación este viva
/*fun main() {
    coroutines()
}
fun coroutines() {
    GlobalScope.launch {
        while(true){
            println("Código de la coroutina ${Thread.currentThread().name} ejecutando")
        }
    }
    Thread.sleep(2000)
}*/


//------------------------CORROUTINES---------------
/*fun main() {
    coroutines()
}
fun coroutines() {
    runBlocking {
        (1..1000000).forEach {
            launch {
                delay(1000)
                print("0")
            }
        }
    }
}*/


//******************* Lambda + Hilos=Multitares***********
/*
fun main() {
    hilolambda(4,7){
        println(it)
    }
}
fun hilolambda(a:Int,b:Int,callback:(result:Int)->Unit){
    var result=0
    thread {
        Thread.sleep(Random.nextLong(1000,3000))
        result=a+b
        callback(result)
    }
    println("Ejecuta más lineas")
}*/


//------------HILOS-----------------
/*fun main() {
    println(hilo(4,5))
}
fun hilo(a:Int,b:Int):Int{
    var result=0
    thread {
        Thread.sleep(Random.nextLong(1000,3000))
        result=a+b
    }
    //Thread.sleep(4000)
    return result
}*/


//---------------FUNCIONES DE TIPO----------------------

/*
fun main() {
    //version uno
    funcionlambda(3,4,{regresa ->
        println(regresa)
    })
    //version dos
    funcionlambda(3,4){regresa ->
        println(regresa)
    }
}
fun funcionlambda(a:Int,b:Int,callback:(result:Int)->Unit) {
    callback(a+b)
}*/
