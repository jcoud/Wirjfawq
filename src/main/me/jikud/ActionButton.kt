package main.me.jikud

import java.awt.Dimension
import java.math.BigDecimal
import java.math.RoundingMode
import javax.swing.JButton
import kotlin.math.floor

class ActionButton(private var nn: String, private val multiplier: Double, private var price: Double) : JButton() {
    var count = 0
    fun updateText() {
        this.text = "$nn + ($count) : $multiplier, $price"
    }

    init {
        updateText()
        font = CookieClicker.font
        size = Dimension(45, 25)
        addActionListener {
//            if (it.actionCommand != name) return@addActionListener
            CookieClicker.multiplier += multiplier
            CookieClicker.score -= price * 1000
            count++
            price *= floor(count + 0.4)
//            price = "$price".format("%.2f").toDouble()
            updateText()
        }
    }

    fun updateButton() {
        isEnabled = CookieClicker.score > price*1000
    }
}