package com.example.administrator.superbase.imManager;

import android.graphics.Bitmap;

import com.example.administrator.superbase.utils.Utils;
import com.example.administrator.superbase.utils.encode.ImageUtils;

import java.io.File;

/**
 * Created by v_chicunxiang on 2018/3/30.
 *
 * @史上最帅无敌创建者 ccx
 * @创建时间 2018/3/30 13:55
 */

public class IMMessage {

    private String mMessage;
    private String mType;

    private IMMessage(String message, String type) {
        mMessage = message;
        mType = type;
    }

    protected String getMessage() {
        return mMessage;
    }

    protected String getType() {
        return mType;
    }

    public IMMessage createTextMessage(String msg, String type) {
        IMMessage sendMessage = null;
        if (type.equals(ChatManager.Type.TEXT)) {
            sendMessage = this.createSendMessage(msg, type);
        }
        return sendMessage;
    }

    private IMMessage createSendMessage(String msg, String type) {
        return new IMMessage(msg, type);
    }

    public IMMessage createImageMessage(Bitmap bitmap, String type) {
        IMMessage sendMessage = null;
        if (type.equals(ChatManager.Type.IMAGE)) {
            String s = ImageUtils.bitmaptoString(bitmap);
            sendMessage = this.createSendMessage(s, type);
        }
        return sendMessage;
    }

    public IMMessage createFileMessage(File file, String type) {
        IMMessage sendMessage = null;
        if (type.equals(ChatManager.Type.FILE)) {
            sendMessage = this.createSendMessage(Utils.readFileToString(file), type);
        }
        return sendMessage;
    }

    public IMMessage createVoiceMessage(File file, String type) {
        IMMessage sendMessage = null;
        if (type.equals(ChatManager.Type.VOICE)) {
            sendMessage = this.createSendMessage(Utils.readFileToString(file), type);
        }
        return sendMessage;
    }

}
