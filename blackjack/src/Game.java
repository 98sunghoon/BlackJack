//package shootingGame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.ArrayList;
//
//public class Game extends JFrame implements Runnable, KeyListener {
//	public static int f_width = 500;
//	public static int f_height = 800;
//	Thread th;
//	Image me_img = new ImageIcon("me.png").getImage();
//	Image[] crash = new Image[3];
//	Image background;
//	public static int count = 0;
//	private int me_x;
//	private int me_y;
//	private int me_width = me_img.getWidth(null);
//	private int me_height = me_img.getHeight(null);
//	private int me_speed = 1;
//	private int me_damage = 10;
//	private int me_hp = 3;
//	private int responeCount = 0;
//	private boolean hitable = true;
//	boolean[] keyDirection = { false, false, false, false };// 위,왼,아,오
//	boolean keyShoot = false;
//
//	Image bImage;
//	Graphics bGraphics;
//
//	ArrayList<Missile> mArrayList = new ArrayList<Missile>();
//	ArrayList<Asteroid> aArrayList = new ArrayList<Asteroid>();
//	ArrayList<Crash> cArrayList = new ArrayList<Crash>();
//
//	public Game() {
//		init();
//		th = new Thread(this);
//		th.start();
//	}
//
//	private void init() {
//		setTitle("***SHOOTING GAME***");
//		setSize(f_width, f_height);
//		setVisible(true);
//		addKeyListener(this);
//		background = new ImageIcon("background.png").getImage();
//		for (int i = 0; i < crash.length; i++) {
//			crash[i] = new ImageIcon("crash" + i + ".png").getImage();
//		}
//		me_x = (f_width / 2 - me_width / 2);
//		me_y = (int) ((f_height / 2 - me_height / 2) * 1.7);
//
//	}
//
//	public void run() {
//		try {
//			while (true) {
//
//				if (count % Asteroid.frequency == 0)
//					aArrayList.add(new Asteroid((byte) ((Math.random() * 7) + 1)));
//				keyprocess();
//
//				repaint();
//				Thread.sleep(5);
//				count++;
//				if (count == Gamedata.level * 1000) {
//					Gamedata.level++;
//					count = 0;
//				}
//			}
//		} catch (Exception e) {
//		}
//	}
//
//	public void paint(Graphics g) {
//		bImage = createImage(f_width, f_height);
//		bGraphics = bImage.getGraphics();
//		draw();
//		g.drawImage(bImage, 0, 0, this);
//	}
//
//	private void draw() {
//		bGraphics.drawImage(background, 0, 0, this);
//		bGraphics.setColor(Color.white);
//		bGraphics.drawString("LEVEL" + Gamedata.level, f_width - 100, f_height - 30);
//		// 모든 미사일들을 움직입니다.
//		// 화면에서 사라지면 삭제합니다.
//		for (int i = 0; i < mArrayList.size(); i++) {
//			mArrayList.get(i).draw(bGraphics);
//			if (mArrayList.get(i).y <= 0)
//				mArrayList.remove(i);
//		}
//		// 모든 소행성들을 움직입니다.
//		// 화면에서 사라지면 삭제합니다.
//		for (int i = 0; i < aArrayList.size(); i++) {
//			aArrayList.get(i).draw(bGraphics);
//			if (aArrayList.get(i).y >= f_height)
//				aArrayList.remove(i);
//		}
//		// 미사일이 맞았는지 검사합니다.
//		hitCheck();
//
//		bGraphics.drawImage(me_img, me_x, me_y, this);
//
//		// 모든 폭발 애니메이션을 보입니다.
//		// 애니메이션이 끝나면 Crash.draw 는 false를 반환하고, 객체를 삭제합니다.
//
//		for (int i = 0; i < cArrayList.size(); i++) {
//			if (!cArrayList.get(i).draw(bGraphics))
//				cArrayList.remove(i);
//		}
//	}
//
//	private void hitCheck() {
//		// 미사일과 소행성의 충돌을 판정합니다.
//		for (int j = 0; j < aArrayList.size(); j++) {
//			for (int i = 0; i < mArrayList.size(); i++) {
//				if (mArrayList.get(i).x + mArrayList.get(i).width >= aArrayList.get(j).x
//						&& aArrayList.get(j).x + aArrayList.get(j).width >= mArrayList.get(i).x
//						&& mArrayList.get(i).y + mArrayList.get(i).height >= aArrayList.get(j).y
//						&& aArrayList.get(j).y + aArrayList.get(j).height >= mArrayList.get(i).y) {
//					cArrayList.add(new Crash(mArrayList.get(i).x, mArrayList.get(i).y));
//					mArrayList.remove(i);
//					aArrayList.get(j).hp -= me_damage;
//					i--;
//				}
//			}
//			if (aArrayList.get(j).hp <= 0) {
//				aArrayList.remove(j);
//				j--;
//			}
//		}
//		// 소행성과 플레이어의 충돌을 판정합니다.
//		if (hitable) {
//			for (int i = 0; i < aArrayList.size(); i++) {
//				if (me_x + me_width -10 >= aArrayList.get(i).x 
//						&& aArrayList.get(i).x + aArrayList.get(i).width >= me_x + 10
//						&& me_y + me_height >= aArrayList.get(i).y
//						&& aArrayList.get(i).y + aArrayList.get(i).height >= me_y + 30) {
//					cArrayList.add(new Crash(me_x, me_y, me_width, me_height));
//					me_hp--;
//					hitable = false;
//				}
//			}
//		} else {
//			if (responeCount >= 200) {
//				responeCount = 0;
//				hitable = true;
//			} else {
//				responeCount++;
//			}
//		}
//		if (me_hp <= 0) {
//			gameover();
//		}
//	}
//
//	private void gameover() {
//		System.out.println("GameOver");
//	}
//
//	private void Shooting() {
//		mArrayList.add(new Missile(me_x + me_width / 2, me_y + me_height / 2));
//	}
//
//	public void keyprocess() {
//		if (keyDirection[0] && me_y >= 50)
//			me_y -= me_speed;
//		if (keyDirection[1] && me_x >= 10)
//			me_x -= me_speed;
//		if (keyDirection[2] && me_y <= f_height - me_height - 10)
//			me_y += me_speed;
//		if (keyDirection[3] && me_x <= f_width - me_width - 10)
//			me_x += me_speed;
//		if (keyShoot && count % 30 == 0)
//			Shooting();
//	}
//
//	public void keyPressed(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//			keyDirection[0] = true;
//			break;
//		case KeyEvent.VK_LEFT:
//			keyDirection[1] = true;
//			break;
//		case KeyEvent.VK_DOWN:
//			keyDirection[2] = true;
//			break;
//		case KeyEvent.VK_RIGHT:
//			keyDirection[3] = true;
//			break;
//		case KeyEvent.VK_SPACE:
//			keyShoot = true;
//			break;
//		}
//	}
//
//	public void keyReleased(KeyEvent e) {
//		switch (e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//			keyDirection[0] = false;
//			break;
//		case KeyEvent.VK_LEFT:
//			keyDirection[1] = false;
//			break;
//		case KeyEvent.VK_DOWN:
//			keyDirection[2] = false;
//			break;
//		case KeyEvent.VK_RIGHT:
//			keyDirection[3] = false;
//			break;
//		case KeyEvent.VK_SPACE:
//			keyShoot = false;
//			break;
//		}
//	}
//
//	public void keyTyped(KeyEvent e) {
//	}
//}
