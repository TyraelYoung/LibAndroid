package wang.tyrael.android.share;

import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ShareParser {
    private final Intent intent;
    private final AbstractShareHandler shareHandler;

    public ShareParser(Intent intent, @NonNull AbstractShareHandler shareHandler) {
        this.intent = intent;
        this.shareHandler = shareHandler;
    }

    public boolean checkHasShare() {
        // Get intent, action and MIME type
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(); // Handle text being sent
            } else if (type.startsWith("image/")) {
                handleSendImage(); // Handle single image being sent
            }
            return true;
        } else if (Intent.ACTION_SEND_MULTIPLE.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendMultipleImages(); // Handle multiple images being sent
            }
            return true;
        }
        return false;
    }

    void handleSendText() {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText == null) {
            return;
        }
        shareHandler.handleSendText(sharedText);
    }

    void handleSendImage() {
        Uri imageUri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri == null) {
            return;
        }
        shareHandler.handleSendImage(imageUri);
    }

    void handleSendMultipleImages() {
        ArrayList<Uri> imageUris = intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
        if (imageUris == null) {
            return;
        }
        shareHandler.handleSendMultipleImages(imageUris);
    }
}
