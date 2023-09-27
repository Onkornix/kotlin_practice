object Use {
    //val input:String = readln()
    val input:String = "hello world"
    val inputList: MutableList<Char> = mutableListOf()}
    /*val StringIntegerMap = mapOf(
        'a' to 0, 'b' to 1, 'c' to 2, 'd' to 3, 'e' to 4, 'f' to 5, 'g' to 6,
        'h' to 7, 'i' to 8, 'j' to 9, 'k' to 10, 'l' to 11, 'm' to 12, 'n' to 13,
        'o' to 14, 'p' to 15, 'q' to 16, 'r' to 17, 's' to 18, 't' to 19, 'u' to 20,
        'v' to 21, 'w' to 22, 'x' to 23, 'y' to 24, 'z' to 25,
        )
     */
    val StringIntegerTable: List<String> = listOf("a","b","c","d","e")
fun main(){
    println("Enter a sentence or word and it will get alphabetized")
    for (l in Use.input){
        Use.inputList.add(l)
    }
    if (' ' in Use.inputList) sentence(Use.inputList)
    else word(Use.inputList)
}
fun sentence(inputSentence:MutableList<Char>) {
    // Detach words separated by spaces and add them to a wordList
    // convert and sort only the first Char of each word
    val wordBuild: MutableList<Char> = mutableListOf() // base for dissecting sentence and building individual words
    val wordList: MutableList<String> = mutableListOf() // base for adding words to list
    for (l in Use.inputList){
        when {
            (l == ' ') -> { //when space is encountered
                wordList.add(wordBuild.joinToString("")) // concat wordBuild and add to wordList
                wordBuild.clear()
            }
            (l == Use.inputList.last()) -> { //when last letter is reached
                wordBuild.add(l)
                wordList.add(wordBuild.joinToString(""))
                wordBuild.clear()
            }

        }
        wordBuild.add(l)
    }
    convert(inType="String",convertee=wordList[0])
}
fun word(inputWord:MutableList<Char>) {
    //sort whole word alphabetically
}
fun convert(inType:String,convertee:String) {
    /*
   Alphabetical Map of Chars and corresponding Int

   Converting Char to an Int
   AND
   Converting Int to Char
    */
}
fun sort(sortee:MutableList<Int>) {
    // Sorting the list of Ints into smallest to largest
    // return sorted list
}