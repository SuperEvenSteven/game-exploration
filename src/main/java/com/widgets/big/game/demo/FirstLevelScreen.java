package com.widgets.big.game.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.widgets.big.game.demo.Player.ActionState;
import com.widgets.big.game.demo.assets.AssetBackground;
import com.widgets.big.game.demo.assets.AssetEnemies;
import com.widgets.big.game.demo.assets.AssetImage;
import com.widgets.big.game.demo.assets.AssetPlayer;
import com.widgets.big.game.demo.assets.AssetSprite;
import com.widgets.big.game.demo.assets.Assets;
import com.widgets.big.game.demo.assets.Assets.AssetType;
import com.widgets.big.game.engine.ui.Sprite;
import com.widgets.big.game.framework.Background;
import com.widgets.big.game.framework.Input;
import com.widgets.big.game.framework.Input.KeyEvent;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.utils.MapLoader;

public class FirstLevelScreen implements Screen {

	enum GameState {
		RUNNING, DEAD
	}

	public static GameState gameState = GameState.RUNNING;

	private static final boolean DEBUG_ENABLED = true;

	// Constants
	private static final int KEY_LEFT = 37;
	private static final int KEY_RIGHT = 39;
	private static final int KEY_UP = 38;
	private static final int KEY_DOWN = 40;
	private static final int KEY_CTRL_L = 17;

	private List<Rectangle> boundaries;

	private List<Tile> tiles;
	private final Font font = new Font(null, Font.BOLD, 12);
	private Image currentPlayerImage;
	private Image currentEnemyImage;
	public static int score = 0;

	private Player hero;

	private Background bg1;

	private Background bg2;

	private List<Enemy> enemies;

	private BufferedImage background;

	public FirstLevelScreen() {
		init();
	}

	private void init() {
		Assets assets = Assets.instance();

		background = ((AssetImage) assets.get(AssetType.BACKGROUND)).getImage();

		assets.put(AssetType.BACKGROUND1, new AssetBackground(new Background(0,
				0)));
		assets.put(AssetType.BACKGROUND2, new AssetBackground(new Background(
				2160, 0)));

		bg1 = ((AssetBackground) assets.get(AssetType.BACKGROUND1))
				.getBackground();
		bg2 = ((AssetBackground) assets.get(AssetType.BACKGROUND2))
				.getBackground();

		assets.put(AssetType.HERO, new AssetPlayer(new Player(20, 20)));

		hero = ((AssetPlayer) assets.get(AssetType.HERO)).getPlayer();
		boundaries = hero.getBoundaries();

		enemies = ((AssetEnemies) assets.get(AssetType.ENEMIES)).getEnemies();

		Sprite enemy = ((AssetSprite) assets.get(AssetType.ENEMY)).getSprite();

		enemies.add(new Enemy(300, 390, enemy));
		enemies.add(new Enemy(700, 390, enemy));
		enemies.add(new Enemy(1000, 390, enemy));
		enemies.add(new Enemy(1300, 390, enemy));
		enemies.add(new Enemy(1600, 390, enemy));
		enemies.add(new Enemy(1900, 390, enemy));

		tiles = MapLoader.loadMap("maps/map1.txt");

		score = 0;

	}

