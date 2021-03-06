package plane;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * 
 * @author æ¹é¶å?
 * æ¬æ¥æè¿°ææº
 *
 */

public class Enemy {
	
	private int enemy_x, enemy_y;//ææºåæ 
	private int enemy_y0;//ææºåå§yåæ 
	private Image ememyPic[];//ææºå¾çæ°ç»
	private final int STEP = 2;//ææºç§»å¨éåº¦
	boolean stayed = true;//ææºçå­æ è¯
	private Break b;//çç¸å¾çå¯¹è±¡
	private int id;//çç¸å¾çID

	public Enemy(int y) {
		//ææºåæ åå§å?
		enemy_x = (int) (Math.random()*500);
		enemy_y0 = enemy_y = y;
				
		ememyPic = new Image[5];
		for(int i = 1; i <= ememyPic.length; i++) {
			ememyPic[i-1] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bullet0" + i + ".png"));
		}
	}
	/**
	 * ç»å¶ææºæ¹æ³
	 * @param g
	 * @param c
	 */
	void drawEnemy(Graphics g, Canvas c, int i) {
		if(stayed)
			g.drawImage(ememyPic[i], enemy_x, enemy_y, GamePanel.ENEMY_SIZE, GamePanel.ENEMY_SIZE, c);//ç»å¶ææº
		else if(id == 0) {
				b = new Break(enemy_x, enemy_y);
				b.enemy_break(g, c, id);
				id++;
			}

		if(b != null && id != 0)
			if(id == 29){
				b.enemy_break(g, c, id);
				id = 0;
			} else {
				b.enemy_break(g, c, id);
				id++;
			}
	}
	/**
	 * ææºç§»å¨æ§å¶æ¹æ³
	 */
	void enemyMove() {
		if(enemy_y > GamePanel.MAP_HEIGHT || stayed == false)
		{
			
			if(GamePanel.time >= 2500) {//50ç§è¿åææºä¸å¨åºç?
				enemy_x = 0;
				enemy_y = GamePanel.MAP_HEIGHT+GamePanel.PLANE_SIZE;
			} else {
				enemy_x = (int) (Math.random()*500);
				enemy_y = enemy_y0;
				stayed = true;//ææºè®¾ç½®ä¸ºçå­ç¶æ?
			}
		} else
			enemy_y += STEP;
	}
	/**
	 * è·åææºåæ æ¹æ³
	 * @return Point
	 */
	Point getX_Y() {
		
		return new Point(enemy_x, enemy_y);
		
	}
	
}
