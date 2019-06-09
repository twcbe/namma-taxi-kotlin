package taxiComapny

val BASE = "base"
val NEXT = "next"
val ADDITIONAL = "additional"
val TOTAL = "total"

fun main(args: Array<String>) {
    val mini = Vehicle("mini")
    val sedan = Vehicle("sedan")
    val suv = Vehicle("suv")
    val usage = HashMap<String,Int>()

    var input: String?

    do{
        input = readLine()
        val split = input!!.split(",")
        val totalDistance = split.first().toDouble()
        val mobile = split.last()

        var isAnOldCustomer = usage.get(mobile)
        if(isAnOldCustomer == null ){
            usage.put(mobile, 1)
        }else{
            usage.put(mobile, ++isAnOldCustomer)
        }

        val miniCharges = getCharges(mini, totalDistance)
        val count = usage.get(mobile)!!.toInt()
        val miniChargesWithDiscount = applyDiscount(miniCharges, count)

        val sedanCharges = getCharges(sedan, totalDistance)
        val sedanChargesWithDiscount = applyDiscount(sedanCharges, count)

        val suvCharges = getCharges(suv, totalDistance)
        val suvChargesWithDiscount = applyDiscount(suvCharges, count)

        println("Mini - $miniChargesWithDiscount , " +
                "Sedan - $sedanChargesWithDiscount ," +
                " SUV - $suvChargesWithDiscount")

    }while (!input.equals("exit", ignoreCase = true))
}

fun applyDiscount(chargesWithoutDiscount: Double, count: Int): Double {
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

class Vehicle (type:String) {
    val fare:HashMap<String, Fare> = HashMap()
    init{
        when(type){
            "mini" -> {
                fare.put(BASE, Fare(3.0, 50.0))
                fare.put(NEXT, Fare(15.0, 10.0))
                fare.put(ADDITIONAL, Fare(1.0, 8.0))
                fare.put(TOTAL, Fare(75.0, 8.0))
            }
            "sedan" -> {
                fare.put(BASE, Fare(5.0, 80.0))
                fare.put(NEXT, Fare(15.0, 12.0))
                fare.put(ADDITIONAL, Fare(1.0, 10.0))
                fare.put(TOTAL, Fare(100.0, 10.0))
            }
            "suv" -> {
                fare.put(BASE, Fare(5.0, 100.0))
                fare.put(NEXT, Fare(15.0, 15.0))
                fare.put(ADDITIONAL, Fare(1.0, 12.0))
            }
        }


    }
}

class Fare(val km:Double, val amt:Double = 0.0)