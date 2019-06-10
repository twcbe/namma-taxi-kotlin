package taxiCompany


fun calculateDiscount(chargesWithoutDiscount: Double, count: Int): Double =
    when {
        count == 1 -> discount(chargesWithoutDiscount) { it - it * 0.25 }
        count % 2 != 0 -> discount(chargesWithoutDiscount) { it - it * 0.10 }
        else -> chargesWithoutDiscount
    }


fun discount(chargesWithoutDiscount: Double, apply: (Double) -> Double): Double {
    return apply(chargesWithoutDiscount)
}

fun getCharges(mini: Vehicle, totalDistance: Double): Double {

    val fare = mini.fare;
    val base = fare[BASE]
    val next = fare[NEXT]
    val additional = fare[ADDITIONAL]
    var total = fare[TOTAL] ?: Fare(totalDistance)
    return when (totalDistance) {
        in 1.0..base!!.km -> base.amt
        in base.km..next!!.km -> base.amt + (next.amt * (totalDistance - base.km))
        in next.km..total.km -> base.amt + (next.amt * next.km) + (additional!!.amt * (totalDistance - base.km - next.km))
        else -> totalDistance * total.amt
    }
}
