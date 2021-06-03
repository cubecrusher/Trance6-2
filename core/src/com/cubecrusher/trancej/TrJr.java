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
	public BitmapFont font, font2, font3, fontBig, fontCyan, fontCyan2, fontCyan3, fontCyanBig;
	public BitmapFont rfont, rfont2, rfont3, rfontBig, rfontCyan, rfontCyan2, rfontCyan3, rfontCyanBig;

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
		this.rfont = new BitmapFont();
		this.rfont2 = new BitmapFont();
		this.rfont3 = new BitmapFont();
		this.rfontBig = new BitmapFont();
		this.rfontCyanBig = new BitmapFont();
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
		FreeTypeFontGenerator rgenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/guinew.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterBig = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyan = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyan2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyan3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameterCyanBig = new FreeTypeFontGenerator.FreeTypeFontParameter();

		FreeTypeFontGenerator.FreeTypeFontParameter rparameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameterBig = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameterCyan = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameterCyan2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameterCyan3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter rparameterCyanBig = new FreeTypeFontGenerator.FreeTypeFontParameter();

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

		parameterCyan3.size = 16;
		parameterCyan3.shadowOffsetX = 2;
		parameterCyan3.shadowOffsetY = 2;
		parameterCyan3.color = Color.CYAN;
		parameterCyan3.shadowColor = Color.BLACK;
		fontCyan3 = generator.generateFont(parameterCyan3);

		parameterCyanBig.size = 128;
		parameterCyanBig.shadowOffsetX = 10;
		parameterCyanBig.shadowOffsetY = 10;
		parameterCyanBig.color = Color.CYAN;
		parameterCyanBig.shadowColor = Color.BLACK;
		fontCyanBig = generator.generateFont(parameterCyanBig);


		rparameter.size = 72;
		rparameter.shadowOffsetX = 5;
		rparameter.shadowOffsetY = 5;
		rparameter.color = Color.WHITE;
		rparameter.shadowColor = Color.BLACK;
		rparameter.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfont = rgenerator.generateFont(rparameter);

		rparameter2.size = 48;
		rparameter2.shadowOffsetX = 3;
		rparameter2.shadowOffsetY = 3;
		rparameter2.color = Color.WHITE;
		rparameter2.shadowColor = Color.BLACK;
		rparameter2.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfont2 = rgenerator.generateFont(rparameter2);

		rparameter3.size = 24;
		rparameter3.shadowOffsetX = 2;
		rparameter3.shadowOffsetY = 2;
		rparameter3.color = Color.WHITE;
		rparameter3.shadowColor = Color.BLACK;
		rparameter3.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfont3 = rgenerator.generateFont(rparameter3);

		rparameterBig.size = 128;
		rparameterBig.shadowOffsetX = 10;
		rparameterBig.shadowOffsetY = 10;
		rparameterBig.color = Color.WHITE;
		rparameterBig.shadowColor = Color.BLACK;
		rparameterBig.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfontBig = rgenerator.generateFont(rparameterBig);

		rparameterCyan.size = 72;
		rparameterCyan.shadowOffsetX = 5;
		rparameterCyan.shadowOffsetY = 5;
		rparameterCyan.color = Color.CYAN;
		rparameterCyan.shadowColor = Color.BLACK;
		rparameterCyan.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfontCyan = rgenerator.generateFont(rparameterCyan);

		rparameterCyan2.size = 48;
		rparameterCyan2.shadowOffsetX = 3;
		rparameterCyan2.shadowOffsetY = 3;
		rparameterCyan2.color = Color.CYAN;
		rparameterCyan2.shadowColor = Color.BLACK;
		rparameterCyan2.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfontCyan2 = rgenerator.generateFont(rparameterCyan2);

		rparameterCyan3.size = 24;
		rparameterCyan3.shadowOffsetX = 2;
		rparameterCyan3.shadowOffsetY = 2;
		rparameterCyan3.color = Color.CYAN;
		rparameterCyan3.shadowColor = Color.BLACK;
		rparameterCyan3.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfontCyan3 = rgenerator.generateFont(rparameterCyan3);

		rparameterCyanBig.size = 128;
		rparameterCyanBig.shadowOffsetX = 10;
		rparameterCyanBig.shadowOffsetY = 10;
		rparameterCyanBig.color = Color.CYAN;
		rparameterCyanBig.shadowColor = Color.BLACK;
		rparameterCyanBig.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		rfontCyanBig = rgenerator.generateFont(rparameterCyanBig);

		generator.dispose();
		rgenerator.dispose();
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
	}

}
