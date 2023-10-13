import kotlin.random.*


//misc variables
val crawlingDialogue = listOf(
    "you move along and find a "
)
val newlines = "\n\n\n\n\n\n\n\n\n\n\n"
var isAlive = true


//player variables
val playerStats = mutableMapOf(
    "MaxHP" to 100,
    "CurrentHP" to 100,
    "BaseDamage" to 10,
    "Level" to 1
)
val playerInventory: MutableMap<String, Int> = mutableMapOf()



//Classes
class Enemy () {
    //try implementing weighted pools if you can figure those out.
    val existingEnemies = listOf(
        "Goblin"
    )
    val enemyStats = mapOf(
        "Goblin" to mutableMapOf(
            "HP" to 50,
            "DMG" to 5
        )
    )
    val engagement = existingEnemies[Random.nextInt(existingEnemies.size)]
}

//maybe use class for item stuff
class Item () {



    /*
    #  this is the problem child.
    #  weighted pools will be my downfall.
    #  come up with something please, thanks.

    maybe a function that takes the random Int input from the post battle
    and checks it against a bunch of IntRanges in a when statement.
     */
    val itemDropPool: Map<IntRange, String> = mapOf(
        1..10 to "HealPot",
        11..15 to "DMGboost",
        16..17 to "LevelUp"

    )


    val itemStats = mapOf(
        /*
        index 0 = effect type
        index 1 = magnitude

        effect types:
            0 : increase player current health
            1 : increase player damage for one turn
            2 : increase player level
         */
        //healing items
        "HealPot" to listOf(0, 10),

        //damage items
        "DMGboost" to listOf(1, 20),

        //level up items
        "LevelUp" to listOf(2, 1)
    )
}
//Hella functions

//this is literally just for printing a varying number of dots
fun dots(amountOfDots: Int) {
    Thread.sleep(500)
    print(".")
    Thread.sleep(500)
    print(".")
    for (number in 1..amountOfDots) {
        Thread.sleep(500)
        print(".")
    }
    println()

}

//In depth view of player stats
fun printPlayerCurrentStats() {
    println("Max Health: ${playerStats["MaxHP"]}, Current Health: ${playerStats["CurrentHP"]}, \n" +
            "Base Damage: ${playerStats["BaseDamage"]}, Level: ${playerStats["Level"]}")
}


//main combat loop after enemy encounter
fun fightEnemy(){

    //creating enemy
    val enemy = Enemy()
    val enemyInFight = enemy.engagement
    print(newlines)

    while (enemy.enemyStats[enemyInFight]!!["HP"]!! != 0) {

        //printing enemy stats and possible player actions
        println(
            "$enemyInFight: \n\n Health: ${enemy.enemyStats[enemyInFight]!!["HP"]}   " +
                    "Damage: ${enemy.enemyStats[enemyInFight]!!["DMG"]} \n\n" +
                    "Your Health: ${playerStats["CurrentHP"]} \n" +
                    "Your actions:   (a)ttack, (i)ventory, (s)tats, (d)ie"

        )
        //player action
        when (readln()) {
            "a" -> {
                println(
                    "You swing your sword and strike the $enemyInFight, " +
                            "dealing ${playerStats["BaseDamage"]!! * playerStats["Level"]!!} damage",
                )
                enemy.enemyStats[enemyInFight]!!["HP"] = enemy.enemyStats[enemyInFight]!!["HP"]!! - (playerStats["BaseDamage"]!! * playerStats["Level"]!!)
            }
            "i" -> {
                println("implement this please")
            }
            "s" -> {
                printPlayerCurrentStats()

            }
            "d" -> {
                isAlive = false
                break
            }
            else -> {
                println("mistakes are important, but never do that again")
                isAlive = false
                break
            }

        }
        Thread.sleep(1500)
        print(newlines)
        Thread.sleep(1000)

        //enemy action
        println("The vile $enemyInFight hits you and deals ${enemy.enemyStats[enemyInFight]!!["DMG"]} damage \n\n")
        playerStats["CurrentHP"] = (playerStats["CurrentHP"]!! - enemy.enemyStats[enemyInFight]!!["DMG"]!!)
        Thread.sleep(1500)
    }


    println("congratulations! you defeated the $enemyInFight!! " +
            "And it dropped 1")
}


fun play () {

    //creating player character
    while (isAlive){

        //start with a dialogue for moving through dungeon
        println(crawlingDialogue[0])
        dots(Random.nextInt(5))

        //fight function (just so i can keep things organized and modular(?))
        fightEnemy()

        //end for testing
        isAlive = false
    }
}
fun main(){
    //First dialogue to establish game
    println("Welcome to the Dungeon! ")
    Thread.sleep(3000)

    play()
}