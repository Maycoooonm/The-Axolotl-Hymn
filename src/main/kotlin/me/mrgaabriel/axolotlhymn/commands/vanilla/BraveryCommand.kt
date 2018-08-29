package me.mrgaabriel.axolotlhymn.commands.vanilla

import me.mrgaabriel.axolotlhymn.commands.*
import me.mrgaabriel.axolotlhymn.utils.*
import net.dv8tion.jda.core.entities.*
import java.awt.*
import java.io.*
import java.net.*
import javax.imageio.*

class BraveryCommand : AbstractCommand(
        "bravery",
        "Bravery Team"
) {

    override fun run(message: Message, args: Array<String>) {
        val user = if (message.mentionedUsers.isNotEmpty()) message.mentionedUsers.get(0) else message.author

        val userAvatar = ImageIO.read(URL(user.effectiveAvatarUrl + "?size=512")).makeRoundedCorner(600)
                .resize(1024, 1024, true)

        val graphics = userAvatar.createGraphics()

        val brillianceOverlay = ImageIO.read(URL("https://cdn.discordapp.com/attachments/445378804685209601/483434290651332608/Modelo-Bravery1.png"))
                .resize(1024, 1024, true)

        graphics.font = Font.createFont(0, File("assets", "whitneyhtf-bold.otf")).deriveFont(16f)
        graphics.color = Color(255, 255, 255)
        graphics.drawString("Feito em Axolotl Blobs Hub", 10, 20)
        graphics.drawString("https://discord.gg/USNQSt5", 10, 40)

        graphics.drawImage(brillianceOverlay, 0, 0, null)

        val baos = ByteArrayOutputStream()
        baos.use {
            ImageIO.write(userAvatar.resize(512, 512, true), "png", it)
        }

        val inputStream = ByteArrayInputStream(baos.toByteArray())

        message.channel.sendFile(inputStream, "bravery.png").queue()
    }
}