import java.io.File

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")
    if(args.isEmpty()){
        println("Program needs a path to find the input file as param")
        return
    }
    val path = args[0]
    val file = File(path)
    if (!file.isFile){
        println("No file detected at path: $path")
        return
    }
    val sum = sumTwoDigitNumbersFromFile(file)
    println("Sum of all this is: $sum")
    return
}
fun sumTwoDigitNumbersFromFile(file: File): Int {
    val numbers = mutableListOf<Int>()
    file.forEachLine { line: String ->
        var twoDigits = ""
        twoDigits += line.first { c: Char -> c.isDigit() }
        twoDigits += line.last { c: Char -> c.isDigit() }
        if (twoDigits.isBlank()) {
            println("Careful we got a line with no digits!")
        } else {
            println(twoDigits)
            numbers.add(twoDigits.toInt())
        }
    }
    return numbers.sum()
}
