package main.me.jikud

import java.awt.*
import java.text.NumberFormat
import java.util.*
import javax.swing.*


enum class ActionButtonNames {
    BIG_BUTTON,
    COOPER("Co", 1, 10, Color.decode("0xAE6B35")),
    LEAD("Le", 5, 50, Color.decode("0x504C58")),
    IRON("Ir", 20, 200, Color.decode("0x7A7679")),
    TUNGSTEN("Tu", 50, 700, Color.decode("0x5F6E5A")),
    GOLD("Go", 100, 1000, Color.decode("0xBBAC2C")),
    PLATINUM("Pl", 300, 5000, Color.decode("0x96B2BB")),
    TITAN("Ti", 500, 50000, Color.decode("0x494745"));

    var count = 0
    var additionalMultiplier = 0
    var prefix = ""
    var multiplier = 0L
    var price = 0L
    var color = Color.BLACK
    var bigButton = false
    val button = ColorButton(color)

    class ColorButton(var bgcolor: Color) : JButton() {
        override fun paintComponent(g: Graphics) {
            val g2 = g.create() as Graphics2D
            g2.color = bgcolor
            g2.fillRect(0, 0, width, height)
            g2.dispose()
            super.paintComponent(g)
        }
    }

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

    constructor() {
        this.bigButton = true
        updateText()
        button.apply {
            border = BorderFactory.createLineBorder(Color.BLACK)
            bgcolor = Color.WHITE
            font = OreClicker.font.deriveFont(15f)
            preferredSize = Dimension(100, 100)
//            isOpaque = false
            isContentAreaFilled = false
//            isBorderPainted = false
            isFocusCycleRoot = false
            isFocusable = false
            isFocusPainted = false
            addActionListener {
                count++
                OreClicker.score += 1000
                updateText()
            }
//            add(this@ActionButtonNames.label, SwingConstants.CENTER)
        }
    }

    constructor(prefix: String, multiplier: Long, price: Long, color: Color) {
        this.prefix = prefix
        this.multiplier = multiplier
        this.price = price
        this.color = color
        updateText()
        button.apply {
            border = BorderFactory.createLineBorder(Color.BLACK)
            bgcolor = this@ActionButtonNames.color.brighter()
            font = OreClicker.font.deriveFont(15f)
            foreground = Color.WHITE
            hideActionText = true
            isOpaque = true
            isContentAreaFilled = false
//            isBorderPainted = false
            isFocusCycleRoot = false
            isFocusable = false
            isFocusPainted = false
//            size = Dimension(250, 20)
            addActionListener {
                count++
                OreClicker.multiplier += multiplier
                OreClicker.score += additionalMultiplier * count
                OreClicker.score -= price * 1000L
                this@ActionButtonNames.price = (price * (count + 0.4 + multiplier / 100)).toLong()
                updateText()
            }
        }
    }

    fun updateText() {
        if (bigButton) {
            button.text = "$count"
        } else
            button.text = "${name.capitalize()} + ($count) : [${multiplier}] $" +
                    NumberFormat.getNumberInstance(Locale.US).format(price)
    }


    fun update() {
        button.toolTipText = "+${additionalMultiplier * count}"
        button.isEnabled = OreClicker.score >= price * 1000L
        if (button.isEnabled) {
            button.bgcolor.darker().darker()
            button.foreground = Color.BLACK
        } else {
            button.bgcolor.brighter().brighter()
            button.foreground = Color.WHITE
        }
//        button.border = if (btn.isEnabled)
//            BorderFactory.createLineBorder(color, 4)
//        else
//            BorderFactory.createLineBorder(Color.BLACK, 1)
    }
}