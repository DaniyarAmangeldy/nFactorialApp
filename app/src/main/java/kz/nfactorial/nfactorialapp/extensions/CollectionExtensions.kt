package kz.nfactorial.nfactorialapp.extensions

object CollectionExtensions {

    fun <T> Set<T>.addOrRemove(item: T): MutableSet<T> {
        val mutableSet = toMutableSet()
        val updatedSet = if (!mutableSet.add(item)) {
            mutableSet.apply { remove(item) }
        } else {
            mutableSet
        }
        return updatedSet
    }
}