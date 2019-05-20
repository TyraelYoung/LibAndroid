package wang.tyrael.android.share;

import android.net.Uri;

import java.util.List;

public abstract class AbstractShareHandler {
    protected void handleSendText(String sharedText) {

    }

    protected void handleSendImage(Uri imageUri) {

    }

    protected void handleSendMultipleImages(List<Uri> imageUris) {

    }
}
