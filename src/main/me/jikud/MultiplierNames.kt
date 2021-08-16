package main.me.jikud

import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JPanel

enum class MultiplierNames(val prefix: String, val multiplier: Int, val price: Long) {
    NONE("", 0, 0),
    TWICE_1("x2", 2, 1_000L),
    TWICE_2("x2", 3, 1_000_000L),
    TWICE_3("x2", 4, 99_000_000L),
    TWICE_4("x2", 5, 50_000_000_000L),
    TWICE_X("x2", 6, 999_000_000_000L);

    companion object {
        fun list(): Array<MultiplierNames> {
            val arr = arrayListOf<MultiplierNames>()
            values().forEach {
                if (it != NONE) {
                    arr += it
                }
            }
            return arr.toTypedArray()
        }
    }
}