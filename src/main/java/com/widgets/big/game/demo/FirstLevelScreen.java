package com.widgets.big.game.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.widgets.big.game.demo.Player.ActionState;
import com.widgets.big.game.engine.applet.AppletGame;
import com.widgets.big.game.framework.Background;
import com.widgets.big.game.framework.Game;
import com.widgets.big.game.framework.Input;
import com.widgets.big.game.framework.Input.KeyEvent;
import com.widgets.big.game.framework.Screen;
import com.widgets.big.game.utils.MapLoader;

public class FirstLevelScreen extends Screen {

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

	private List<KeyEvent> keyEvents;

	public FirstLevelScreen(Game game) {
		super(game);
		init();
	}

	@Override
	public void init() {
		Assets.hero = new Player(20, 20);

		boundaries = Assets.hero.getBoundaries();

		Enemy enemy1 = new Enemy(300, 390, Assets.enemy);
		Enemy enemy2 = new Enemy(700, 390, Assets.enemy);
		Enemy enemy3 = new Enemy(1000, 390, Assets.enemy);
		Enemy enemy4 = new Enemy(1300, 390, Assets.enemy);
		Enemy enemy5 = new Enemy(1600, 390, Assets.enemy);

		Assets.enemies = Arrays.asList(enemy1, enemy2, enemy3, enemy4, enemy5);

		Assets.bg1 = new Background(0, 0);
		Assets.bg2 = new Background(2160, 0);

		tiles = MapLoader.loadMap("maps/map1.txt");

		score = 0;

	}

	@Override
	public void update(float deltaTimeMs) {

		// check if player has dropped off the screen
		if (Assets.hero.getCenterY() > 480) {
			FirstLevelScreen.gameState = GameState.DEAD;
		}

		checkKeyEvents();

		Assets.hero.update(deltaTimeMs);
		for (Enemy enemy : Assets.enemies) {
			enemy.update(deltaTimeMs);
		}
		Assets.bg1.update();
		Assets.bg2.update();
		updateTiles(deltaTimeMs);

		ArrayList<Bullet> projectiles = Assets.hero.getProjectiles();
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
	public void paint(Graphics g, AppletGame game) {

		if (gameState == GameState.RUNNING) {

			// draw background
			g.drawImage(Assets.background, Assets.bg1.getBgX(),
					Assets.bg1.getBgY(), game);
			g.drawImage(Assets.background, Assets.bg2.getBgX(),
					Assets.bg2.getBgY(), game);

			// draw tiles
			paintTiles(g, game);

			// paint projectiles
			ArrayList<Bullet> projectiles = Assets.hero.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Bullet p = projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}

			// draw player
			currentPlayerImage = Assets.hero.getSprite().getAnimation()
					.getImage();
			g.drawImage(currentPlayerImage, Assets.hero.getCenterX() - 20,
					Assets.hero.getCenterY() - 20, game);

			// draw enemies
			for (Enemy enemy : Assets.enemies) {
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

	private void paintTiles(Graphics g, AppletGame game) {
		for (int i = 0; i < tiles.size(); i++) {
			Tile t = tiles.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), game);
		}
	}

	private void showDebug(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Center x :" + Integer.toString(Assets.hero.getCenterY()),
				700, 60);
		g.drawString("Center y :" + Integer.toString(Assets.hero.getCenterX()),
				700, 90);
		g.drawString("Speed x :" + Integer.toString(Assets.hero.getSpeedX()),
				700, 120);
		g.drawString("Speed y :" + Integer.toString(Assets.hero.getSpeedY()),
				700, 150);
	}

	private void checkKeyEvents() {
		keyEvents = game.getInput().getKeyEvents();
		for (KeyEvent keyEvent : keyEvents) {
			if (keyEvent.type == Input.KeyEvent.KEY_DOWN) {
				// System.out.println(keyEvent.keyChar + " character pressed");
				// System.out.println(keyEvent.keyCode + " code pressed");
				switch (keyEvent.keyCode) {
				case KEY_RIGHT:
					Assets.hero.moveRight();
					Assets.hero.setSpriteAction(ActionState.WALKING_RIGHT);
					break;
				case KEY_LEFT:
					Assets.hero.setSpriteAction(ActionState.WALKING_LEFT);
					Assets.hero.moveLeft();
					break;
				case KEY_UP:
					Assets.hero.jump();
					if (Assets.hero.getSpeedX() < 0) {
						Assets.hero.setSpriteAction(ActionState.JUMPING_LEFT);
						System.out.println("jumping left");
					} else {
						Assets.hero.setSpriteAction(ActionState.JUMPING_RIGHT);
						System.out.println("jumping right");
					}
					break;
				case KEY_DOWN:
					Assets.hero.setDucked(true);
					Assets.hero.setSpriteAction(ActionState.DUCKING);
					break;
				case KEY_CTRL_L:
					Assets.hero.shoot();
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
					Assets.hero.stopLeft();
					Assets.hero.setSpriteAction(ActionState.STANDING);
					break;
				case KEY_RIGHT:
					Assets.hero.stopRight();
					Assets.hero.setSpriteAction(ActionState.STANDING);
					break;
				case KEY_UP:
					break;
				case KEY_DOWN:
					Assets.hero.setDucked(false);
					Assets.hero.setSpriteAction(ActionState.STANDING);
					break;
				}
			}
		}

	}
}
