/**
 *  Copyright (c) 2014, Runix-Minecraft
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *  CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package us.illyohs.azathoth.internal.helper;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import us.illyohs.azathoth.internal.Reference;

public class ChatHelper {

    private static final IChatComponent AZT = createSckChatComponent(Reference.MOD_ID.toLowerCase());

    public static void iCommandSenderReply(ICommandSender player, String message) {
        sendChatToPlayer((EntityPlayer)player, message);
    }

    private static IChatComponent createSckChatComponent(String string) {
        ChatComponentText Component = new ChatComponentText(string);
        return Component;
    }

    public static IChatComponent createChatComponent(String message) {
        ChatComponentText component = new ChatComponentText(message);
        return AZT.appendSibling(component);
    }

    public static void sendChatToPlayer(EntityPlayer player, String message) {
        player.addChatComponentMessage(createChatComponent(message));
    }

    public static void broadcastMessageToPlayers(String message){
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg(createChatComponent(message));
    }
}
