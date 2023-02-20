import java.io.File

fun main() {
    val rucksacks = createRucksackListFromInput()
    var sumOfAllPriorityValues = 0
    rucksacks.forEach { rucksack ->
        sumOfAllPriorityValues += getValueOfDuplicate(rucksack.identifyDuplicate())
    }
    println("Summe aller Prioriäten: $sumOfAllPriorityValues")
}

fun createRucksackListFromInput(): List<Rucksack> {
    val file = File("src/main/resources/rucksack-items.txt")
    var rucksackNameCounter = 0

    return file.readLines().map { line ->
        val halfLength = line.length / 2
        val firstHalf = line.substring(0, halfLength).toMutableList()
        val secondHalf = line.substring(halfLength).toMutableList()
        rucksackNameCounter++
        Rucksack("Rucksack $rucksackNameCounter", firstHalf, secondHalf)
    }
}

fun getValueOfDuplicate(character: Char?): Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val characterToNumericPriorityMap = alphabet.mapIndexed { index, c -> c to index + 1 }.toMap()
    return characterToNumericPriorityMap.getOrDefault(character, 0)
}