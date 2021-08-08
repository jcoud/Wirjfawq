package main.me.jikud

import kotlin.system.exitProcess

object Loop {
    var running = false
    fun loop(init: Runnable, update: Runnable, render: Runnable) {
        init.run()
        while (running) {
            update.run()
            render.run()
//            Thread.sleep((1 / 60) * 1000)

        }
        exitProcess(0)
    }

    fun start() {
        running = true
    }

    fun stop() {
        running = false
    }
}