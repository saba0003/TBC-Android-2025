import kotlin.random.Random

fun main() {
    start()
}

fun start() {
    while(true) {
        print("Enter x: ")
        val x = readln()

        print("Enter y: ")
        val y = readln()

        println("Enter operation (add, sub, mul, div, mod):")
        val op = readln()

        val result: String = expressionEvaluatorFromStrings(x = x, y = y, op = op)
        println("\n$result")

        print("\nWanna start over? (Y/N): ")
        val answer = readln().uppercase()

        if (answer != "Y") {
            terminus()
            break
        } else {
            println()
        }
    }
}

fun expressionEvaluatorFromStrings(x: String = "2nnk4b67", y: String = "0nnvkb78f9f", op: String = "add") : String {

    /** AUX */
    fun numberExtractorOrRandomizer(s: String): Long {
        val digits = s.filter{it.isDigit()}
        return if (digits.isNotEmpty()) digits.toLong()
               else Random.nextLong(from = -10_000_000, until = 10_000_001)
    }

    val num1: Long = numberExtractorOrRandomizer(s = x)
    val num2: Long = numberExtractorOrRandomizer(s = y)

    val result: Long = when (op.trim().lowercase()) {
        "add" -> num1 + num2
        "sub" -> num1 - num2
        "mul" -> num1 * num2
        "div" -> if (num2 != 0L) num1 / num2 else throw ArithmeticException("Division by zero")
        "mod" -> if (num2 != 0L) num1 % num2 else throw ArithmeticException("Modulo by zero")
        else -> throw UnsupportedOperationException("Unsupported operation type: $op")
    }

    return """
        original 'x': $x; original 'y': $y
        'x' converted to number: $num1; 'y' converted to number: $num2
        x.$op(y) -> $result
    """.trimIndent()
}

fun terminus() {
    println("\nAll threads closed. Unlike my browser tabs... :skull:")
}
