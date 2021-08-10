package main.me.jikud

import java.awt.Color
import java.awt.Dimension
import java.text.NumberFormat
import java.util.*
import javax.swing.BorderFactory
import javax.swing.ButtonModel
import javax.swing.JButton
import kotlin.math.floor

class ActionButton(
    private var nn: String,
    private val multiplier: Int,
    private var price: Int,
    private val bigButton: Boolean = false
) : JButton() {
    constructor() : this("", 0, 0, true) {
        preferredSize = Dimension(100, 100)
        background = Color.BLACK
        isOpaque = false
//            isContentAreaFilled = false
//            isBorderPainted = false
        isFocusCycleRoot = false
        isFocusable = false
        isFocusPainted = false
        border = BorderFactory.createLineBorder(Color.BLACK)
        model.addChangeListener {
            val model = it.source as ButtonModel
            if (model.isRollover) {
                background = Color.PINK
            }
            if (model.isPressed) {
                background = Color.RED
                CookieClicker.score += 1000
            }
        }
    }

    var count = 0
    fun updateText() {
        if (bigButton) {
            this.text = "$count"
        } else
            this.text = "$nn + ($count) : [$multiplier] $"+NumberFormat.getNumberInstance(Locale.FRANCE).format(price.toDouble())
    }

    init {
        updateText()
        font = CookieClicker.font.deriveFont(15f)
//        size = Dimension(250, 20)
        addActionListener {
//            if (it.actionCommand != name) return@addActionListener
            CookieClicker.multiplier += multiplier
            CookieClicker.score -= price * 1000
            count++
            price = (price * (count + 0.4)).toInt()
//            price = "$price".format("%.2f").toDouble()
            updateText()
        }
    }

    fun updateButton() {
        isEnabled = CookieClicker.score > price * 1000
    }
}