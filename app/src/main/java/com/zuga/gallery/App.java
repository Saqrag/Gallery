package com.zuga.gallery;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.zuga.imagepicker.ImagePicker;
import com.zuga.imagepicker.PickerConfig;
import com.zuga.keyboard.helpers.KeyboardManager;
import com.zuga.video.record.SmallVideoDefaultParams;

/**
 * @author saqrag
 * @version 1.0
 * @see null
 * 2017-11-01
 * @since 1.0
 **/

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KeyboardManager.init(this);
        SmallVideoDefaultParams.init(this);
        ImagePicker.init(new PickerConfig.Builder().setAppContext(getApplicationContext())
                .setToolbaseColor(ContextCompat.getColor(getApplicationContext(), R.color.title_bar_background)).build());
    }
}
