package com.cubecrusher.trancej;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TrJr extends Game {
	private int scrW, scrH;
	public static final TrJr INSTANCE = new TrJr();
	private OrthographicCamera camera;
	public AssetManager manager;
	public BitmapFont font, font2, font3, fontBig, fontCyan, fontCyan2, fontCyanBig;

	TrJr(){
	}

	public void create () {
		System.out.println("INFO: Trance.create() called");
		manager = new AssetManager();
		this.font = new BitmapFont();
		this.font2 = new BitmapFont();
		this.font3 = new BitmapFont();
		this.fontBig = new BitmapFont();
		this.fontCyanBig = new BitmapFont();
		Assets.load();
		this.scrW = Gdx.graphics.getWidth();
		this.scrH = Gdx.graphics.getHeight();
		makeFont();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, scrW, scrH);
		setScreen(new MainScreen(camera));
	}

	public void makeFont(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/gui.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterBig = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyan = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyan2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyanBig = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameter.size = 64;
		parameter.shadowOffsetX = 5;
		parameter.shadowOffsetY = 5;
		parameter.color = Color.WHITE;
		parameter.shadowColor = Color.BLACK;
		font = generator.generateFont(parameter);

		parameter2.size = 32;
		parameter2.shadowOffsetX = 3;
		parameter2.shadowOffsetY = 3;
		parameter2.color = Color.WHITE;
		parameter2.shadowColor = Color.BLACK;
		font2 = generator.generateFont(parameter2);

		parameter3.size = 16;
		parameter3.shadowOffsetX = 2;
		parameter3.shadowOffsetY = 2;
		parameter3.color = Color.WHITE;
		parameter3.shadowColor = Color.BLACK;
		font3 = generator.generateFont(parameter3);

		parameterBig.size = 128;
		parameterBig.shadowOffsetX = 10;
		parameterBig.shadowOffsetY = 10;
		parameterBig.color = Color.WHITE;
		parameterBig.shadowColor = Color.BLACK;
		fontBig = generator.generateFont(parameterBig);

		parameterCyan.size = 64;
		parameterCyan.shadowOffsetX = 5;
		parameterCyan.shadowOffsetY = 5;
		parameterCyan.color = Color.CYAN;
		parameterCyan.shadowColor = Color.BLACK;
		fontCyan = generator.generateFont(parameterCyan);

		parameterCyan2.size = 32;
		parameterCyan2.shadowOffsetX = 3;
		parameterCyan2.shadowOffsetY = 3;
		parameterCyan2.color = Color.CYAN;
		parameterCyan2.shadowColor = Color.BLACK;
		fontCyan2 = generator.generateFont(parameterCyan2);

		parameterCyanBig.size = 128;
		parameterCyanBig.shadowOffsetX = 10;
		parameterCyanBig.shadowOffsetY = 10;
		parameterCyanBig.color = Color.CYAN;
		parameterCyanBig.shadowColor = Color.BLACK;
		fontCyanBig = generator.generateFont(parameterCyanBig);

		generator.dispose();
	}


	public int getScrW() {
		return scrW;
	}

	public int getScrH() {
		return scrH;
	}

	public void render () {
		super.render();
	}

	public void dispose () {
		super.dispose();
		font.dispose();
	}

}
