object Use {
    //val input:String = readln()
    val input:String = "hello world"
    val inputList: MutableList<Char> = mutableListOf()}
    val StringIntegerMap = mapOf(
        'a' to 0,
        'b' to 1
    )

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
            (l == ' ') -> {
                wordList.add(wordBuild.joinToString("")) // concat wordBuild and add to wordList
                wordBuild.clear()
            }
            (l == Use.inputList.last()) -> {
                wordBuild.add(l)
                wordList.add(wordBuild.joinToString(""))
                wordBuild.clear()
            }
        }
        wordBuild.add(l)


    }
    convert("String","Int",wordList[0])
}
fun word(inputWord:MutableList<Char>) {
    //sort whole word alphabetically
}
fun convert(inType:String,outType:String,convertee:String) {
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
