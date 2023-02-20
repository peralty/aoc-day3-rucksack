data class Rucksack(
    val name: String,
    val firstCompartment: List<Char>,
    val secondCompartment: List<Char>
) {
    fun identifyDuplicate(): Char? {
        return this.firstCompartment.intersect(this.secondCompartment.toSet()).firstOrNull()
    }
}
