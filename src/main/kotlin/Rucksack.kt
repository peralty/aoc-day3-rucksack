data class Rucksack(
    val name: String,
    val firstCompartment: List<Char>,
    val secondCompartment: List<Char>,
    val allItems: List<Char>
) {
    fun identifyDuplicate(): Char? {
        return this.firstCompartment.intersect(this.secondCompartment.toSet()).firstOrNull()
    }
}
