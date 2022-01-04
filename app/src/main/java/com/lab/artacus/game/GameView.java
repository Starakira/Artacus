package com.lab.artacus.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.lab.artacus.R;
import com.lab.artacus.view.activities.GameActivity;
import com.lab.artacus.view.activities.MainActivity;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;

public class GameView extends SurfaceView implements Runnable {

    private GameActivity activity;
    private Thread thread;
    private boolean isPlaying, isGameOver, isWin, isLose;
    private Background backgroundGame;
    private Player player;
    private Enemy enemy;
    private Question question;
    private Button buttonAnswer;
    private QuestionDummyModel questionDummyModel;
    private ArrayList<QuestionDummyModel> questionDummyModelArrayList;
    public static float screenRatioX, screenRatioY;
    private Paint backgroundPaint, playerSpritePaint, playerHealthTextPaint, enemySpritePaint, enemyHealthTextPaint, questionBubbleSpritePaint, questionBubbleTextPaint, buttonAnswerSpritePaint, buttonAnswerTextPaint, timerTextPaint, gameOverTextPaint;
    private int screenX, screenY, timer, n;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.screenX = screenX;
        this.screenY = screenY;

        screenRatioX = 1280f/screenX;
        screenRatioY = 720f/screenY;

        n = 0;

        questionDummyModelArrayList = new ArrayList<QuestionDummyModel>();

        questionDummyModelArrayList.add(new QuestionDummyModel("Suatu cabang seni yang karyanya hanya dapat dinikmati secara visual adalah ...", new String[]{"Seni musik", "Seni tari", "Seni rupa", "Seni teater"}, "Seni rupa"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Komposisi dalam menggambar dapat dibedakan dalam dua jenis yaitu ...", new String[]{"Simetris dan kesatuan", "Simatris dan keseimbangan", "Simetris dan asimetris", "Asimetris dan kesatuan"}, "Simetris dan asimetris"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Gagasan, ide, atau pokok pikiran yang ada dalam sebuah karya seni merupakan pengertian dari ...", new String[]{"Judul", "Tema", "Karya sastra", "Ilustrasi"}, "Tema"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Komplementer dari warna merah, kuning, dan biru berturut-turut adalah ...", new String[]{"Hijau, ungu, oranye", "Ungu, hijau, oranye", "Hijau, oranye, ungu", "Oranye, ungu, hijau"}, "Seni rupa"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Teknik pewarnaan yang paling tepat digunakan dalam membuat lukisan dengan menggunakan media crayon adalah ….", new String[]{"teknik gosok/dusel", "teknik pointilis", "teknik plakat", "teknik sapuan basah"}, "teknik gosok/dusel"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Melukis tembok dengan bahan cair sehingga hasilnya menyatu dengan arsitektur menggunakan teknik …", new String[]{"spray", "aquarel", "plaket", "tempra"}, "tempra"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Suatu cabang seni yang karyanya hanya dapat dinikmati secara visual adalah ...", new String[]{"Seni musik", "Seni tari", "Seni rupa", "Seni teater"}, "Seni rupa"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Suatu cabang seni yang karyanya hanya dapat dinikmati secara visual adalah ...", new String[]{"Seni musik", "Seni tari", "Seni rupa", "Seni teater"}, "Seni rupa"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Suatu cabang seni yang karyanya hanya dapat dinikmati secara visual adalah ...", new String[]{"Seni musik", "Seni tari", "Seni rupa", "Seni teater"}, "Seni rupa"));
        questionDummyModelArrayList.add(new QuestionDummyModel("Suatu cabang seni yang karyanya hanya dapat dinikmati secara visual adalah ...", new String[]{"Seni musik", "Seni tari", "Seni rupa", "Seni teater"}, "Seni rupa"));

        backgroundGame = new Background(screenX, screenY, getResources());
        player = new Player(screenY, getResources());
        enemy = new Enemy(screenX, screenY, getResources());
        question = new Question(screenX, screenY, getResources());
        buttonAnswer = new Button(getResources());

        backgroundPaint = new Paint();

        playerSpritePaint = new Paint();

        playerHealthTextPaint = new Paint();
        playerHealthTextPaint.setTextSize(96);
        playerHealthTextPaint.setColor(ContextCompat.getColor(activity, R.color.red));

        enemySpritePaint = new Paint();

        enemyHealthTextPaint = new Paint();
        enemyHealthTextPaint.setTextSize(96);
        enemyHealthTextPaint.setColor(ContextCompat.getColor(activity, R.color.red));

        questionBubbleSpritePaint = new Paint();

        questionBubbleTextPaint = new Paint();
        questionBubbleTextPaint.setTextSize(12);
        questionBubbleTextPaint.setColor(ContextCompat.getColor(activity, R.color.black));

