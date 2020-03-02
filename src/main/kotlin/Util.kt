fun <T> List<T>.add(index: Int, value: T) =
    this.subList(0, index) + listOf(value) + this.subList(index, this.size)

fun <T> List<T>.update(requestedIndex: Int, newValue: T) =
    this.mapIndexed { index, oldValue ->
        if (index == requestedIndex) newValue else oldValue
    }