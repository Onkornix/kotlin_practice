import kotlin.random.Random
fun main() {
    print("choose a number between 1 and 100 \ntype anything when done \n> ")
    readln()
    println("rules: \nI'm going to guess a number and if it is: \ntoo big type b \ntoo small type s \nif I guessed correct type c \n \n")

    //variables
    val limit = mutableListOf(1, 100)
    var guess = 0
    var count: Int = 0
    var lastguess: Int
    // main loop for guessing and stuff
    while (true) {
        lastguess = guess // last guess before new guess
        guess = Random.nextInt(limit[0], limit[1]) // guessing number within the known limits
        if (guess == lastguess){ // won't guess the same number twice
            continue
        }
        print("I'm guessing... $guess:\n> ")
        count++
        when (readln().lowercase()) {
            "b" -> limit[1] = guess // changing maximum to previously guessed number
            "s" -> limit[0] = guess // changing minimum to previously guessed number
            "c" -> {
                println("Wahoo!! your number is $guess!! \nI guessed your number in $count attempts \nThanks for playing!")
                break
            }
            else -> println("invalid response. please use 'b' or 's' or 'c'")
        }
    }
}