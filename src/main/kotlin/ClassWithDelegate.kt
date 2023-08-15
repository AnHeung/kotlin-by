var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)

class MyClass(var memberInt: Int, val anotherClassInstance  : ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt

    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}

var MyClass.extDelegated: Int by ::topLevelInt

class MyNewClass {
    var newName: Int = 0
    @Deprecated("oldName -> newName 으로 바뀌었다... 새로운 값을 쓰거라", ReplaceWith("newName"))
    var oldName: Int by this::newName
}
