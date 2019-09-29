package RPGE;

import engine.GameObject;
import engine.Position;
import utils.Random;

import org.telegram.telegrambots.util.WebhookUtils;

public class Player extends GameObject {
    String name = "Безымянный Скиталец";
    int HP = 100;
    int energy  = 200;
    int fieldOfView = 10;
    int level = 1;
    int xp = 2;

    char mapIcon = '@';

    public String[] welcomeMessages = {" прибыл из космоса", " вылез из под земли", " наконец-то вышел из дома",
            " был добавлен в мир"};

    public Player(String name){
        super("name", new Position(0,0), 200, '@');
        this.name = name;
        System.out.println(name + welcomeMessages[(int)(Math.random() * ((welcomeMessages.length)))]);
    }

    public void moveX(int deltaX) {
        int dangerChance = 10;
        if(deltaX > fieldOfView) {
            dangerChance = 75;
        }
        int randInt = Random.randInt(0,100);
        if(randInt<=dangerChance){
            int looseHP = Random.randInt(5, 30);
            System.out.println(name + ": так быстро бежал, что по дороге не заметил яму, споткнулся и потерял " +
                    randInt +" HP");
            HP -= looseHP;
        }
        position.x += deltaX;
        System.out.println(name + ": Моя позиция: " + position.x + ", " + position.y);
        energy -= Math.abs(deltaX);
    }
    public void moveY(int deltaY) {
        int dangerChanse = 10;
        if(deltaY > fieldOfView) {
            dangerChanse = 75;
        }
        int randInt = Random.randInt(0, 100);
        if(randInt<=dangerChanse){
            int looseHP = Random.randInt(5, 30);
            System.out.println(name + ": так быстро бежал, что по дороге не заметил яму, споткнулся и потерял " +
                    randInt +" HP");
            HP -= looseHP;
        }
        super.position.y += deltaY;
        System.out.println(name + ": Моя позиция: " + position.x + ", " + position.y);
        energy -= Math.abs(deltaY);
    }

    public void sleep() {
        if(energy <= 50) {
            System.out.println(name + ": Z-z-z-z...");
            energy += 100;
        } else {
            System.out.println(name + ": Я пока не устал!");
        }
    }

    public int getXP(){
        return xp;
    }
    public int getLevel(){
        return level;
    }
    public String getName() {
        return name;
    }
    public int getHP() {
        return HP;
    }
    public int getEnergy() {
        return energy;
    }
    public int getFOV() {
        return fieldOfView;
    }
    public int getPosX() {
        return position.x;
    }
    public int getPosY() {
        return position.y;
    }
}

