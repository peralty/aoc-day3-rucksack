import java.io.File

fun main() {
    val rucksacks = createRucksackListFromInput()

    // Part 1
    val sumOfAllPriorityValues = rucksacks.sumOf { getValueOfItem(it.identifyDuplicate()) }
    println("Summe aller Prioriäten von allen Duplikaten: $sumOfAllPriorityValues")

    // Part 2
    val elfGroups = createElfGroupFromRucksackList(rucksacks)
    val sumOfAllBadgeValues = elfGroups.sumOf { getValueOfItem(identifyBadgeFromElfGroup(it)) }
    println("Summe aller Prioriäten von Badges: $sumOfAllBadgeValues")
}

fun createRucksackListFromInput(): List<Rucksack> {
    val file = File("src/main/resources/rucksack-items.txt")
    var rucksackNameCounter = 0

    return file.readLines().map { line ->
        val halfLength = line.length / 2
        val firstHalf = line.substring(0, halfLength).toMutableList()
        val secondHalf = line.substring(halfLength).toMutableList()
        val allItems = firstHalf + secondHalf
        rucksackNameCounter++
        Rucksack("Rucksack $rucksackNameCounter", firstHalf, secondHalf, allItems)
    }
}

fun getValueOfItem(character: Char?): Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val characterToNumericPriorityMap = alphabet.mapIndexed { index, c -> c to index + 1 }.toMap()
    return characterToNumericPriorityMap.getOrDefault(character, 0)
}

fun createElfGroupFromRucksackList(rucksacks: List<Rucksack>): List<List<Rucksack>> {
    val allElfGroups = mutableListOf(mutableListOf<Rucksack>())
    var elfGroup = mutableListOf<Rucksack>()

    rucksacks.forEach { rucksack ->
        elfGroup.add(rucksack)
        if (elfGroup.size == 3) {
            allElfGroups.add(elfGroup)
            elfGroup = mutableListOf<Rucksack>()
        }
    }

    if (elfGroup.isNotEmpty()) {
        allElfGroups.add(elfGroup)
    }

    allElfGroups.removeIf { it.isEmpty() }
    return allElfGroups
}

fun identifyBadgeFromElfGroup(elfGroup: List<Rucksack>): Char? {
    val badge = (elfGroup[0].allItems.intersect(elfGroup[1].allItems.toSet())).intersect(elfGroup[2].allItems.toSet())
    return if (badge.isNotEmpty()) badge.first() else null
}