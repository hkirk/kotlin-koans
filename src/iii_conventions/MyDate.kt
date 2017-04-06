package iii_conventions

import iv_properties.toMillis

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
    operator fun compareTo(other: MyDate): Int = (this.toMillis() - other.toMillis()).toInt()
    operator fun  plus(timeInterval: TimeInterval): MyDate = this.addTimeIntervals(timeInterval, 1)
    operator fun  plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.repeat)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

class RepeatedTimeInterval(val timeInterval: TimeInterval, val repeat: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, i);
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate): Boolean = start.toMillis() < date.toMillis() && date.toMillis() <= endInclusive.toMillis()
    operator fun iterator(): Iterator<MyDate> = object: Iterator<MyDate> {
        var date = start
        override fun hasNext(): Boolean = date <= endInclusive

        override fun next(): MyDate {
            val oldDate = date
            date = date.nextDay()
            return oldDate
        }

    }
}
