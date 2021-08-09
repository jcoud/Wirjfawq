package main.me.jikud

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.*

class CookieClicker : Runnable {

    companion object {
        val font = Font("Consolas", Font.PLAIN, 12)
        var score = 0.0
        var multiplier = 1.0
        val scorePanel = JLabel("$score", SwingConstants.CENTER).also {
        }.apply {
            isOpaque = true
            background = Color.RED
            font = CookieClicker.font
            border = BorderFactory.createLineBorder(Color.BLACK)
            foreground = Color.WHITE
        }
        val btns = arrayListOf<ActionButton>()

        @JvmStatic
        fun main(args: Array<String>) {
            Thread(CookieClicker()).start()
            Loop.start()
        }
    }

    private fun init() {
        val frame = JFrame()
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        frame.size = Dimension(400, 400)
        frame.add(mainPanel())
        frame.defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        frame.isVisible = true
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent?) {
                Loop.stop()
                frame.dispose()
            }
        })
//        frame.pack()
        frame.setLocationRelativeTo(null)
    }

    private fun mainPanel(): JPanel {
        val panel = JPanel()
        val buttonPanel = JPanel()
        buttonPanel.layout = BoxLayout(buttonPanel, BoxLayout.Y_AXIS)
        buttonPanel.border = BorderFactory.createLineBorder(Color.BLACK)
        val cookieButton = JButton().apply {
            preferredSize = Dimension(100, 100)
            background = Color.BLACK
            isOpaque = false
            isContentAreaFilled = false
            isBorderPainted = false
            isFocusCycleRoot = false
            addActionListener {
                score += 1000
            }
        }
        buttonPanel.add(makeActionButton("+ Click", 1.0, 10.0))
        buttonPanel.add(makeActionButton("+ Delivery", 5.0, 50.0))
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
//        con.anchor = GridBagConstraints.EAST
        con.gridwidth = 1
        con.weightx = 1.0
        layout.setConstraints(buttonPanel, con)
        panel.add(buttonPanel)
        return panel
    }

    private fun makeActionButton(name: String, multiplier: Double, price: Double): JButton {
        val button = ActionButton(name, multiplier, price)
        btns += button
        return button
    }

    private var last = System.currentTimeMillis()
    private fun update() {
        val now = System.currentTimeMillis()
        if (now - last >= 1) {
            updateScore()
            last = now
        }
    }

    private fun updateScore() {
        score += multiplier
        scorePanel.text = "${score / 1000} + $multiplier".format("%0.3f")
        btns.forEach(ActionButton::updateButton)
    }

    private fun render() {
    }

    override fun run() {
        Loop.loop(this::init, this::update, this::render)
    }
}