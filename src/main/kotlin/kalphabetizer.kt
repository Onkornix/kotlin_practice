
//val input:String = readln()
val input:String = "hello"
val inputList: MutableList<Char> = mutableListOf()
val StringIntegerMap: Map<Char, Int> = mapOf(
    'a' to 0, 'b' to 1, 'c' to 2, 'd' to 3, 'e' to 4, 'f' to 5, 'g' to 6,
    'h' to 7, 'i' to 8, 'j' to 9, 'k' to 10, 'l' to 11, 'm' to 12, 'n' to 13,
    'o' to 14, 'p' to 15, 'q' to 16, 'r' to 17, 's' to 18, 't' to 19, 'u' to 20,
    'v' to 21, 'w' to 22, 'x' to 23, 'y' to 24, 'z' to 25,
)

fun main(){
    //println(StringIntegerTable.indexOf("a"))
    println("Enter a sentence or word and it will get alphabetized")
    for (l in input){
        inputList.add(l)
    }
    if (' ' in inputList) { // start sentence sorting
        // Detach words separated by spaces and add them to a wordList
        // convert and sort only the first Char of each word
        val wordBuild: MutableList<Char> = mutableListOf() // base for dissecting sentence and building individual words
        val wordList: MutableList<String> = mutableListOf() // base for adding words to list
        //val charWordMap: MutableMap<Char, String> = mutableMapOf()
        for (l in inputList){
            // conditions
            when {
                (l == ' ') -> { //when space is encountered
                    wordList.add(wordBuild.joinToString("")) // concat wordBuild and add to wordList
                    wordBuild.clear()
                }
                (l == inputList.last()) -> { //when last letter is reached
                    wordBuild.add(l)
                    wordList.add(wordBuild.joinToString("")) // concat wordBuild and add to wordList
                    wordBuild.clear()
                }
                else -> wordBuild.add(l)
            }
        }
        print(wordList[0])

    }




    // Sorting a single word.
    else {



        // breaking up, changing to Int, replacing
        val charIntList: MutableList<Int> = mutableListOf() // character to integer list init
        for (c in inputList){ // taking Characters
            charIntList.add(StringIntegerMap[c]!!) //
        }


        //sorting (kinda messed up how easy it is lol. I don't even need to convert it to Int)
        charIntList.sort()


        //
        val intCharList: MutableList<Char> = mutableListOf()
        for (i in charIntList){ // taking integers
            intCharList.add(StringIntegerMap.filterValues { it == i }.keys)
        }
    }
}