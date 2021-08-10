package main.me.jikud

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.swing.*

class CookieClicker : Runnable {

    companion object {
        val font = Font("Consolas", Font.PLAIN, 12)
        var score = 0
        var multiplier = 1
        val scorePanel = JLabel("$score", SwingConstants.CENTER).apply {
            isOpaque = true
            background = Color.RED
            font = CookieClicker.font.deriveFont(20f)
            border = BorderFactory.createLineBorder(Color.BLACK)
            foreground = Color.WHITE
        }
        val jbtns = arrayListOf<ActionButton>()

        @JvmStatic
        fun main(args: Array<String>) {
            Thread(CookieClicker()).start()
            Loop.start()
        }
    }

    private fun init() {
        val frame = JFrame()
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        frame.preferredSize = Dimension(500, 500)
        frame.add(makeMainView())
        frame.defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        frame.isVisible = true
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                Loop.stop()
                frame.dispose()
            }
        })
        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun makeMainView(): JPanel {
        val panel = JPanel()
        val buttonPanel = JPanel()
        buttonPanel.layout = BoxLayout(buttonPanel, BoxLayout.Y_AXIS)
        buttonPanel.border = BorderFactory.createLineBorder(Color.BLACK)
        val cookieButton = ActionButton()
        buttonPanel.add(makeActionButton("Cooper", 1, 10))
        buttonPanel.add(makeActionButton("Lead", 5, 50))
        buttonPanel.add(makeActionButton("Iron", 20, 200))
        buttonPanel.add(makeActionButton("Tungsten", 50, 700))
        buttonPanel.add(makeActionButton("Gold", 100, 1000))
        buttonPanel.add(makeActionButton("Platinum", 300, 5000))
        buttonPanel.add(makeActionButton("Titan", 500, 50000))
        val layout = GridBagLayout()
        val con = GridBagConstraints()
        panel.layout = layout

        con.fill = GridBagConstraints.HORIZONTAL
        con.gridwidth = 3
        con.weightx = 1.0
        layout.setConstraints(scorePanel, con)
        panel.add(scorePanel)

        con.fill = GridBagConstraints.NONE
        con.weightx = .7
        con.anchor = GridBagConstraints.CENTER
        con.gridwidth = 2
//        con.ipadx = 45
        con.gridy = 1
        layout.setConstraints(cookieButton, con)
        panel.add(cookieButton)

//        con.gridx = 2
        con.anchor = GridBagConstraints.EAST
        con.gridwidth = 1
        con.weightx = 1.0
        layout.setConstraints(buttonPanel, con)
        panel.add(buttonPanel)
        return panel
    }

    private fun makeActionButton(name: String, multiplier: Int, price: Int): JButton {
        val button = ActionButton(name, multiplier, price)
        jbtns += button
        return button
    }

    private fun update() {
        updateScore()
//        IOHandler.printEvent()
    }

    private fun updateScore() {
        score += multiplier
        scorePanel.text = "${score.toDouble() / 1000}".format("{0:D3}") + " + $multiplier"
        jbtns.forEach(ActionButton::updateButton)
    }

    private fun render() {
    }

    override fun run() {
        Loop.loop(this::init, this::update, this::render)
    }
}