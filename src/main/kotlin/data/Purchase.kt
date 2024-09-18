package data

data class Purchase(
    val id: Long,
    val userId: Long,
    val eventId: Long,
    val amount: Double,
    val createdDate: String,
    val seat: String,


) {
    override fun toString(): String {
        return "Compra(Id:$id, IdUsuario:$userId, IdEvento:$eventId, Precio:$amount, FechaCompra:'$createdDate', Asiento:'$seat')"
    }
}