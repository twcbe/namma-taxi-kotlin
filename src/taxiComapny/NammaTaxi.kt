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
        val miniChargesWithDiscount = calculateDiscount(miniCharges, count)

        val sedanCharges = getCharges(sedan, totalDistance)
        val sedanChargesWithDiscount = calculateDiscount(sedanCharges, count)

        val suvCharges = getCharges(suv, totalDistance)
        val suvChargesWithDiscount = calculateDiscount(suvCharges, count)

        println("Mini - $miniChargesWithDiscount , " +
                "Sedan - $sedanChargesWithDiscount ," +
                " SUV - $suvChargesWithDiscount")

    }while (!input.equals("exit", ignoreCase = true))
}

