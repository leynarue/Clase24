object EjerciciosPolimorfismo extends App {

  println("Ejercicio 61")
  
  class Caja[A](val contenido: A) {
    def obtener: A = contenido
    
    def combinar[B](otro: B): (A, B) = (contenido, otro)
  }

  val cajaInt = new Caja(10)
  val cajaStr = new Caja("Hola")
  val cajaBool = new Caja(true)

  println(s"Caja Int: ${cajaInt.obtener}")
  println(s"Caja String: ${cajaStr.obtener}")
  println(s"Caja Boolean: ${cajaBool.obtener}")
  println(s"Combinado: ${cajaStr.combinar(2026)}")
  
  println("\nEjercicio 62")
  
  def seleccionarPrimero[A](primero: A, segundo: A): A = primero

  println(s"Int: ${seleccionarPrimero(5, 10)}")
  println(s"String: ${seleccionarPrimero("Scala", "Java")}")
  println(s"Double: ${seleccionarPrimero(3.14, 2.71)}")

  println("\nEjercicio 63")
  
  def transformar[A, B](valor: A, funcion: A => B): B = funcion(valor)

  val t1 = transformar(10, (x: Int) => x.toString)                    
  val t2 = transformar("Scala", (s: String) => s.length)               
  val t3 = transformar(5.5, (d: Double) => d > 3.0)                    
  println(s"Int a String: $t1 (Tipo: ${t1.getClass.getSimpleName})")
  println(s"String a Longitud: $t2")
  println(s"Double a Boolean: $t3")

  println("\nEjercicio 64")
  
  abstract class Vehiculo(val marca: String)
  class Automovil(marca: String) extends Vehiculo(marca)
  class Motocicleta(marca: String) extends Vehiculo(marca)

  class RegistroVehiculo[A <: Vehiculo](val vehiculo: A) {
    def mostrarMarca: String = s"Vehículo marca: ${vehiculo.marca}"
  }

  val regAuto = new RegistroVehiculo(new Automovil("Toyota"))
  val regMoto = new RegistroVehiculo(new Motocicleta("Yamaha"))
  
  println(regAuto.mostrarMarca)
  println(regMoto.mostrarMarca)
 
  println("\nEjercicio 65")
  
  class Animal(val nombre: String)
  class Perro(nombre: String) extends Animal(nombre)

  class Contenedor[+A](val valor: A)

  val contenedorPerro: Contenedor[Perro] = new Contenedor(new Perro("Firulais"))
  
  val contenedorAnimal: Contenedor[Animal] = contenedorPerro
  println(s"Contenedor animal nombre: ${contenedorAnimal.valor.nombre}")

  println("\nEjercicio 66")
  
  trait Procesador[-A] {
    def procesar(valor: A): String
  }

  class ProcesadorAnimal extends Procesador[Animal] {
    def procesar(valor: Animal): String = s"Procesando al animal: ${valor.nombre}"
  }

  val procesadorGeneral: Procesador[Animal] = new ProcesadorAnimal()
  
  val procesadorPerros: Procesador[Perro] = procesadorGeneral

  println(procesadorPerros.procesar(new Perro("Bobby")))

  println("\nEjercicio 67")
  
  class Gato(nombre: String) extends Animal(nombre)

  trait Productor[+A] {
    def producir: A
  }

  trait Consumidor[-A] {
    def consumir(valor: A): String
  }

  val criadorPerros: Productor[Perro] = new Productor[Perro] {
    def producir: Perro = new Perro("Rocky")
  }
  val productorAnimales: Productor[Animal] = criadorPerros
  println(s"Productor produjo: ${productorAnimales.producir.nombre}")

  val consumidorAnimales: Consumidor[Animal] = new Consumidor[Animal] {
    def consumir(valor: Animal): String = s"Alimentando a ${valor.nombre}"
  }
  val consumidorPerros: Consumidor[Perro] = consumidorAnimales // Válido por contravarianza
  println(consumidorPerros.consumir(new Perro("Max")))

  println("\n-Ejercicio 68")
  
  class Resultado[A](val valor: A, val exitoso: Boolean, val mensaje: String)

  class Persona(val nombre: String)

  val resInt = new Resultado(100, true, "Operación exitosa")
  val resStr = new Resultado("TokenXYZ", true, "Autenticado")
  val resPersona = new Resultado(new Persona("Ana"), true, "Usuario encontrado")

  println(s"Resultado Int: ${resInt.valor} (${resInt.mensaje})")
  println(s"Resultado String: ${resStr.valor} (${resStr.mensaje})")
  println(s"Resultado Persona: ${resPersona.valor.nombre} (${resPersona.mensaje})")
}