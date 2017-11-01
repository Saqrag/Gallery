package com.zuga.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zuga.imagepicker.ImagePicker;
import com.zuga.imagepicker.activity.PhotoPickerActivity;
import com.zuga.imagepicker.model.Content;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bn_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.from(MainActivity.this)
                        .maxCount(9)
                        .includeImage(true)
                        .includeGif(true)
                        .includeVideo(true)
                        .crop(true)
                        .forResult(REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<Content> photos = data.getParcelableArrayListExtra(PhotoPickerActivity.EXTRA_RESULT_SELECTION);
    }
}
