class MapDelegateTest(private val map: Map<String, Any?>) {
    val name by map
    val age by map
}

class MutableMapDelegateTest(private val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int by map
}