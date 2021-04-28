package com.cubecrusher.trancej;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class TrJr extends Game {
	private int scrW, scrH;
	public static final TrJr INSTANCE = new TrJr();
	private OrthographicCamera camera;

	TrJr(){
	}

	public void create () {
		System.out.println("INFO: Trance.create() called");
		Assets.load();
		this.scrW = Gdx.graphics.getWidth();
		this.scrH = Gdx.graphics.getHeight();
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, scrW, scrH);
		setScreen(new EpilepsyScreen(camera));
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
