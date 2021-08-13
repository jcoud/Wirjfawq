package main.me.jikud

import java.awt.Color
import java.awt.Dimension
import java.text.NumberFormat
import java.util.*
import javax.swing.BorderFactory
import javax.swing.ButtonModel
import javax.swing.JButton
import javax.swing.JPanel

class ActionButton(private val abn: ActionButtonNames, private var bigButton: Boolean = false): JPanel() {
    private val btn = JButton()
    constructor() : this(ActionButtonNames.NONE, true) {
//        background = Color.BLACK
        this.add(btn.apply {
            preferredSize = Dimension(100, 100)
//            isOpaque = false
            isContentAreaFilled = false
            isBorderPainted = false
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
                    OreClicker.score += 1000
                }
            }
        })
    }

    var count = 0
    fun updateText() {
        if (bigButton) {
            btn.text = "$count"
        } else
            btn.text = "${abn.name.capitalize()} + ($count) : [${abn.multiplier}] $" + NumberFormat.getNumberInstance(Locale.US).format(abn.price)
    }

    init {
        updateText()
        background = abn.color
        this.add(btn.apply {
            font = OreClicker.font.deriveFont(15f)
//            isOpaque = false
            isContentAreaFilled = false
            isBorderPainted = false
            isFocusCycleRoot = false
            isFocusable = false
            isFocusPainted = false
//        size = Dimension(250, 20)
            addActionListener {
//            if (it.actionCommand != name) return@addActionListener
                OreClicker.multiplier += abn.multiplier
                OreClicker.score -= abn.price * 1000L
                count++
                abn.price = (abn.price * (count + 0.4 + abn.multiplier / 100)).toLong()
//            price = "$price".format("%.2f").toDouble()
                updateText()
            }
        })
    }

    fun updateButton() {
        btn.isEnabled = OreClicker.score >= abn.price * 1000L
        this.border = if (btn.isEnabled)
            BorderFactory.createLineBorder(abn.color, 4)
        else
            BorderFactory.createLineBorder(Color.BLACK, 1)
    }
}