	@Override
	public void update(float deltaTimeMs, List<KeyEvent> keyEvents) {

		// check if player has dropped off the screen
		if (hero.getCenterY() > 480) {
			FirstLevelScreen.gameState = GameState.DEAD;
		}

		checkKeyEvents(keyEvents);

		hero.update(deltaTimeMs);
		for (Enemy enemy : enemies) {
			enemy.update(deltaTimeMs);
		}
		bg1.update();
		bg2.update();
		updateTiles(deltaTimeMs);

		ArrayList<Bullet> projectiles = hero.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Bullet p = projectiles.get(i);
			if (p.isVisible() == true) {
				p.update(50);
			} else {
				projectiles.remove(i);
			}
		}

	}

	private void updateTiles(float deltaTimeMs) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			t.update(deltaTimeMs);
		}
	}

	@Override
	public void paint(Graphics g, Component game) {

		if (gameState == GameState.RUNNING) {

			// draw background
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), game);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), game);

			// draw tiles
			paintTiles(g, game);

			// paint projectiles
			ArrayList<Bullet> projectiles = hero.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Bullet p = projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}

			// draw player
			currentPlayerImage = hero.getSprite().getAnimation().getImage();
			g.drawImage(currentPlayerImage, hero.getCenterX() - 20,
					hero.getCenterY() - 20, game);

			// draw enemies
			for (Enemy enemy : enemies) {
				currentEnemyImage = enemy.getSprite().getAnimation().getImage();
				g.drawImage(currentEnemyImage, enemy.getCenterX() - 48,
						enemy.getCenterY() - 48, game);
			}

			// draw score
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("Score :" + Integer.toString(score), 700, 30);

			if (DEBUG_ENABLED) {
				showDebug(g);
				// draw boundaries around player
				g.setColor(Color.LIGHT_GRAY);
				for (Rectangle rect : boundaries) {
					g.drawRect((int) rect.getX(), (int) rect.getY(),
							(int) rect.getWidth(), (int) rect.getHeight());
				}

			}
		} else if (gameState == GameState.DEAD) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Game Over!!!", 320, 240);
			g.setColor(Color.BLUE);
			g.drawString("Hit ENTER to try again", 270, 290);
		}
	}

	private void paintTiles(Graphics g, Component game) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), game);
		}
	}

	private void showDebug(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Center x :" + Integer.toString(hero.getCenterY()), 700,
				60);
		g.drawString("Center y :" + Integer.toString(hero.getCenterX()), 700,
				90);
		g.drawString("Speed x :" + Integer.toString(hero.getSpeedX()), 700, 120);
		g.drawString("Speed y :" + Integer.toString(hero.getSpeedY()), 700, 150);
	}

	private void checkKeyEvents(List<KeyEvent> keyEvents) {
		for (KeyEvent keyEvent : keyEvents) {
			if (keyEvent.type == Input.KeyEvent.KEY_DOWN) {
				// System.out.println(keyEvent.keyChar + " character pressed");
				// System.out.println(keyEvent.keyCode + " code pressed");
				switch (keyEvent.keyCode) {
				case KEY_RIGHT:
					hero.moveRight();
					hero.setSpriteAction(ActionState.WALKING_RIGHT);
					break;
				case KEY_LEFT:
					hero.setSpriteAction(ActionState.WALKING_LEFT);
					hero.moveLeft();
					break;
				case KEY_UP:
					hero.jump();
					if (hero.getSpeedX() < 0) {
						hero.setSpriteAction(ActionState.JUMPING_LEFT);
						System.out.println("jumping left");
					} else {
						hero.setSpriteAction(ActionState.JUMPING_RIGHT);
						System.out.println("jumping right");
					}
					break;
				case KEY_DOWN:
					hero.setDucked(true);
					hero.setSpriteAction(ActionState.DUCKING);
					break;
				case KEY_CTRL_L:
					hero.shoot();
					break;
				case 10:
					if (gameState == GameState.DEAD) {
						init();
						gameState = GameState.RUNNING;
					}
				}
			}
			if (keyEvent.type == Input.KeyEvent.KEY_UP) {

				switch (keyEvent.keyCode) {
				case KEY_LEFT:
					hero.stopLeft();
					hero.setSpriteAction(ActionState.STANDING);
					break;
				case KEY_RIGHT:
					hero.stopRight();
					hero.setSpriteAction(ActionState.STANDING);
					break;
				case KEY_UP:
					break;
				case KEY_DOWN:
					hero.setDucked(false);
					hero.setSpriteAction(ActionState.STANDING);
					break;
				}
			}
		}

	}
}
