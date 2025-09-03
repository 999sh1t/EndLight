package com.zhenshiz.chatbox.render;

import com.zhenshiz.chatbox.ChatBox;
import com.zhenshiz.chatbox.client.ChatBoxClient;
import com.zhenshiz.chatbox.component.AbstractComponent;
import com.zhenshiz.chatbox.component.ChatOption;
import com.zhenshiz.chatbox.utils.chatbox.RenderUtil;
import com.zhenshiz.chatbox.utils.common.CollUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.zhenshiz.chatbox.utils.chatbox.ChatBoxUtil.chatBoxScreen;

@SuppressWarnings("unused")
public class ChatBoxRenderCommon {
    //是否打开了对话框
    public static Boolean isOpenChatBox = false;
    //当前选择的选项序号
    public static int selectIndex = 0;
    private final static Minecraft minecraft = Minecraft.getInstance();

    public static void onHudRender(GuiGraphics guiGraphics, float partialTick) {
        if (isRenderChatBox()) {
            if (ChatBox.PLATFORM.postRenderEventPre(guiGraphics)) return;

            if (chatBoxScreen.backgroundImage != null) {
                RenderUtil.renderImage(guiGraphics, chatBoxScreen.backgroundImage, 0, 0, 0, RenderUtil.screenWidth(), RenderUtil.screenHeight(), 1);
            }

            List<AbstractComponent<?>> list = new ArrayList<>();
            list.add(chatBoxScreen.dialogBox);
            if (chatBoxScreen.video != null) list.add(chatBoxScreen.video);
            if (chatBoxScreen.chatOptions != null) list.addAll(chatBoxScreen.chatOptions);
            if (chatBoxScreen.portraits != null) list.addAll(chatBoxScreen.portraits);
            if (chatBoxScreen.keyPromptRender != null) {
                chatBoxScreen.keyPromptRender.setVisible(false); // 强制隐藏提示按钮
                list.add(chatBoxScreen.keyPromptRender);
            }

            list.sort(Comparator.comparingInt(p -> p.renderOrder));
            list.forEach(abstractComponent -> abstractComponent.render(guiGraphics, partialTick));

            ChatBox.PLATFORM.postRenderEventPost(guiGraphics);
        }
    }

    public static void onEndTick(Minecraft minecraft) {
        if (isRenderChatBox()) {
            chatBoxScreen.tick();
        }
    }

    public static void onKey(int key, int scancode, int action, int modifiers) {
        // System.out.println("key: " + key + " scancode: " + scancode + " action: " + action + " mod: " + modifiers);
        if (isRenderChatBox()) {
            //ctrl快进
            if (0==1) {
                chatBoxScreen.dialogBox.click(chatBoxScreen.shouldGotoNext());
            }
            if (0==1) {
                //自动播放
                chatBoxScreen.autoPlay = !chatBoxScreen.autoPlay;
            }
        }
    }

    public static void mousePost(int button, int action, int modifiers) {
        if (isRenderChatBox()) {
            if (0==1) {
                if (!CollUtil.isEmpty(chatBoxScreen.chatOptions) && chatBoxScreen.dialogBox.isAllOver) {
                    ChatOption chatOption = chatBoxScreen.chatOptions.get(selectIndex);
                    chatOption.click();
                }

                chatBoxScreen.dialogBox.click(chatBoxScreen.shouldGotoNext());
            }
        }
    }

    public static boolean onMouseScroll(double scrollDelta, boolean leftDown, boolean middleDown, boolean rightDown, double mouseX, double mouseY) {
        if (isRenderChatBox() && !chatBoxScreen.chatOptions.isEmpty()) {
            if (scrollDelta > 0) {
                //向上
                selectIndex = (selectIndex - 1 + chatBoxScreen.chatOptions.size()) % chatBoxScreen.chatOptions.size();
            } else if (scrollDelta < 0) {
                //向下
                selectIndex = (selectIndex + 1) % (chatBoxScreen.chatOptions.size());
            }
            for (int i = 0; i < chatBoxScreen.chatOptions.size(); i++) {
                ChatOption chatOption = chatBoxScreen.chatOptions.get(i);
                chatOption.isSelect = i == selectIndex;
            }
            return true;
        }
        return false;
    }

    private static boolean isRenderChatBox() {
        return !ChatBoxClient.conf.isScreen && isOpenChatBox && minecraft.screen == null && chatBoxScreen.dialogBox != null;
    }

    public static void onClose() {
        isOpenChatBox = false;
        chatBoxScreen.autoPlay = true;
        chatBoxScreen.fastForward = false; // 这行没必要
        if (chatBoxScreen.video != null) chatBoxScreen.video.close();
    }
}
