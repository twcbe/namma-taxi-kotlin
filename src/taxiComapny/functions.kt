package taxiComapny


fun calculateDiscount(chargesWithoutDiscount: Double, count: Int): Double {
    var withDiscount: Double
    when {
        count == 1 -> withDiscount = discount(chargesWithoutDiscount){ it - it*0.25}
        count % 2 != 0 -> withDiscount = discount(chargesWithoutDiscount){ it - it*0.10}
        else -> withDiscount = chargesWithoutDiscount
    }
    return withDiscount
}

fun discount(chargesWithoutDiscount: Double, apply: (Double) -> Double):Double {
    return apply(chargesWithoutDiscount)
}

fun getCharges(mini: Vehicle, totalDistance: Double):Double {
    var charge: Double
    val fare = mini.fare

    val base = fare.get(BASE)
    val next = fare.get(NEXT)
    val additional = fare.get(ADDITIONAL)
    var total = fare.get(TOTAL)?:Fare(totalDistance)
    when(totalDistance){
        in 1.0..base!!.km -> charge = base.amt
        in base.km..next!!.km ->  charge = base.amt + (next.amt * (totalDistance - base.km))
        in next.km..total.km ->  charge = base.amt + (next.amt * next.km) + (additional!!.amt * (totalDistance-base.km-next.km))
        else  ->  charge = totalDistance * total.amt
    }
    return charge
}
