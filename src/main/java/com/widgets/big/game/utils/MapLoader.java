package com.widgets.big.game.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.demo.Tile;

public class MapLoader {

	public static List<Tile> loadMap(String filename) {
		ArrayList<String> lines = new ArrayList<String>();
		int width = 0;

		InputStream is = MapLoader.class.getClassLoader().getResourceAsStream(
				filename);
		System.out.println(is.toString());
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		while (true) {
			String line;
			try {
				line = reader.readLine();

				// no more lines to read
				if (line == null) {
					reader.close();
					break;
				}

				if (!line.startsWith("!")) {
					lines.add(line);
					width = Math.max(width, line.length());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println(e);
			}
		}

		List<Tile> tiles = new ArrayList<Tile>();
		for (int j = 0; j < 12; j++) {
			String line = lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tiles.add(t);
				}
			}
		}
		return tiles;
	}

}
