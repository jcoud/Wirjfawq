package main.me.jikud

import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JPanel

enum class MultiplierNames(val multiplier: Long, val price: Long) {
    NONE(0, 0),
    TWICE_1(2, 1_000L),
    TWICE_2(2, 1_000_000L),
    TWICE_3(2, 99_000_000L),
    TWICE_4(2, 50_000_000_000L),
    TWICE_X(2, 999_000_000_000L);

    private val btn = JButton()
    val button = JPanel()
    var text_ = ""

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

    init {
        button.add(btn.apply {
            preferredSize = Dimension(30, 30)
            text = text_
            addActionListener {
                when (this@MultiplierNames) {
                    TWICE_1 -> {
                        ActionButtonNames.COOPER.additionalMultiplier += 2
                    }
                    TWICE_2 -> {
                        ActionButtonNames.COOPER.additionalMultiplier += 2
                    }
                    TWICE_3 -> {
                        ActionButtonNames.COOPER.additionalMultiplier += 2
                    }
                    TWICE_4 -> {
                        ActionButtonNames.COOPER.additionalMultiplier += 2
                    }
                    TWICE_X -> {
                        ActionButtonNames.COOPER.additionalMultiplier += 2
                    }
                    else -> return@addActionListener
                }
            }
        })
    }

    fun update() {

    }
}