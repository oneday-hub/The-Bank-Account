fun main() {

    val account = BankAccount(500, "debit")

    var isRunning = true

    while (isRunning) {
        println("1. Check balance")
        println("2. Deposit")
        println("3. Withdraw")
        println("4. History")
        println("5. Exit")

        val option = readLine()!!.toInt()

        when (option) {
            1 -> println("Balance: ${account.balance}")
            2 -> {
                println("Enter amount:")
                val amt = readLine()!!.toInt()
                account.deposit(amt)
            }
            3 -> {
                println("Enter amount:")
                val amt = readLine()!!.toInt()
                account.withdraw(amt)
            }
            4 -> account.showTransactions()
            5 -> {
                isRunning = false
                println("Goodbye!")
            }
        }
    }
}