package main.me.jikud

import java.awt.Dimension
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
            price *= count + 0.4
//            price = "$price".format("%.2f").toDouble()
            updateText()
        }
    }

    fun updateButton() {
        isEnabled = CookieClicker.score > price*1000
    }
}