fun main(){
    println("Enter a sentence or word and it will get alphabetized")
    val input:String = readln()
    val inputList: MutableList<Char> = mutableListOf()
    for (l in input){
        inputList.add(l)
        //println(l)
    }
    if (' ' in inputList) sentence(inputList)
    else word(inputList)

}

fun sentence(inputSentence:MutableList<Char>) {
    // Detach words separated by spaces and add them to a wordList
    // convert and sort only the first Char of each word
    var wordBuild: MutableList<Char> = mutableListOf() // base for dissecting sentence and building individual words
    var wordList: MutableList<String> = mutableListOf() // base for adding words to list
}
fun word(inputWord:MutableList<Char>) {
    //sort whole word alphabetically
}
fun convert(inType:String,outType:String,convertee:Char) {
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
