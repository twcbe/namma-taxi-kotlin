package taxiCompany

const val BASE = "base"
const val NEXT = "next"
const val ADDITIONAL = "additional"
const val TOTAL = "total"

data class Vehicle(val type: String) {
    val fare: HashMap<String, Fare> = HashMap()

    init {
        when (type) {
            "mini" -> {
                fare[BASE] = Fare(3.0, 50.0)
                fare[NEXT] = Fare(15.0, 10.0)
                fare[ADDITIONAL] = Fare(1.0, 8.0)
                fare[TOTAL] = Fare(75.0, 8.0)
            }
            "sedan" -> {
                fare[BASE] = Fare(5.0, 80.0)
                fare[NEXT] = Fare(15.0, 12.0)
                fare[ADDITIONAL] = Fare(1.0, 10.0)
                fare[TOTAL] = Fare(100.0, 10.0)
            }
            "suv" -> {
                fare[BASE] = Fare(5.0, 100.0)
                fare[NEXT] = Fare(15.0, 15.0)
                fare[ADDITIONAL] = Fare(1.0, 12.0)
            }
        }


    }
}

data class Fare(val km: Double, val amt: Double = 0.0)
