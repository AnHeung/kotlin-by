fun example(condition: Boolean = false, computeFoo: () -> Foo) {
    val memoizedFoo by lazy(computeFoo)

    if (condition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
    }
}

class Foo(private val someCondition: Boolean = false) {

    fun isValid(): Boolean = someCondition
    fun doSomething(): Unit {
        log("Foo doSomething")
    }
}
