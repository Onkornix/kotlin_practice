import kotlin.random.*


//misc variables
val crawlingDialogue = listOf(
    "you move along and find a "
)
val deathDialogue = listOf(
    "you died lol"
)
const val newlines = "\n\n\n\n\n\n\n\n\n\n\n"
var isAlive = true


//player variables
val playerStats = mutableMapOf(
    "MaxHP" to 100,
    "CurrentHP" to 100,
    "BaseDamage" to 50,
    "Level" to 1
)
val playerInventory: MutableList<String> = mutableListOf()





//Classes
class Enemy () {
    //try implementing weighted pools if you can figure those out.
    private val existingEnemies = listOf(
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


class Item () {

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

    private val amountOfItemsInInventory: MutableMap<String, Int> = mutableMapOf()
    private fun addToInventory(itemToAdd: String){
        playerInventory.add(playerInventory.size, itemToAdd)
    }
    fun getWeightedItemPool(randomIn:Int){
        when (randomIn){
            in 1..10 -> {
                println("HealPot")
                addToInventory("HealPot")
            }
            in 11..15 -> {
                println("DMGboost")
                addToInventory("DMGboost")
            }
            in 16..17 -> {
                println("LevelUp")
                addToInventory("LevelUp")
            }
        }
    }

    fun getItemsInInventory(listOfItems: List<String>){
        /*
        create a map where the key is the item and the value is the amount
        ex:
            "HealPot" = 3,
            "DMGboost" = 1
         */
        for (item in listOfItems){
            if (item !in amountOfItemsInInventory.keys){
                amountOfItemsInInventory.run { put(item, 1)}
            }
            else {
                val increase = amountOfItemsInInventory[item]!! + 1
                amountOfItemsInInventory.run { replace(item, increase) }
            }
        }
        for (itemKey in amountOfItemsInInventory.keys){
            println("${amountOfItemsInInventory[itemKey]} x $itemKey")
        }
    }
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
    var actionCondition: String
    print(newlines)

    while (enemy.enemyStats[enemyInFight]!!["HP"]!! > 0) {

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
                actionCondition = "a"
            }
            "i" -> {
                val item = Item()
                item.getItemsInInventory(playerInventory)
                actionCondition = "i"
            }
            "s" -> {
                printPlayerCurrentStats()
                actionCondition = "s"
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

        //if player chose the attack action, and the enemy is not dead, player receives damage
        if (actionCondition == "a" && enemy.enemyStats[enemyInFight]!!["HP"]!! > 0 ){

            //waiting and creating newlines for enemy attack text
            Thread.sleep(1500)
            print(newlines)
            Thread.sleep(1000)

            //enemy attack
            println("The vile $enemyInFight hits you and deals ${enemy.enemyStats[enemyInFight]!!["DMG"]} damage \n\n") //display attack text
            playerStats["CurrentHP"] = (playerStats["CurrentHP"]!! - enemy.enemyStats[enemyInFight]!!["DMG"]!!) //change player health
            Thread.sleep(1500)
        } else {
            continue
        }


    }

    val newItem = Item()
    println("congratulations! you defeated the $enemyInFight!! " +
            "And it dropped:")
    for (i in 1..3){
        newItem.getWeightedItemPool(Random.nextInt(17))

    }
}


fun play () {

    //creating player character
    while (isAlive){

        //start with a dialogue for moving through dungeon
        println(crawlingDialogue[0])
        dots(Random.nextInt(5))

        //fight function (just so I can keep things organized and modular(?))
        fightEnemy()

        //end for testing
        //isAlive = false
    }
}
fun main(){
    //First dialogue to establish game
    println("Welcome to the Dungeon! ")
    Thread.sleep(3000)

    play()
}