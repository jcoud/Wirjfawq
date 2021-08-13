package main.me.jikud

import java.awt.Color

enum class ActionButtonNames(val multiplier: Long, var price: Long, val color: Color) {
    NONE(0, 0, Color.BLACK),
    COOPER(1, 10, Color.decode("0xAE6B35")),
    LEAD(5, 50, Color.decode("0x504C58")),
    IRON(20, 200, Color.decode("0x7A7679")),
    TUNGSTEN(50, 700, Color.decode("0x5F6E5A")),
    GOLD(100, 1000, Color.decode("0xBBAC2C")),
    PLATINUM(300, 5000, Color.decode("0x96B2BB")),
    TITAN(500, 50000, Color.decode("0x494745"))
}