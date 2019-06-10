package taxiCompany


fun main(args: Array<String>) {
    val mini = Vehicle("mini")
    val sedan = Vehicle("sedan")
    val suv = Vehicle("suv")
    val usage = HashMap<String, Int>()

    var input: String?

    do {
        input = readLine()
        val split = input!!.split(",")
        val totalDistance = split.first().toDouble()
        val mobile = split.last()

        var isAnOldCustomer = usage[mobile]
        when (isAnOldCustomer) {
            null -> usage.put(mobile, 1)
            else -> usage.put(mobile, ++isAnOldCustomer)
        }


        val charges = listOf(mini, sedan, suv).map {
            val count = usage[mobile]!!.toInt();
            Pair(it.type, getChargesAndDiscount(it, totalDistance, count))
        }

        displayCharges(charges)

    } while (!input.equals("exit", ignoreCase = true))
}

fun getChargesAndDiscount(vehicle: Vehicle, totalDistance: Double, count: Int): Pair<Double, Double> {
    val charges = getCharges(vehicle, totalDistance)
    val chargesWithDiscount = calculateDiscount(charges, count)
    return Pair(charges, chargesWithDiscount);
}

fun displayCharges(charges: List<Pair<String, Pair<Double, Double>>>) {
    charges.map {
        println(
            "${it.first} - "
                    + "Price ${it.second.first}, "
                    + "Discounted Price ${it.second.second}, "
        )
    }

}
