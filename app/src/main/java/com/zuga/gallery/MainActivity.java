package com.zuga.gallery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zuga.imagepicker.ImagePicker;
import com.zuga.imagepicker.activity.ImagePickerActivity;
import com.zuga.imagepicker.model.Content;
import com.zuga.imagepicker.util.SystemUtil;
import com.zuga.imagepicker.widget.EnableView;
import com.zuga.imagepicker.widget.StrokeImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 2;
    private StrokeImageView ivTest;
    private int maxCount, maxVideoCount, maxImageCount, maxGifCount;
    private int videoMaxDuration, videoMinDuration;
    private int cropX;
    private int cropY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bn_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshValue();
                ImagePicker.from(MainActivity.this)
                        .maxCount(maxCount)
                        .maxVideoCount(maxVideoCount)
                        .maxImageCount(maxImageCount)
                        .maxGifCount(maxGifCount)
                        .setVideoMaxDuration(videoMaxDuration)
                        .setVideoMinDuration(videoMinDuration)
                        .includeImage(((CheckBox) findViewById(R.id.include_image)).isChecked())
                        .includeGif(((CheckBox) findViewById(R.id.include_gif)).isChecked())
                        .includeVideo(((CheckBox) findViewById(R.id.include_video)).isChecked())
                        .crop(((CheckBox) findViewById(R.id.crop)).isChecked())
                        .setCropAspectX(cropX)
                        .setCropAspectY(cropY)
                        .allowOriginal(((CheckBox) findViewById(R.id.allow_original)).isChecked())
                        .allowEditVideo(((CheckBox) findViewById(R.id.allow_edit)).isChecked())
                        .onlySelectSingleType(((CheckBox) findViewById(R.id.only_select_single_type)).isChecked())
                        .forResult(REQUEST_CODE);
            }
        });

        ivTest = findViewById(R.id.iv_test);
        ivTest.setStrokeColor(Color.BLACK, SystemUtil.dp(2));
        ivTest.setSelected(true);
        Glide.with(ivTest).load("/storage/emulated/0/DCIM/Camera/IMG_20171005_112200.jpg").into(ivTest);


        EnableView view = findViewById(R.id.ev_test);
        view.setDisable(R.drawable.gallery_action_send_enable);
        view.setEnable(R.drawable.gallery_action_submit);
        view.setEnabled(false);
    }

    private void refreshValue() {
        Editable max_count = ((EditText) findViewById(R.id.max_count)).getText();
        Editable max_video_count = ((EditText) findViewById(R.id.max_video_count)).getText();
        Editable max_image_count = ((EditText) findViewById(R.id.max_image_count)).getText();
        Editable max_gif_count = ((EditText) findViewById(R.id.max_gif_count)).getText();
        Editable video_max_duration = ((EditText) findViewById(R.id.video_max_duration)).getText();
        Editable video_min_duration = ((EditText) findViewById(R.id.video_min_duration)).getText();
        Editable crop_x = ((EditText) findViewById(R.id.crop_x)).getText();
        Editable crop_y = ((EditText) findViewById(R.id.crop_y)).getText();
        maxCount = TextUtils.isEmpty(max_count) ? 1 : Integer.valueOf(String.valueOf(max_count));
        maxVideoCount = TextUtils.isEmpty(max_video_count) ? 1 : Integer.valueOf(String.valueOf(max_video_count));
        maxImageCount = TextUtils.isEmpty(max_image_count) ? 1 : Integer.valueOf(String.valueOf(max_image_count));
        maxGifCount = TextUtils.isEmpty(max_gif_count) ? 1 : Integer.valueOf(String.valueOf(max_gif_count));
        videoMaxDuration = TextUtils.isEmpty(video_max_duration) ? 15000 : Integer.valueOf(String.valueOf(video_max_duration));
        videoMinDuration = TextUtils.isEmpty(video_min_duration) ? 3000 : Integer.valueOf(String.valueOf(video_min_duration));
        cropX = TextUtils.isEmpty(crop_x) ? 0 : Integer.valueOf(String.valueOf(crop_x));
        cropY = TextUtils.isEmpty(crop_y) ? 0 : Integer.valueOf(String.valueOf(crop_y));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        switch (requestCode) {
            case REQUEST_CODE:
                handleGalleyResult(data);
                break;
        }
    }

    private void handleGalleyResult(Intent data) {
        ArrayList<Content> photos = data.getParcelableArrayListExtra(ImagePickerActivity.EXTRA_RESULT_SELECTION);
        if (photos == null) return;
        Toast.makeText(this, photos.toString(), Toast.LENGTH_LONG).show();
        Glide.with(ivTest).load(photos.get(0).getPath()).into(ivTest);
    }


}
