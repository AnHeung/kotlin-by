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
    Example().also {
        it.p = "test"
        log(it.p)
    }

    lazyValue
    lazyValue
    val user = User()
    user.apply {
        name ="1"
        name ="2"
    }
    user.optionalName = "11"
    user.optionalName = "22"
    user.optionalName = "21"
    user.optionalName = "33"

    val classWithDelegate = ClassWithDelegate(anotherClassInt = 11)
    val myClass = MyClass(memberInt = 12, anotherClassInstance = classWithDelegate)
    log(myClass.delegatedToMember)
    log(myClass.callByValue)
    log(myClass.delegatedToTopLevel)
    topLevelInt = 16
    log(myClass.callByValue)
    log(myClass.delegatedToTopLevel)
    log(myClass.delegatedToAnotherClass)
    log(myClass.extDelegated)

    val myNewClass = MyNewClass()
    myNewClass.run {
        oldName = 11
        log(oldName)
        log(newName)
    }
    val map = MapDelegateTest(mapOf(
        "name" to "kuma",
        "age"  to 30
    ))

    map.run {
        log(name)
        log(age)
    }
    val mutableMap = MutableMapDelegateTest(mutableMapOf(
        "name" to "kuma",
        "age"  to 30
    ))

    mutableMap.name = ""
    mutableMap.age = 1

    example(true) {
        Foo(true)
    }
}


class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef 의 '${property.name}' 라는 변수값에 위임받음 "
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        log("$thisRef 안에 '${property.name}' 변수에 $value 가 주입됨.")
    }
}

val lazyValue : String by lazy {
    log("hello man~")
    "Hello"
}

class User {
    var name: String by Delegates.observable("초기값") { prop, old, new ->
        println("$old -> $new")
    }
    var optionalName: String by Delegates.vetoable("초기값"){property, oldValue, newValue ->
        println("$oldValue -> $newValue")
        newValue.contains("1")
    }
}


