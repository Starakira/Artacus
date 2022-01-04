package com.lab.artacus.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lab.artacus.R;

import java.util.ArrayList;

import static com.lab.artacus.game.GameView.screenRatioX;
import static com.lab.artacus.game.GameView.screenRatioY;

public class Question {
    int x, y, width, height;
    ArrayList<String[]> list = new ArrayList<String[]>();
    Bitmap question;

    Question(int screenX, int screenY, Resources res){
        question = BitmapFactory.decodeResource(res, R.drawable.question_bubble_game);

        width = question.getWidth();
        height = question.getHeight();

        width /= 4;
        height /= 4;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        question = Bitmap.createScaledBitmap(question, width, height, false);
        y = (screenY/2)-192;
        x = (int) (screenX-(840*screenRatioX));
    }
}
