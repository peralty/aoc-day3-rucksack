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
    val allRucksacks = mutableListOf<Rucksack>()
    var rucksackNameCounter = 0

    file.forEachLine { line ->
        var firstCompartmentItems = mutableListOf<Char>()
        var secondCompartmentItems = mutableListOf<Char>()
        val halfLength = line.length / 2
        val firstHalf = line.substring(0, halfLength)
        val secondHalf = line.substring(halfLength)
        firstHalf.forEach { character ->
            firstCompartmentItems.add(character)
        }
        secondHalf.forEach { character ->
            secondCompartmentItems.add(character)
        }
        rucksackNameCounter++
        allRucksacks.add(Rucksack("Rucksack $rucksackNameCounter", firstCompartmentItems, secondCompartmentItems))
    }

    return allRucksacks
}

fun getValueOfDuplicate(character: Char?): Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val characterToNumericPriorityMap = alphabet.mapIndexed { index, c -> c to index + 1 }.toMap()
    return characterToNumericPriorityMap.getOrDefault(character, 0)
}