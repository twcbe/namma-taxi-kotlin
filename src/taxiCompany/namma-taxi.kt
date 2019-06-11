package nammaTaxi

data class Fare(val amt: Double = 0.0, val distanceConstraint: Int = Int.MAX_VALUE)

data class Fares(val base: Fare, val next: Fare, val additional: Fare, val total: Fare? = null)

fun getCharge(distance: Int, amt: Double) = distance * amt;

fun getChargeableDistance(distance: Int, constraint: Int) = if (distance >= constraint) constraint else distance

fun calculateFare(fare: Fare, distanceTravelled: Int, vararg chargedDistance: Int): Double {
    val remainingDistance = distanceTravelled - chargedDistance.sum()

    return if (remainingDistance > 0)
        getCharge(getChargeableDistance(remainingDistance, fare.distanceConstraint), fare.amt)
    else 0.0
}

fun calculateFares(fares: Fares, distanceTravelled: Int): Double =

    if (fares.total != null && distanceTravelled >= fares.total.distanceConstraint)
        getCharge(distanceTravelled, fares.total.amt)
    else {
        val base = fares.base.amt
        val next = calculateFare(
            fares.next,
            distanceTravelled,
            fares.base.distanceConstraint
        )
        val additional = calculateFare(
            fares.additional,
            distanceTravelled,
            fares.base.distanceConstraint, fares.next.distanceConstraint
        )
        (base + next + additional)
    }

fun nammaTaxi() {
    val mini = Fares(
        base = Fare(50.0, 3),
        next = Fare(10.0, 15),
        additional = Fare(8.0),
        total = Fare(8.0, 75)
    )

    val sedan = Fares(
        base = Fare(80.0, 5),
        next = Fare(12.0, 15),
        additional = Fare(10.0),
        total = Fare(10.0, 100)
    )

    val suv = Fares(
        base = Fare(100.0, 5),
        next = Fare(15.0, 15),
        additional = Fare(12.0)
    )

    fun getFares(distance: Int) = listOf(mini, sedan, suv).map { calculateFares(it, distance) }

    val fares = listOf(10, 20, 80, 120).map(::getFares)

    println(fares)


}

