import kotlin.random.Random
fun main() {

    //Introducing the program
    print("choose a number between 1 and 100 \ntype anything when done \n> ")
    readln()
    println("rules: \nI'm going to guess a number and if it is: \ntoo big type b \ntoo small type s \nif I guessed correct type c \n \n")


    //Starting Point
    val limit = mutableListOf(1, 100)
    var guess = 0
    var count: Int = 0
    val guessList: MutableSet<Int> = mutableSetOf() // creating a set of guesses


    while (true) { // main loop for guessing and stuff

        guess = Random.nextInt(limit[0], limit[1]) // guessing number within the known limits

        if (guess in guessList) continue // restarts the loop if $guess already exists

        guessList.add(guess) // adding new guess to set of previous guesses

        print("I'm guessing... $guess:\n> ")
        count++

        //responses module
        when (readln().lowercase()) {
            "b" -> limit[1] = guess // changing maximum index to previously guessed number
            "s" -> limit[0] = guess // changing minimum index to previously guessed number
            "c" -> {
                println("Wahoo!! your number is $guess!! \nI guessed your number in $count attempts \nThanks for playing!")
                break
            }
            else -> println("invalid response. please use 'b' or 's' or 'c'")
        }
    }
}