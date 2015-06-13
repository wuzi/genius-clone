package com.geniusclone.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameAssetLoader {
    public static Texture squareTexture, squareTextureGlow, circleTexture, backgroundTexture, gameoverTexture, restartButtonTexture, backButtonTexture;
    public static TextureRegion greenSquare, redSquare, yellowSquare, blueSquare;
    public static TextureRegion greenSquareGlow, redSquareGlow, yellowSquareGlow, blueSquareGlow;
    public static TextureRegion restartButtonReleased, restartButtonPressed, backButtonReleased, backButtonPressed;
    public static Sound redsound, bluesound, yellowsound, greensound, errorSound, gameoverSound;
    public static BitmapFont font;

    public static void load() {
        // gameplay background
        backgroundTexture = new Texture("images/gameplay/background.png");
        backgroundTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // gameover texture
        gameoverTexture = new Texture("images/gameplay/greyscreen.png");
        gameoverTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // gameplay textures
        circleTexture = new Texture("images/gameplay/circle.png");
        circleTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        squareTexture = new Texture("images/gameplay/squaretextures.png");
        squareTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        greenSquare = new TextureRegion(squareTexture, 0, 0, 160, 160);
        redSquare = new TextureRegion(squareTexture, 161, 0, 160, 160);
        yellowSquare = new TextureRegion(squareTexture, 322, 0, 160, 160);
        blueSquare = new TextureRegion(squareTexture, 483, 0, 160, 160);

        squareTextureGlow = new Texture("images/gameplay/squaretexturesglow.png");
        squareTextureGlow.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        greenSquareGlow = new TextureRegion(squareTextureGlow, 0, 0, 160, 160);
        redSquareGlow = new TextureRegion(squareTextureGlow, 161, 0, 160, 160);
        yellowSquareGlow = new TextureRegion(squareTextureGlow, 322, 0, 160, 160);
        blueSquareGlow = new TextureRegion(squareTextureGlow, 483, 0, 160, 160);

        //buttons
        restartButtonTexture = new Texture("images/gameplay/buttons/restart.png");
        restartButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        restartButtonReleased = new TextureRegion(restartButtonTexture, 0, 0, 120, 131);
        restartButtonPressed = new TextureRegion(restartButtonTexture, 0, 133, 120, 131);

        backButtonTexture = new Texture("images/gameplay/buttons/back.png");
        backButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        backButtonReleased = new TextureRegion(backButtonTexture, 0, 0, 120, 131);
        backButtonPressed = new TextureRegion(backButtonTexture, 0, 133, 120, 131);

        // sounds
        redsound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/redsound.mp3"));
        bluesound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/bluesound.mp3"));
        greensound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/greensound.mp3"));
        yellowsound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/yellowsound.mp3"));
        errorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/errorsound.mp3"));
        gameoverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/gameplay/gameoversound.mp3"));

        //fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/dimbo.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 18;
        parameter.color = Color.WHITE;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 2;

        font = generator.generateFont(parameter);
        generator.dispose();

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.

        // textures
        squareTexture.dispose();
        squareTextureGlow.dispose();
        circleTexture.dispose();
        backgroundTexture.dispose();
        gameoverTexture.dispose();
        restartButtonTexture.dispose();
        backButtonTexture.dispose();

        // sounds
        redsound.dispose();
        bluesound.dispose();
        greensound.dispose();
        yellowsound.dispose();
        errorSound.dispose();
        gameoverSound.dispose();

        // fonts
        font.dispose();
    }

}