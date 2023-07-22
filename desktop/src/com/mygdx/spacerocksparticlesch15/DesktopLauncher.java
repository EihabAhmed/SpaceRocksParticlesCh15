package com.mygdx.spacerocksparticlesch15;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.spacerocksparticlesch15.SpaceRocksParticlesCh15;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Space Rocks Particles Ch15");
		new Lwjgl3Application(new SpaceRocksParticlesCh15(), config);
	}
}
