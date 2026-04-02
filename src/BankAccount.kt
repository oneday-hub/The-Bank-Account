class BankAccount(
    var balance: Int,
    val type: String
) {

    val transactions = mutableListOf<String>()

    fun deposit(amount: Int) {
        balance += amount
        val msg = "Deposited $amount. Balance: $balance"
        println(msg)
        transactions.add(msg)
    }

    fun withdraw(amount: Int) {
        if (amount > balance) {
            val msg = "Failed: Not enough balance"
            println(msg)
            transactions.add(msg)
        } else {
            balance -= amount
            val msg = "Withdrew $amount. Balance: $balance"
            println(msg)
            transactions.add(msg)
        }
    }

    fun showTransactions() {
        if (transactions.isEmpty()) {
            println("No transactions yet.")
        } else {
            println("Transaction History:")
            transactions.forEach { println(it) }
        }
    }
}