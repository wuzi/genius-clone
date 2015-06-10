package com.geniusclone.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture playButtonTexture, exitButtonTexture, squareTexture, squareTextureGlow, circleTexture, menuBgTexture, gameplayBgTexture, optionButtonTexture, logo;
    public static TextureRegion greenSquare, redSquare, yellowSquare, blueSquare;
    public static TextureRegion greenSquareGlow, redSquareGlow, yellowSquareGlow, blueSquareGlow;
    public static TextureRegion playButtonReleased, playButtonPressed, exitButtonReleased, exitButtonPressed, optionButtonPressed, optionButtonReleased;
    public static Sound soundTrack, muspellGames, redsound, bluesound, yellowsound, greensound, errorSound;
    public static BitmapFont font;

    public static void load() {
        // playButtons
        playButtonTexture = new Texture("buttons/play-buttons.png");
        playButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonReleased = new TextureRegion(playButtonTexture, 0, 0, 139, 40);
        playButtonPressed = new TextureRegion(playButtonTexture, 0, 80, 139, 40);

        // exitButtons
        exitButtonTexture = new Texture("buttons/exit-buttons.png");
        exitButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        exitButtonReleased = new TextureRegion(exitButtonTexture, 0, 0, 139, 40);
        exitButtonPressed = new TextureRegion(exitButtonTexture, 0, 80, 139, 40);

        // optionButtons
        optionButtonTexture = new Texture("buttons/option-buttons.png");
        optionButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        optionButtonReleased = new TextureRegion(optionButtonTexture, 0, 0, 139, 40);
        optionButtonPressed = new TextureRegion(optionButtonTexture, 0, 80, 139, 40);

        // menuBackground
        menuBgTexture = new Texture("background.png");
        menuBgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // Gameplay Background
        gameplayBgTexture = new Texture("gameplay/background.png");
        gameplayBgTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // gameplayTextures
        circleTexture = new Texture("gameplay/circle.png");
        circleTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // logo
        logo = new Texture("logo.png");
        logo.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        squareTexture = new Texture("gameplay/squaretextures.png");
        squareTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        greenSquare = new TextureRegion(squareTexture, 0, 0, 160, 160);
        redSquare = new TextureRegion(squareTexture, 161, 0, 160, 160);
        yellowSquare = new TextureRegion(squareTexture, 322, 0, 160, 160);
        blueSquare = new TextureRegion(squareTexture, 483, 0, 160, 160);

        squareTextureGlow = new Texture("gameplay/squaretexturesglow.png");
        squareTextureGlow.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        greenSquareGlow = new TextureRegion(squareTextureGlow, 0, 0, 160, 160);
        redSquareGlow = new TextureRegion(squareTextureGlow, 161, 0, 160, 160);
        yellowSquareGlow = new TextureRegion(squareTextureGlow, 322, 0, 160, 160);
        blueSquareGlow = new TextureRegion(squareTextureGlow, 483, 0, 160, 160);

        // sounds
        soundTrack = Gdx.audio.newSound(Gdx.files.internal("songs/soundtrack.mp3"));
        muspellGames = Gdx.audio.newSound(Gdx.files.internal("songs/muspellgames.mp3"));

        redsound = Gdx.audio.newSound(Gdx.files.internal("sounds/redsound.mp3"));
        bluesound = Gdx.audio.newSound(Gdx.files.internal("sounds/bluesound.mp3"));
        greensound = Gdx.audio.newSound(Gdx.files.internal("sounds/greensound.mp3"));
        yellowsound = Gdx.audio.newSound(Gdx.files.internal("sounds/yellowsound.mp3"));
        errorSound = Gdx.audio.newSound(Gdx.files.internal("sounds/errorsound.mp3"));

        //fonts
        font = new BitmapFont(Gdx.files.internal("fonts/text.fnt"));
        font.getData().setScale(.25f, .25f);

    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
        squareTexture.dispose();
        squareTextureGlow.dispose();
        circleTexture.dispose();
        menuBgTexture.dispose();
        gameplayBgTexture.dispose();
        optionButtonTexture.dispose();
        logo.dispose();
        soundTrack.dispose();
        muspellGames.dispose();
    }

}