        buttonAnswerSpritePaint = new Paint();

        buttonAnswerTextPaint = new Paint();
        buttonAnswerTextPaint.setTextSize(32);
        buttonAnswerTextPaint.setColor(ContextCompat.getColor(activity, R.color.black));

        timer = 20;
        timerTextPaint = new Paint();
        timerTextPaint.setTextSize(64);
        timerTextPaint.setColor(ContextCompat.getColor(activity, R.color.black));

        gameOverTextPaint = new Paint();
        gameOverTextPaint.setTextSize(64);
        timerTextPaint.setColor(ContextCompat.getColor(activity, R.color.black));
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    public void update() {
        timer--;
        if (timer<=0) {
            timer = 20;
            player.setHealth(player.getHealth()-25f);

            if (n<questionDummyModelArrayList.size()){
                n++;
            } else {
                n=0;
            }
        }

        if (player.getHealth() <=0){
            isGameOver = true;
            isWin = true;
        } else if (enemy.getHealth() <=0){
            isGameOver = true;
            isLose = true;
        }
    }

    public void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(backgroundGame.background, (backgroundGame.x)/screenRatioX, (backgroundGame.y)/screenRatioY, backgroundPaint);

            canvas.drawBitmap(player.player, player.x, player.y, playerSpritePaint);
            canvas.drawText((int) player.getHealth() + "/" + (int) player.getMaxhealth(), (int) (64*screenRatioX), 128, playerHealthTextPaint);

            canvas.drawBitmap(enemy.enemy, enemy.x, enemy.y, enemySpritePaint);
            canvas.drawText((int) enemy.getHealth() + "/" + (int) enemy.getMaxhealth(), (int) (screenX-(420*screenRatioX)), 128, enemyHealthTextPaint);

            canvas.drawBitmap(question.question, question.x, question.y, questionBubbleSpritePaint);
            canvas.drawText(questionDummyModelArrayList.get(n).question, (question.x + 64), (question.y + 64), questionBubbleTextPaint);

            canvas.drawBitmap(buttonAnswer.button, (int) (16*screenRatioX), screenY-128, buttonAnswerSpritePaint);
            canvas.drawText(questionDummyModelArrayList.get(n).answersArray[0], (int) (64*screenRatioX), screenY-64, buttonAnswerTextPaint);

            canvas.drawBitmap(buttonAnswer.button, (int) (336*screenRatioX), screenY-128, buttonAnswerSpritePaint);
            canvas.drawText(questionDummyModelArrayList.get(n).answersArray[1], (int) (388*screenRatioX), screenY-64, buttonAnswerTextPaint);

            canvas.drawBitmap(buttonAnswer.button, (int) (656*screenRatioX), screenY-128, buttonAnswerSpritePaint);
            canvas.drawText(questionDummyModelArrayList.get(n).answersArray[2], (int) (708*screenRatioX), screenY-64, buttonAnswerTextPaint);

            canvas.drawBitmap(buttonAnswer.button, (int) (976*screenRatioX), screenY-128, buttonAnswerSpritePaint);
            canvas.drawText(questionDummyModelArrayList.get(n).answersArray[3], (int) (1028*screenRatioX), screenY-64, buttonAnswerTextPaint);

            canvas.drawText(timer + "", screenX / 2f, 96, timerTextPaint);

            if (isGameOver) {
                isPlaying = false;
                canvas.drawText("Game Over", ((int) ((screenX/screenRatioX)/2) - 128), (int) ((screenY/screenRatioY)/2), gameOverTextPaint);
                getHolder().unlockCanvasAndPost(canvas);
                waitBeforeExiting ();
                return;
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            activity.startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() <= (int) (16*screenRatioX)) {
                    timer = 20;
                    player.setHealth(player.getHealth()-25f);

                    if (n<questionDummyModelArrayList.size()){
                        n++;
                    } else {
                        n=0;
                    }
                } else if (event.getX() <= (int) (336*screenRatioX)) {
                    timer = 20;
                    enemy.setHealth(enemy.getHealth()-25f);

                    if (n<questionDummyModelArrayList.size()){
                        n++;
                    } else {
                        n=0;
                    }
                } else if (event.getX() <= (int) (656*screenRatioX)) {
                    timer = 20;
                    enemy.setHealth(enemy.getHealth()-25f);

                    if (n<questionDummyModelArrayList.size()){
                        n++;
                    } else {
                        n=0;
                    }
                } else {
                    timer = 20;
                    player.setHealth(player.getHealth()-25f);

                    if (n<questionDummyModelArrayList.size()){
                        n++;
                    } else {
                        n=0;
                    }
                }

                break;
        }

        return true;
    }

}
