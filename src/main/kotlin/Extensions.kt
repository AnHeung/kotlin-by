
fun log(msg:String = ""){
    val currentThreadName = Thread.currentThread().name
    println("현재 동작중인 Thread : $currentThreadName , msg : $msg")
}