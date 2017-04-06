package iii_conventions

import util.TODO


class Invokable {
    var i = 0

    fun getNumberOfInvocations(): Int {
        return i
    }

    operator fun invoke(): Invokable {
        i++
        return this
    }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}
