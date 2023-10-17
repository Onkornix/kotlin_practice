import kotlin.random.*


//misc variables
val crawlingDialogue = listOf(
    "you move along and find a ",
    "proceeding forward, you encounter a",
    "as you travel onward you see a"
)
val deathDialogue = listOf(
    "you died lol",
    "Rip L",
    "Glad you died"

)
const val newlines = "\n\n\n\n\n\n\n\n\n\n\n"
var isAlive = true


//player variables
val playerStats = mutableMapOf(
    "MaxHP" to 100,
    "CurrentHP" to 100,
    "BaseDamage" to 50,
    "Level" to 1,
    "BonusDamage" to 0
)
val playerInventory: MutableList<String> = mutableListOf()

//Classes
class Enemy () {
    //try implementing weighted pools if you can figure those out.
    //also add different drop pools for different enemies
    private val existingEnemies = listOf(
        "Goblin",
        "Trenton"
    )
    val enemyStats = mapOf(
        "Goblin" to mutableMapOf(
            "HP" to 50,
            "DMG" to 5
        ),
        "Trenton" to mutableMapOf(
            "HP" to 50,
            "DMG" to 2
        )
    )
    val engagement = existingEnemies[Random.nextInt(existingEnemies.size)]
}


class Item () {

    private val itemStats: Map<String, List<Int>> = mapOf(
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

    private val orderedInvMap: MutableMap<String, Int> = mutableMapOf()
    private val itemListForIndexingOrderedInvMap: MutableList<String> = mutableListOf()

    fun getItemsInInventory() {
        /*
        create a map where the key is the item and the value is the amount
        ex:
            "HealPot" to 3,
            "DMGboost" to 1

         and create a list of the entries, so they can be indexed and more easily referenced
         */


        for (item in playerInventory) {
            if (item !in orderedInvMap.keys) {
                orderedInvMap.run { put(item, 1) }
            } else {
                val increase = orderedInvMap[item]!! + 1
                orderedInvMap.run { replace(item, increase) }
            }
        }

        for (itemKey in orderedInvMap.keys) {
            itemListForIndexingOrderedInvMap.add(itemKey)
            println(
                "[${itemListForIndexingOrderedInvMap.indexOf(itemKey)}]  " +
                        "$itemKey x ${orderedInvMap[itemKey]}"
            )
        }
    }

    fun useItem(itemIndexInput: Int, amount: Int){
        val useItemName = itemListForIndexingOrderedInvMap[itemIndexInput]
        var useTotal = 0
        var useAmount = amount //so it's mutable

        if (useAmount > orderedInvMap[useItemName]!!) {
            println("cannot use that many. Using ${orderedInvMap[useItemName]} instead")
            useAmount = orderedInvMap[useItemName]!!
        }

        while (useTotal < useAmount){
            when (itemStats[useItemName]!![0]){
                0 -> {
                    playerStats["CurrentHP"] = playerStats["CurrentHP"]!! + itemStats[useItemName]!![1]
                    if (playerStats["CurrentHP"]!! > playerStats["MaxHP"]!!) {
                        playerStats["CurrentHP"] = playerStats["MaxHP"]!!
                    }
                    //println(playerStats["CurrentHP"])
                }
                1 -> {
                    playerStats["BonusDamage"] = playerStats["BonusDamage"]!! + itemStats[useItemName]!![1]
                }
                2 -> {
                    //if the player has enough tokens they can level up.
                }
            }
            useTotal++
        }

        println(playerInventory)
        //removes used item from inventory
        playerInventory.run { removeAt(indexOf(useItemName)) }
        println(playerInventory)

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
    if (playerStats["BonusDamage"] != 0){
        println("\nBonus Damage: ${playerStats["BonusDamage"]}")
    }
}

fun pressEnterToContinue(){
    println("Press ENTER to continue: ")
    readln()
}

//main combat loop after enemy encounter
fun fightEnemy(){

    //creating enemy
    val enemy = Enemy()
    val enemyInFight = enemy.engagement
    var actionCondition = false
    print(newlines)

    while (enemy.enemyStats[enemyInFight]!!["HP"]!! > 0) {

        //printing enemy stats and possible player actions
        println(
            "$enemyInFight: \n\n Health: ${enemy.enemyStats[enemyInFight]!!["HP"]}   " +
                    "Damage: ${enemy.enemyStats[enemyInFight]!!["DMG"]} \n\n" +
                    "Your Health: ${playerStats["CurrentHP"]} \n" +
                    "Your actions:   (a)ttack, (i)ventory, (s)tats"

        )
        //player action
        when (readln()) {
            "a" -> {
                println(
                    "You swing your sword and strike the $enemyInFight, " +
                            "dealing ${playerStats["BaseDamage"]!! * playerStats["Level"]!!} damage",
                )
                enemy.enemyStats[enemyInFight]!!["HP"] = enemy.enemyStats[enemyInFight]!!["HP"]!! - (playerStats["BaseDamage"]!! + playerStats["BonusDamage"]!!)
                actionCondition = true
            }
            "i" -> {
                val item = Item()
                item.getItemsInInventory()
                println("\naction: (u)se item, (r)eturn to battle")
                val action: String = readln()
                when (action){
                    "u" -> {
                        println("enter item number: ")
                        val itemIndexInput: Int = readln().toInt()
                        println("how many to use: ")
                        val amount: Int = readln().toInt()
                        item.useItem(itemIndexInput,amount)
                    }
                    "r" -> {
                        continue
                    }
                }

            }
            "s" -> {
                printPlayerCurrentStats()
                pressEnterToContinue()
            }
            /*
            "d" -> {
                isAlive = false
                break
            }
            */
            else -> {
                println("mistakes are important, but never do that again")
                isAlive = false
                break
            }

        }

        //if player chose the attack action, and the enemy is not dead, player receives damage
        if (actionCondition && enemy.enemyStats[enemyInFight]!!["HP"]!! > 0){

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
    for (i in 0..Random.nextInt(from = 1, until = 3)){
        newItem.getWeightedItemPool(Random.nextInt(17))

    }
    pressEnterToContinue()
}


fun play () {

    //creating player character
    while (isAlive){

        //start with a dialogue for moving through dungeon
        println(crawlingDialogue[Random.nextInt(3)])
        dots(Random.nextInt(5))

        //fight function (just so I can keep things organized and modular(?))
        fightEnemy()

        //end for testing
        //isAlive = false
    }
    //when isAlive == false
    print(newlines)
    println(deathDialogue[Random.nextInt(3)])
}
fun main(){
    //First dialogue to establish game
    println("Welcome to the Dungeon! ")
    Thread.sleep(3000)

    play()
}