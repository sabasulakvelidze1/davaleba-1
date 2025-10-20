open class Account(
    val accountNumber: String,
    val ownerName: String
) {
    private var balance: Double = 0.0

    fun getBalance(): Double = balance

    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
            println("Deposit: $amount to $accountNumber — new balance: ${"%.2f".format(balance)}")
        } else {
            println("Deposit failed: amount must be > 0")
        }
    }

    open fun withdraw(amount: Double) {
        if (amount <= 0) {
            println("Withdraw failed: amount must be > 0")
            return
        }

        if (balance >= amount) {
            balance -= amount
            println("Withdraw: $amount from $accountNumber — new balance: ${"%.2f".format(balance)}")
        } else {
            println("Withdraw failed: insufficient funds for $accountNumber (requested: $amount, balance: ${"%.2f".format(balance)})")
        }
    }

    fun printInfo() {
        println("----- Account Info -----")
        println("Account #: $accountNumber")
        println("Owner: $ownerName")
        println("Balance: ${"%.2f".format(getBalance())}")
        println("------------------------")
    }
}

class SavingsAccount(accountNumber: String, ownerName: String) : Account(accountNumber, ownerName) {
    override fun withdraw(amount: Double) {
        if (amount > 500.0) {
            println("Withdraw failed: SavingsAccount limit is 500 per transaction (requested: $amount).")
            return
        }
        super.withdraw(amount)
    }
}

class VIPAccount(accountNumber: String, ownerName: String, private val transactionFee: Double = 2.0) :
    Account(accountNumber, ownerName) {

    override fun withdraw(amount: Double) {
        val totalAmount = amount + transactionFee
        super.withdraw(totalAmount)
        if (getBalance() >= 0) {

        }
    }
}

fun main() {
    val acc1 = SavingsAccount("S101", "Giorgi G.")
    val acc2 = VIPAccount("V202", "Nuca K.")


    acc1.deposit(1000.0)
    acc1.withdraw(300.0)
    acc1.withdraw(600.0)


    acc2.deposit(1000.0)
    acc2.withdraw(50.0)
    acc2.printInfo()


    val accounts: List<Account> = listOf(acc1, acc2)
    for (account in accounts) {
        account.deposit(50.0)
        account.printInfo()
    }
}
