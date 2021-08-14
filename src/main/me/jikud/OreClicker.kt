package main.me.jikud

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.text.DecimalFormat
import javax.swing.*

class OreClicker : Runnable {

    companion object {
        val font = Font("Consolas", Font.PLAIN, 12)
        var score = 0L
        var multiplier = 1L
        val scorePanel = JLabel("$score", SwingConstants.CENTER).apply {
            isOpaque = true
            background = Color.RED
            font = OreClicker.font.deriveFont(20f)
            border = BorderFactory.createLineBorder(Color.BLACK)
            foreground = Color.WHITE
        }
//        val actionButtons = arrayListOf<ActionButton>()
//        val multiplierButtons = arrayListOf<MultiplierButton>()

        @JvmStatic
        fun main(args: Array<String>) {
            Thread(OreClicker()).start()
            Loop.start()
        }

        @JvmStatic
        fun sf(number: Long) = DecimalFormat("#,##0,000").format(number).replace(",", "'")
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
        val actionButtonPanel = JPanel()
        val multiplierButtonPanel = JPanel()
        actionButtonPanel.layout = BoxLayout(actionButtonPanel, BoxLayout.Y_AXIS)
        actionButtonPanel.border = BorderFactory.createLineBorder(Color.BLACK)
        val cookieButton = ActionButtonNames.BIG_BUTTON
        MultiplierNames.values().forEach { ml ->
            ml.text_ = "x2C"
            //todo: добавить на каждую кнопку по несколько умножителей (по количеству из MultiplierNames)
            ActionButtonNames.list().forEach {
                multiplierButtonPanel.add(it.button)
            }
        }
        ActionButtonNames.values().forEach {
            actionButtonPanel.add(it.button)
        }
        val layout = GridBagLayout()
        val con = GridBagConstraints()
        panel.layout = layout

        con.fill = GridBagConstraints.HORIZONTAL
        con.gridwidth = 3
        layout.setConstraints(multiplierButtonPanel, con)
        panel.add(multiplierButtonPanel)

        con.weightx = 1.0
        con.gridy = 1
        layout.setConstraints(scorePanel, con)
        panel.add(scorePanel)

        con.fill = GridBagConstraints.NONE
        con.weightx = .7
        con.anchor = GridBagConstraints.CENTER
        con.gridwidth = 2
//        con.ipadx = 45
        con.gridy = 2
        layout.setConstraints(cookieButton.button, con)
        panel.add(cookieButton.button)

//        con.gridx = 2
        con.anchor = GridBagConstraints.EAST
        con.gridwidth = 1
        con.weightx = 1.0
        layout.setConstraints(actionButtonPanel, con)
        panel.add(actionButtonPanel)
        return panel
    }

    private fun update() {
        score += multiplier
        scorePanel.text = sf(score) + " + $multiplier"
        ActionButtonNames.list().forEach(ActionButtonNames::update)
        MultiplierNames.list().forEach(MultiplierNames::update)
    }

    override fun run() {
        Loop.loop(this::init, this::update)
    }
}