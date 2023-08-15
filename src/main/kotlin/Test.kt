import kotlin.properties.Delegates
import kotlin.reflect.KProperty

interface Base {
    val message: String
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override val message = "BaseImpl: x = $x"
    override fun print() { println(message) }
}

class Derived(b: Base) : Base by b {
    // This property is not accessed from b's implementation of `print`
    override val message = "Message of Derived"

}

fun main() {
    val b = BaseImpl(10)
    val derived = Derived(b)
    derived.print()
    log(derived.message)
    val example = Example().also {
        it.p = "test"
    }
    log(example.p)
    lazyValue
    lazyValue

}


class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

val lazyValue : String by lazy {
    log("hello man~")
    "Hello"
}


