package taxiComapny

val BASE = "base"
val NEXT = "next"
val ADDITIONAL = "additional"
val TOTAL = "total"

fun main(args: Array<String>) {
    val mini = Vehicle("mini")
    val sedan = Vehicle("sedan")
    val suv = Vehicle("suv")
    val totalDistance = readLine()!!.toDouble()
    println("Mini - ${getCharges(mini, totalDistance)} , " +
            "Sedan - ${getCharges(sedan, totalDistance)} ," +
            " SUV - ${getCharges(suv, totalDistance)}")
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
        in next.km..total!!.km ->  charge = base.amt + (next.amt * next.km) + (additional!!.amt * (totalDistance-base.km-next.km))
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