package main.me.jikud

enum class MultiplierNames(val multiplier: Long, val price: Long) {
    NONE(0, 0),
    TWICE_1(2, 1_000L),
    TWICE_2(2, 1_000_000L),
    TWICE_3(2, 99_000_000L),
    TWICE_4(2, 50_000_000_000L),
    TWICE_X(2, 999_000_000_000L),

}