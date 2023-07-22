package com.mygdx.spacerocksparticlesch15;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreen extends BaseScreen {
    private Spaceship spaceship;

    private boolean gameOver;

    public void initialize() {
        BaseActor space = new BaseActor(0, 0, mainStage);
        space.loadTexture("space.png");
        space.setSize(800, 600);
        BaseActor.setWorldBounds(space);

        spaceship = new Spaceship(400, 300, mainStage);

        new Rock(600, 500, mainStage);
        new Rock(600, 300, mainStage);
        new Rock(600, 100, mainStage);
        new Rock(400, 100, mainStage);
        new Rock(200, 100, mainStage);
        new Rock(200, 300, mainStage);
        new Rock(200, 500, mainStage);
        new Rock(400, 500, mainStage);

        gameOver = false;
    }

    public void update(float dt) {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "com.mygdx.spacerocksparticlesch15.Rock")) {
            if (rockActor.overlaps(spaceship)) {
                if (spaceship.shieldPower <= 0) {
//                    Explosion boom = new Explosion(0, 0, mainStage);
//                    boom.centerAtActor(spaceship);
                    ExplosionEffect boom = new ExplosionEffect();
                    boom.centerAtActor(spaceship);
                    boom.start();
                    mainStage.addActor(boom);
                    spaceship.remove();
                    spaceship.setPosition(-1000, -1000);

                    BaseActor messageLose = new BaseActor(0, 0, uiStage);
                    messageLose.loadTexture("message-lose.png");
                    messageLose.centerAtPosition(400, 300);
                    messageLose.setOpacity(0);
                    messageLose.addAction(Actions.fadeIn(1));
                    gameOver = true;
                } else {
                    spaceship.shieldPower -= 34;
//                    Explosion boom = new Explosion(0, 0, mainStage);
//                    boom.centerAtActor(rockActor);
                    ExplosionEffect boom = new ExplosionEffect();
                    boom.centerAtActor(rockActor);
                    boom.start();
                    mainStage.addActor(boom);
                    rockActor.remove();
                }
            }

            for ( BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.spacerocksparticlesch15.Laser") )
            {
                if (laserActor.overlaps(rockActor))
                {
//                    Explosion boom = new Explosion(0,0, mainStage);
//                    boom.centerAtActor(rockActor);
                    ExplosionEffect boom = new ExplosionEffect();
                    boom.centerAtActor(rockActor);
                    boom.start();
                    mainStage.addActor(boom);
                    laserActor.remove();
                    rockActor.remove();
                }
            }
        }

        if ( !gameOver && BaseActor.getList(mainStage, "com.mygdx.spacerocksparticlesch15.Rock").size() == 0 )
        {
            BaseActor messageWin = new BaseActor(0,0, uiStage);
            messageWin.loadTexture("assets/message-win.png");
            messageWin.centerAtPosition(400,300);
            messageWin.setOpacity(0);
            messageWin.addAction( Actions.fadeIn(1) );
            gameOver = true;
        }
    }

    // override default InputProcessor method

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.X)
            spaceship.warp();

        if (keycode == Keys.SPACE)
            spaceship.shoot();

        return false;
    }
}
