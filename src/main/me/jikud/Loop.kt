package main.me.jikud

import kotlin.system.exitProcess

object Loop {
    var running = false
    fun loop(init: Runnable, update: Runnable) {
        init.run()
        var last = System.currentTimeMillis()
        while (running) {
            val now = System.currentTimeMillis()
            if (now - last >= 1) {
                update.run()
            }
            last = now
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