import data.*
import repositories.EventRepository
import repositories.MedalTableRepository
import repositories.PurchaseRepository
import repositories.UserRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {

    val serviceUser = UserRepository
    var usuarioActual : User?
    var newPurchase : Purchase
    val purchaseRepo = PurchaseRepository
    var eventoActual : Event
    val eventoRepo = EventRepository
    val medalleroRepo = MedalTableRepository
    val ticketProActual = TicketPro()
    val eliteActual = Elite()
    val ultimateActual = UltimateEvent()


    do {
        println("Ingrese su nickname: ")
        val nickname = readln() ?: ""

        println("Ingrese su password: ")
        val password = readln() ?: ""

        usuarioActual = serviceUser.login(nickname,password)

        if (usuarioActual == null){
            println("Error: Contraseña o usuario equivocado")
        }
    }while (usuarioActual == null)


    var opcionSeleccionada : Int = 0
    do {
        try { //Con este try - catch buscamos sacarnos de encima que cuando el usuario ingrese un char o string se nos cierre el programa.

        println("---MENU JUEGOS OLIMPICOS---")
        println("0 - COMPRAR ENTRADAS")
        println("1 - HISTORIAL DE COMPRAS")
        println("2 - MEDALLERO OLIMPICO")
        println("3 - Salir")
        println("Ingrese una opcion: ")

        opcionSeleccionada = readln().toInt()

        when(opcionSeleccionada){
            0 -> {
                var indice : Int
                do {

                println("Intermediarios: ")
                println("0 - TicketPro")
                println("1 - Elite")
                println("2 - UltimateEvent")
                println("Ingrese una opcion: ")

                    indice = readln().toInt()

                }while (indice < 0 || indice > 2)

                    println("Eventos disponibles: ")
                    println(eventoRepo.get())

                    println("Ingrese el ID del evento: ")
                    val idEvento = readln().toLong()
                    eventoActual = eventoRepo.getById(idEvento)!!

                    val day = LocalDate.now()
                    val fecha: String = day.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                    println("Ingrese el asiento: ")
                    val asiento = readln()

                    var lastId = purchaseRepo.get().maxOfOrNull { it.id } ?: 0L

                val precio = when(indice){
                    0 -> {
                        ticketProActual.calcularComision(eventoActual.price)
                    }

                    1 -> {
                        eliteActual.calcularComision(eventoActual.price)
                    }

                    2-> {
                        ultimateActual.calcularComision(eventoActual.price)
                    }
                    else -> 0.0

                }

                    newPurchase = Purchase(lastId + 1, usuarioActual.id, eventoActual.id, precio, fecha, asiento)
                    purchaseRepo.add(newPurchase)



            }

            1 -> {

                val allpurchase = purchaseRepo.get()
                val purchaseOfUser = allpurchase.filter { it.userId == usuarioActual.id }
                println("Historial de compras: ")
                for (i in purchaseOfUser){
                    println(i)
                }

            }

            2 -> {
                println("Medallero olimpico: ")
                println(medalleroRepo.get())

            }
            else -> println("Error: Debes ingresar un numero válido")
        }
        }catch (e: NumberFormatException){
            println("Error: Debes ingresar un número válido")

        }

    }while (opcionSeleccionada != 3)

}