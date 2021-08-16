package main.me.jikud

import kotlin.system.exitProcess

object Loop {
    var running = false
    fun loop(init: Runnable, update: Runnable, ups: Runnable) {
        init.run()
        var last = System.currentTimeMillis()
        var last_s = System.currentTimeMillis()
        while (running) {
            val now = System.currentTimeMillis()
            val now_s = System.currentTimeMillis()
            if (now - last >= 1) {
                update.run()
                last = now
            }
            if (now_s - last_s >= 1000) {
                ups.run()
                last_s = now_s
            }
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