package data

data class Event(
    val id: Long,
    val date: String,
    val hour: String,
    val place: String,
    val price: Double,
    val sport: Sport


) {
    override fun toString(): String {
        return "\n\nEvento(Id:$id, Dia:'$date', Hora:'$hour', Lugar:'$place', Precio:$price, Deporte:$sport)"
    }
}
