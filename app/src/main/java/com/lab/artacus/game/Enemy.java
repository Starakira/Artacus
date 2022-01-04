package com.lab.artacus.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lab.artacus.R;

import static com.lab.artacus.game.GameView.screenRatioX;
import static com.lab.artacus.game.GameView.screenRatioY;

public class Enemy {
    int x, y, width, height;
    private float health = 100f, maxhealth = 100f;
    Bitmap enemy;

    Enemy(int screenX, int screenY, Resources res) {
        enemy = BitmapFactory.decodeResource(res, R.drawable.dragon_a);

        width = enemy.getWidth();
        height = enemy.getHeight();

        width /= 6;
        height /= 6;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        enemy = Bitmap.createScaledBitmap(enemy, width, height, false);

        y = (screenY/2)-192;
        x = (int) (screenX-(356*screenRatioX));
    }

    void setHealth(float health){
        this.health = health;
    }

    float getHealth(){
        return health;
    }

    float getMaxhealth(){
        return maxhealth;
    }
}
