import kotlin.random.Random

class EnemyAndItemStats (name: String, damage: Int, health: Int) {
    var EnemyConstructor = arrayOf(name, damage, health)
}
object CollectionsOfDialogueAndEncounters {

    val EnemyStatArrayMap = mapOf(
        "medium T" to arrayOf(5, 200),
        "goblins" to arrayOf(10, 75)

    )
    val ItemStatArrayMap = mapOf(
        // bd = "bonus damage" on hit
        "banana" to arrayOf("heal", -10),
        "evil juice" to arrayOf("bd", 500000)
    )

    private val RandomCrawlingEncounterDialogue = listOf(
        "hoard of goblins",
        "bunch of spiders",
        "man named speev"
    )
    private val RandomItemEncounter = listOf(
        "banana"
    )
    private val RandomCrawlingDialogue = listOf(
        "You continue along and encounter a ",
        "A hole opens up in the floor and inside there's a ",
        "Something drops from the ceiling... It's a "
    )
    private val RandomDeathdialogue = listOf(
        "You died that's sad"
    )


}



fun main(){

    //opening procedure
    println("welcome to the dungeon")
    Thread.sleep(1000)



    var enemy = EnemyAndItemStats("goblin", 5, 100)



}