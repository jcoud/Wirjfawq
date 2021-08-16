package main.me.jikud

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import javax.swing.*

class OreClicker : Runnable {

    private var cpls = arrayListOf<CoopButton>()

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
        fun sf(number: Long) = NumberFormat.getNumberInstance(Locale.US).format(number)
    }

    lateinit var frame: JFrame

    private fun init() {
        frame = JFrame("OreClicker | Score: ${sf(score)}")
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
        val cookieButton = ActionButtonNames.BIG_BUTTON.button

        multiplierButtonPanel.layout = BoxLayout(multiplierButtonPanel, BoxLayout.Y_AXIS)
        actionButtonPanel.layout = BoxLayout(actionButtonPanel, BoxLayout.Y_AXIS)

        multiplierButtonPanel.border = BorderFactory.createLineBorder(Color.BLACK)
        actionButtonPanel.border = BorderFactory.createLineBorder(Color.BLACK)

        //todo: добавить на каждую кнопку по несколько умножителей (по количеству из MultiplierNames)
        MultiplierNames.list().forEach { ml ->
            val jp = JPanel()
            jp.layout = BoxLayout(jp, BoxLayout.X_AXIS)
            ActionButtonNames.list().forEach {
                val cp = CoopButton(it, ml)
                cpls += cp
                jp.add(cp)
            }
            multiplierButtonPanel.add(jp)
        }
        ActionButtonNames.list().forEach {
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
        con.weightx = 1.0
//        con.anchor = GridBagConstraints.CENTER
        con.gridwidth = 2
//        con.ipadx = 50
        con.insets = Insets(10, 10, 10, 10)
        con.gridy = 2
        layout.setConstraints(cookieButton, con)
        panel.add(cookieButton)

//        con.gridx = 2
//        con.anchor = GridBagConstraints.EAST
        con.gridwidth = 1
        con.weightx = 1.0
        layout.setConstraints(actionButtonPanel, con)
        panel.add(actionButtonPanel)
        return panel
    }

    private fun update() {
        score += multiplier
        scorePanel.text = "${sf(score)} + $multiplier"
        ActionButtonNames.list().forEach(ActionButtonNames::update)
        cpls.forEach(CoopButton::update)
    }

    private fun ups() {
        frame.title = "OreClicker | Score: ${sf(score)}"
    }

    override fun run() {
        Loop.loop(this::init, this::update, this::ups)
    }
}