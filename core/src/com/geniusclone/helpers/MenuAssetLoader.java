package com.geniusclone.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuAssetLoader {
    public static Texture playButtonTexture, exitButtonTexture, optionButtonTexture, logoTexture, backgroundTexture;
    public static TextureRegion playButtonReleased, playButtonPressed, exitButtonReleased, exitButtonPressed, optionButtonPressed, optionButtonReleased;
    public static Music menuMusic;

    public static void load() {
        // playButtons
        playButtonTexture = new Texture("images/menu/buttons/play-buttons.png");
        playButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonReleased = new TextureRegion(playButtonTexture, 0, 0, 139, 40);
        playButtonPressed = new TextureRegion(playButtonTexture, 0, 80, 139, 40);

        // exitButtons
        exitButtonTexture = new Texture("images/menu/buttons/exit-buttons.png");
        exitButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        exitButtonReleased = new TextureRegion(exitButtonTexture, 0, 0, 139, 40);
        exitButtonPressed = new TextureRegion(exitButtonTexture, 0, 80, 139, 40);

        // optionButtons
        optionButtonTexture = new Texture("images/menu/buttons/option-buttons.png");
        optionButtonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        optionButtonReleased = new TextureRegion(optionButtonTexture, 0, 0, 139, 40);
        optionButtonPressed = new TextureRegion(optionButtonTexture, 0, 80, 139, 40);

        // menuBackground
        backgroundTexture = new Texture("images/menu/background.jpg");
        backgroundTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // logo
        logoTexture = new Texture("images/menu/logo.png");
        logoTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        // music
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/menumusic.mp3"));
    }

    public static void dispose() {
        // We must dispose of the texture when we are finished.
        playButtonTexture.dispose();
        exitButtonTexture.dispose();
        optionButtonTexture.dispose();
        logoTexture.dispose();
        backgroundTexture.dispose();
        menuMusic.dispose();
    }

}