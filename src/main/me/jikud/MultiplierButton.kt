package main.me.jikud

import java.awt.Dimension
import javax.swing.JButton
import javax.swing.JPanel

class MultiplierButton(val mn: MultiplierNames) : JPanel() {
    private val btn = JButton()
    init {
        this.add(btn.apply {
            preferredSize = Dimension(30, 30)
            text = name
            addActionListener {
                when (mn) {
                    MultiplierNames.TWICE_1 -> {}
                    MultiplierNames.TWICE_2 -> {}
                    MultiplierNames.TWICE_3 -> {}
                    MultiplierNames.TWICE_4 -> {}
                    MultiplierNames.TWICE_X -> {}
                    else -> return@addActionListener
                }
            }
        })
    }

    fun update() {
        
    }
}