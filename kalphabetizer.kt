//global variables
val inputList: MutableList<Char> = mutableListOf()
val StringIntegerMap: Map<Char, Int> = mapOf(
    'a' to 0, 'b' to 1, 'c' to 2, 'd' to 3, 'e' to 4, 'f' to 5, 'g' to 6,
    'h' to 7, 'i' to 8, 'j' to 9, 'k' to 10, 'l' to 11, 'm' to 12, 'n' to 13,
    'o' to 14, 'p' to 15, 'q' to 16, 'r' to 17, 's' to 18, 't' to 19, 'u' to 20,
    'v' to 21, 'w' to 22, 'x' to 23, 'y' to 24, 'z' to 25,
)

fun doWordSorting(){

    //some vars i guess
    //val wordMap: MutableMap<Int, String> = mutableMapOf()
    val wordList: MutableList<String> = mutableListOf()
    val wordBuild: MutableList<Char> = mutableListOf() // base for dissecting sentence and building individual words
    var iteration = 1

    //val charWordMap: MutableMap<Char, String> = mutableMapOf()
    for (l in inputList){

        // conditions
        when {
            //when space is encountered
            (l !in StringIntegerMap) -> {
                wordList.add(wordBuild.joinToString(""))
                wordBuild.clear()
                iteration++
            }

            //when last letter is reached
            (iteration == inputList.size) -> {
                wordBuild.add(l)
                wordList.add(wordBuild.joinToString(""))
                wordBuild.clear()


            }

            //for every other letter
            else -> {
                wordBuild.add(l)
                iteration++
            }
        }
    }
    wordList.sort()
    println(wordList.joinToString(" "))


}


fun doLetterSorting(){

    // breaking up, changing to Int, replacing
    val charIntList: MutableList<Int> = mutableListOf() // character to integer list init
    for (c in inputList){ // taking Characters
        charIntList.add(StringIntegerMap[c]!!)
    }

    //sorting (kinda messed up how easy it is lol. I don't even need to convert it to Int)
    charIntList.sort()


    //getting Character keys from Map using Integer values
    val lastStringList: MutableList<String> = mutableListOf()
    for (i in charIntList){ // taking integers
        val filterValues = StringIntegerMap.filterValues { it == i }
        lastStringList.add(filterValues.keys.joinToString())

    }
    //finally done.
    print(lastStringList.joinToString(""))

}

fun main(){
    //println(StringIntegerTable.indexOf("a"))
    println("Enter a sentence or word and it will get alphabetized")
    val input:String = readln()
    for (l in input){
        inputList.add(l) //creating list of characters
    }
    if (' ' in inputList) {
        doWordSorting() // start sentence sorting
    } else doLetterSorting() // Sorting a single word
}