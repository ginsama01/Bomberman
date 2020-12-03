package uet.oop.bomberman.entities.candead.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.candead.EntityCanDead;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

import java.util.Random;

public class Doll extends Enemy {

    private boolean leftOrRight = true;
    private int speed = 2;

    public Doll(int x, int y, Image img) {
        super(x, y, img);
    }


    @Override
    public void whenDead() {
        if (dead) {
            if (timing == 0) {
                img = Sprite.doll_dead.getFxImage();
                Sound.play("AA126_11");
            }
            timing++;
        }
    }

    public void followRow() {
        if (!leftOrRight) {
            if (BombermanGame.map[y/32].charAt(x/32+1) != '#'
                    && BombermanGame.map[y/32].charAt(x/32+1) != '*'
                    && BombermanGame.map[y/32].charAt(x/32+1) != 'B') {
                x += speed;
                if (timing % 21 == 0) {
                    img = Sprite.doll_right1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.doll_right2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.doll_right3.getFxImage();
                }
                timing++;
            } else {
                leftOrRight = true;
                timing = 0;
            }
        } else {
            if (BombermanGame.map[y/32].charAt((x-1)/32) != '#'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != '*'
                    && BombermanGame.map[y/32].charAt((x-1)/32) != 'B') {
                x -= speed;
                if (timing % 21 == 0) {
                    img = Sprite.doll_left1.getFxImage();
                } else if (timing % 21 == 7) {
                    img = Sprite.doll_left2.getFxImage();
                } else if (timing % 21 == 14) {
                    img = Sprite.doll_left3.getFxImage();
                }
                timing++;
            } else {
                leftOrRight = false;
                timing = 0;
            }
        }


    }


    public void ballomAction() {
        followRow();
    }

    @Override
    public void update() {
        whenDead();
        if (!dead) {
            ballomAction();
        }
    }
}
