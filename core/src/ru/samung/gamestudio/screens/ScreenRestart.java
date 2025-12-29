package ru.samung.gamestudio.screens;

import static ru.samung.gamestudio.MyGdxGame.SCR_HEIGHT;
import static ru.samung.gamestudio.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.samung.gamestudio.components.MovingBackground;
import ru.samung.gamestudio.MyGdxGame;
import ru.samung.gamestudio.components.PointCounter;
import ru.samung.gamestudio.components.TextButton;

public class ScreenRestart implements Screen {
    TextButton textButton;
    TextButton textButtonMenu;
    MovingBackground background;
    MyGdxGame myGdxGame;
    PointCounter pointCounter;
    public int getGamePoints() {
        return gamePoints;
    }

    public void setGamePoints(int gamePoints) {
        this.gamePoints = gamePoints;
    }
    final int pointCounterMarginTop = 130;
    final int pointCounterMarginRight = 790;
    private int gamePoints;
    public ScreenRestart(MyGdxGame myGdxGame_new) {
        this.myGdxGame=myGdxGame_new;
        background = new MovingBackground("background/play1.png");
        textButton = new TextButton(150, 200, "Restart");
        textButtonMenu = new TextButton(650, 200, "Menu");
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight,SCR_HEIGHT - pointCounterMarginTop);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (textButton.isHit((int)touch.x, (int)touch.y)){
                myGdxGame.setScreen(myGdxGame.screenGame);
            }
            if (textButtonMenu.isHit((int)touch.x, (int)touch.y)){
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }
        background.move();
        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        textButton.draw(myGdxGame.batch);
        textButtonMenu.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

        myGdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        textButton.dispose();
        textButtonMenu.dispose();
        pointCounter.dispose();
    }
}
