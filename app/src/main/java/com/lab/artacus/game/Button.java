package com.lab.artacus.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lab.artacus.R;

import static com.lab.artacus.game.GameView.screenRatioX;
import static com.lab.artacus.game.GameView.screenRatioY;

public class Button {
    int width, height;
    Bitmap button;

    Button(Resources res){
        button = BitmapFactory.decodeResource(res, R.drawable.button_game);

        width = button.getWidth();
        height = button.getHeight();

        width /= 4;
        height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        button = Bitmap.createScaledBitmap(button, width, height, false);
    }
}
