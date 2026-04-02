fun main() {

    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType = ""
    var userChoice = 0

    // ✅ NEW: transaction history list
    val transactions = mutableListOf<String>()

    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = (1..5).random()
        println("The selected option is $userChoice.")

        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue
        }
    }

    println("You have created a $accountType account.")

    var accountBalance = (0..1000).random()
    println("The checking balance is $accountBalance dollars.")

    val money = (0..1000).random()
    println("The amount you transferred is $money dollars.")

    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        val msg = "Withdrew $amount dollars. Balance: $accountBalance"
        println(msg)
        transactions.add(msg)   // ✅ store history
        return amount
    }

    fun debitWithdrew(amount: Int): Int {
        if (accountBalance == 0) {
            val msg = "Failed: No money to withdraw"
            println(msg)
            transactions.add(msg)
            return accountBalance

        } else if (amount > accountBalance) {
            val msg = "Failed: Not enough balance. Balance: $accountBalance"
            println(msg)
            transactions.add(msg)
            return 0

        } else {
            return withdraw(amount)
        }
    }

    fun deposit(amount: Int): Int {
        accountBalance += amount
        val msg = "Deposited $amount dollars. Balance: $accountBalance"
        println(msg)
        transactions.add(msg)   // ✅ store history
        return amount
    }

    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            val msg = "Account already paid off!"
            println(msg)
            transactions.add(msg)
            return accountBalance

        } else if (accountBalance + amount > 0) {
            val msg = "Deposit failed: Overpaying credit balance"
            println(msg)
            transactions.add(msg)
            return 0

        } else if (amount == -accountBalance) {
            accountBalance = 0
            val msg = "Credit account fully paid!"
            println(msg)
            transactions.add(msg)
            return amount

        } else {
            return deposit(amount)
        }
    }

    // ✅ NEW: show history function
    fun showTransactions() {
        if (transactions.isEmpty()) {
            println("No transactions yet.")
        } else {
            println("Transaction History:")
            transactions.forEach { println(it) }
        }
    }

    fun transfer(mode: String) {
        val transferAmount: Int

        when (mode) {
            "withdraw" -> {
                if (accountType == "debit") {
                    transferAmount = debitWithdrew(money)
                } else {
                    transferAmount = withdraw(money)
                }
                println("The amount you withdrew is $transferAmount dollars.")
            }

            "deposit" -> {
                if (accountType == "credit") {
                    transferAmount = creditDeposit(money)
                } else {
                    transferAmount = deposit(money)
                }
                println("The amount you deposited is $transferAmount dollars.")
            }

            else -> return
        }

        var isSystemOpen = true
        var option = 0

        while (isSystemOpen) {
            println("What would you like to do?")
            println("1. Check bank account balance")
            println("2. Withdraw money")
            println("3. Deposit money")
            println("4. View transaction history")   // ✅ NEW OPTION
            println("5. Close the system")

            option = (1..5).random()
            println("The selected option is $option.")

            when (option) {
                1 -> println("The current balance is $accountBalance dollars.")
                2 -> transfer("withdraw")
                3 -> transfer("deposit")
                4 -> showTransactions()   // ✅ CALL HISTORY
                5 -> {
                    isSystemOpen = false
                    println("The system is closed")
                }
                else -> continue
            }
        }
    }

    // ✅ START SYSTEM
    transfer("deposit")
}





























