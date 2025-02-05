package kz.nfactorial.nfactorialapp.extensions

object IntExtensions {

    fun Int.toShortString(): String {
        return when {
            this < 1000 -> this.toString()
            this < 1_000_000 -> {
                val divided = this / 1000.0
                // Format with one decimal place and remove trailing .0 if present
                String.format("%.1f", divided).replace(".0", "") + "k"
            }
            else -> {
                val divided = this / 1_000_000.0
                String.format("%.1f", divided).replace(".0", "") + "m"
            }
        }
    }
}