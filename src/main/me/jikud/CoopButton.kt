package main.me.jikud

import java.awt.Dimension
import java.text.NumberFormat
import java.util.*
import javax.swing.JButton
import javax.swing.JPanel

class CoopButton(private val actionButton: ActionButtonNames, private val multiplier: MultiplierNames) : JButton() {

    init {
        toolTipText = "$" + NumberFormat.getIntegerInstance(Locale.US).format(multiplier.price * actionButton.price)
        text = "${multiplier.prefix}${actionButton.prefix}"
        addActionListener {
            actionButton.additionalMultiplier = multiplier.multiplier
            this.isVisible = false
        }
    }

    fun update() {
        isVisible = actionButton.count >= 20
        isEnabled = OreClicker.score > multiplier.price * 1000L * actionButton.multiplier && actionButton.count >= 20
    }
}