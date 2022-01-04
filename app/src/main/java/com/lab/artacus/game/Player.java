package com.lab.artacus.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lab.artacus.R;

import static com.lab.artacus.game.GameView.screenRatioX;
import static com.lab.artacus.game.GameView.screenRatioY;

public class Player {

    int x, y, width, height;
    private float health = 100f, maxhealth = 100f;
    Bitmap player;

    Player(int screenY, Resources res) {
        player = BitmapFactory.decodeResource(res, R.drawable.player_a);

        width = player.getWidth();
        height = player.getHeight();

        width /= 12;
        height /= 12;

        width *= (int) screenRatioX;
        height *= (int) screenRatioY;

        player = Bitmap.createScaledBitmap(player, width, height, false);

        y = screenY/2;
        x = (int) (64*screenRatioX);
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
