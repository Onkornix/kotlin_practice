import kotlin.random.Random


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
    "BaseDamage" to 10,
    "Level" to 1,
    "TokensNeeded" to 19,
    "BonusDamage" to 0
)
val playerInventory: MutableList<String> = mutableListOf()

//Classes
class Enemy {
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


class Item {

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
        "LevelUp" to listOf(2, 1),
        "token" to listOf(2, 0)
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
    val itemListForIndexingOrderedInvMap: MutableList<String> = mutableListOf()

    fun getItemsInInventory() {

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
                    playerStats.run {
                        (getValue("CurrentHP") + itemStats[useItemName]!![1]).also {
                            set("CurrentHP", it)
                        }
                    }
                    if (playerStats["CurrentHP"]!! > playerStats["MaxHP"]!!) {
                        playerStats.run { set("CurrentHP", getValue("MaxHP")) }
                    }
                    //println(main.kotlin.getPlayerStats["CurrentHP"])
                }
                1 -> {
                    playerStats.run {
                        (getValue("BonusDamage") + itemStats[useItemName]!![1]).also {
                            set("BonusDamage", it)
                        }
                    }
                }
                2 -> {
                    playerStats.run {
                        (getValue("Level") + itemStats[useItemName]!![1]).also {
                            set("Level", it + itemStats[useItemName]!![0])

                        }
                    }
                }
            }
            //removes used item from inventory
            if (itemStats[useItemName]!![0] != 2) {
                playerInventory.run { removeAt(indexOf(useItemName)) }
                useTotal++
            }
        }
    }
    fun getLevelUpTokens(): Int {
        val quantity = Random.nextInt(from = 2, until = 5)

        for (a in 0..quantity) playerInventory.add(playerInventory.size,"token" )

        return quantity
    }
}

//Hella functions
fun levelUpCheck(){
    var tokenCount = 0
    for (index in 0..<playerInventory.size){
        if (playerInventory[index] == "token"){
            tokenCount++
        }
    }

    if (tokenCount >= playerStats["TokensNeeded"]!!){

        // increase level, MaxHP, BaseDamage
        playerStats.run {
            getValue("Level").also{ set("Level", it + 1) }
            getValue("MaxHP").also{ set("MaxHP", it + 20) }
            getValue("BaseDamage").also{ set("BaseDamage", it + 5) }
        }

        // print something
        println("Level Up! \n" +
                "Level: ${playerStats["Level"]!! - 1} -> ${playerStats["Level"]}\n" +
                "Max HP: ${playerStats["MaxHP"]!! - 20} -> ${playerStats["MaxHP"]}\n" +
                "Base Damage: ${playerStats["BaseDamage"]!! - 5} -> ${playerStats["BaseDamage"]}\n")
        pressEnterToContinue()

        // removing spent tokens
        for (index in 1..playerStats["TokensNeeded"]!!){
            playerInventory.remove("token")
        }

        // increase TokensNeeded
        playerStats.run {
            (getValue("TokensNeeded") + (getValue("TokensNeeded"))/2).also {
                set("TokensNeeded", it)
            }
        }

    }else println("not enough to level up")
}


//this is literally just for printing a varying number of main.kotlin.dots
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

//main.kotlin.main combat loop after enemy encounter
fun fightEnemy(){

    //creating enemy
    val enemy = Enemy()
    val enemyInFight = enemy.engagement
    var actionCondition = false
    print(newlines)

    // removing bonus damage
    playerStats["BonusDamage"] = 0

    while (enemy.enemyStats[enemyInFight]!!["HP"]!! > 0) {

        //printing enemy stats and possible player actions
        println(
            "$enemyInFight: \n\n Health: ${enemy.enemyStats[enemyInFight]!!["HP"]}   " +
                    "Damage: ${enemy.enemyStats[enemyInFight]!!["DMG"]} \n\n" +
                    "Your Health: ${playerStats["CurrentHP"]} \n" +
                    "Your actions:   (a)ttack, (i)ventory, (s)tats"

        )
        //player action
        when (readln().lowercase()) {

            //attack enemy
            "a" -> {
                println(
                    "You swing your sword and strike the $enemyInFight, " +
                            "dealing ${playerStats["BaseDamage"]!! * playerStats["Level"]!!} damage",
                )
                Thread.sleep(1000)
                enemy.enemyStats[enemyInFight]!!.run {
                    (getValue("HP") - (playerStats["BaseDamage"]!! + playerStats["BonusDamage"]!!)).also {
                        set("HP", it)
                    }
                }
                actionCondition = true
            }

            //access inventory
            "i" -> {
                val item = Item()
                item.getItemsInInventory()
                println("\naction: (u)se item, (r)eturn to battle")
                val action: String = readln().lowercase()
                when (action){

                    //use item
                    "u" -> {
                        println("enter item number: ")
                        val itemIndexInput: Int = readln().toInt()

                        //if the item is "token" don't ask for how many
                         if (item.itemListForIndexingOrderedInvMap[itemIndexInput] == "token") {
                            levelUpCheck()
                        } else {
                             println("how many to use: ")
                             val amount: Int = readln().toInt()
                             item.useItem(itemIndexInput,amount)
                        }
                    }

                    //return to battle
                    "r" -> {
                        continue
                    }
                }

            }

            //stats
            "s" -> {
                printPlayerCurrentStats()
                pressEnterToContinue()
            }
            else -> {
                println("mistakes are important, but never do that again")
                actionCondition = true
            }

        }

        //if player chose the attack action, and the enemy is not dead, player receives damage
        if (actionCondition && enemy.enemyStats[enemyInFight]!!["HP"]!! > 0){

            //waiting and creating main.kotlin.newlines for enemy attack text
            Thread.sleep(1500)
            print(newlines)
            Thread.sleep(1000)

            //enemy attack
            println("The vile $enemyInFight hits you and deals ${enemy.enemyStats[enemyInFight]!!["DMG"]} damage \n\n") //display attack text

            //change player health
            playerStats.run {
                (getValue("CurrentHP") - enemy.enemyStats[enemyInFight]!!["DMG"]!!). also {
                    set("CurrentHP", it)
                }
            }
            Thread.sleep(1500)
        } else {
            continue
        }


    }

    val newItem = Item()
    println("congratulations! you defeated the $enemyInFight!! And it dropped:")
    Thread.sleep(1500)

    //dispense items
    for (i in 0..Random.nextInt(from = 1, until = 3)){
        Thread.sleep(750)
        newItem.getWeightedItemPool(Random.nextInt(17))

    }

    //dispense tokens
    Thread.sleep(750)
    println("You also acquired ${newItem.getLevelUpTokens()} Level up tokens")


    Thread.sleep(1000)
    print("\n")
    pressEnterToContinue()
}



fun main(){
    //First dialogue to establish game
    println("Welcome to the Dungeon! ")
    Thread.sleep(3000)


    //creating player character
    while (isAlive){

        //start with a dialogue for moving through dungeon
        println(crawlingDialogue[Random.nextInt(3)])
        dots(Random.nextInt(5))

        //fight function (just so I can keep things organized and modular(?))
        fightEnemy()

    }

    //when main.kotlin.isAlive == false
    print(newlines)
    println(deathDialogue[Random.nextInt(3)])

}