package main.me.jikud

import java.awt.Color
import javax.swing.JButton
import javax.swing.JPanel
import java.awt.Dimension
import java.text.NumberFormat
import java.util.*
import javax.swing.BorderFactory
import javax.swing.ButtonModel

enum class ActionButtonNames(var multiplier: Long, var price: Long, val color: Color, var bigButton: Boolean = false) {
    BIG_BUTTON,
    COOPER(1, 10, Color.decode("0xAE6B35")),
    LEAD(5, 50, Color.decode("0x504C58")),
    IRON(20, 200, Color.decode("0x7A7679")),
    TUNGSTEN(50, 700, Color.decode("0x5F6E5A")),
    GOLD(100, 1000, Color.decode("0xBBAC2C")),
    PLATINUM(300, 5000, Color.decode("0x96B2BB")),
    TITAN(500, 50000, Color.decode("0x494745"));

    private val btn = JButton()
    val button = JPanel()
    var count = 0
    var additionalMultiplier = 0

    companion object {
        fun list(): Array<ActionButtonNames> {
            val arr = arrayListOf<ActionButtonNames>()
            values().forEach {
                if (it != BIG_BUTTON) {
                    arr += it
                }
            }
            return arr.toTypedArray()
        }
    }

    constructor() : this(0, 0, Color.WHITE, true) {
        button.add(btn.apply {
            preferredSize = Dimension(100, 100)
//            isOpaque = false
//            isContentAreaFilled = false
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

    init {
        updateText()
        button.background = color
        button.add(btn.apply {
            font = OreClicker.font.deriveFont(15f)
            isOpaque = false
            isContentAreaFilled = false
            isBorderPainted = false
            isFocusCycleRoot = false
            isFocusable = false
            isFocusPainted = false
            size = Dimension(250, 20)
            addActionListener {
//            if (it.actionCommand != name) return@addActionListener
                count++
                OreClicker.multiplier += multiplier + additionalMultiplier * count
                OreClicker.score -= price * 1000L
                price = (price * (count + 0.4 + multiplier / 100)).toLong()
//            price = "$price".format("%.2f").toDouble()
                updateText()
            }
        })
    }

    fun updateText() {
        if (bigButton) {
            btn.text = "$count"
        } else
            btn.text = "${name.capitalize()} + ($count) : [${multiplier}] $" +
                    NumberFormat.getNumberInstance(Locale.US).format(price)
    }


    fun update() {
        btn.isEnabled = OreClicker.score >= price * 1000L
        if (btn.isEnabled) {
            button.background.darker().darker()
            btn.foreground = Color.BLACK
        }
        else {
            button.background.brighter().brighter()
            btn.foreground = Color.WHITE
        }
//        button.border = if (btn.isEnabled)
//            BorderFactory.createLineBorder(color, 4)
//        else
//            BorderFactory.createLineBorder(Color.BLACK, 1)
    }
}