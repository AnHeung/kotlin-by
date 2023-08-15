
fun log(msg:Any? = ""){
    val currentThreadName = Thread.currentThread().name
    println("현재 동작중인 Thread : $currentThreadName , msg : $msg")
}