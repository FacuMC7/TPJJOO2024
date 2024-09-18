package data

class TicketPro : Intermediario(){

    override fun calcularComision(monto : Double): Double = monto*1.02

}