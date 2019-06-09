package taxiComapny

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