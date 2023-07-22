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
            row.append("     ")

            for (w in 0 until width) {
                val idx = offset + width * h + w
                val value = data[idx].toInt()

                row.append("█").color(ChatColor.of(Color(value, value, value)))
            }

            if (h + 1 < height) row.append("\n")
        }

        return row.create()
    }
}
