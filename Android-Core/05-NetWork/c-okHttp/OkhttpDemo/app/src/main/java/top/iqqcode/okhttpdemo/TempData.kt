package top.iqqcode.okhttpdemo

data class TempData(
    val count: String,
    val info: String,
    val infocode: String,
    val lives: List<Live>,
    val status: String
)