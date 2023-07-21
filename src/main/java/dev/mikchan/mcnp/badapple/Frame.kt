@file:OptIn(ExperimentalUnsignedTypes::class)

package dev.mikchan.mcnp.badapple

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import java.awt.Color

internal class Frame private constructor(
    private val data: UByteArray,
    private val frame: Int,
) {
    companion object {
        private const val height = 20
        private const val width = 27

        fun createFrame(data: UByteArray?, frame: Int): Frame? {
            if (data == null) return null
            if (data.size <= (frame + 1) * width * height) return null
            return Frame(data, frame)
        }
    }

    private val offset: Int by lazy { frame * width * height }

    fun getComponents(): Array<BaseComponent> {
        val row = ComponentBuilder()

        for (h in 0 until height) {
            for (w in 0 until width) {
                val idx = offset + width * h + w
                val value = data[idx].toInt()

                val col: ChatColor = if (value < 10) {
                    ChatColor.BLACK
                } else if (value > 250) {
                    ChatColor.WHITE
                } else {
                    ChatColor.of(Color(value, value, value))
                }

                row.append("█").color(col)
            }

            row.append("\n")
        }

        return row.create()
    }
}
