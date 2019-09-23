package RPGE;

import utils.Random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String[] commands = {"вверх <n> - Пройти вверх на n шагов", "вниз <n> - Пройти вниз на n шагов",
            "влево <n> - Пройти влево на n шагов", "вправо <n> - Пройти вправо на n шагов",
            "спать - Восстановить энергию, если ее слишком мало","осмотреться - карта метсности в " +
            "радиусе вашего зрения" , "осмотреть_себя - узнать информацию о персонаже"};

    static Map map = new Map(500, 500, 3, 3, 1);

    public static void main(String[] args) throws IOException {
        init();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Введите свое имя: ");

        String name = reader.readLine();

        String[] randUnknownCommandPhrases = {": хм, ума не приложу как это сделать, но я могу вот что: ",
                ": я так не умею :( Кстати, могу так: ", ": возможно мне стоит научиться делать это, но пока я могу только: "};

        Player player = new Player(name);
        while (true) {
            System.out.println("* Что бы мне сделать? *");
            String command = reader.readLine();
            String[] commandArray = command.split(" ");
            if (commandArray[0].equals("вправо")) {
                int newPosX = player.getPosX() + Integer.parseInt(commandArray[1]);
                if (newPosX <= 500) {
                    player.moveX(Integer.parseInt(commandArray[1]));
                    map.changePlayerPos(player.getPosX(), player.getPosY(), player.getFOV());
                } else {
                    map.changePlayerPos( 0 + player.getPosX() - 499,  player.getPosY(), player.getFOV());
                }
            } else if (commandArray[0].equals("влево")) {
                int newPosX = player.getPosX() - Integer.parseInt(commandArray[1]);
                if (newPosX >= 0) {
                    player.moveX(Integer.parseInt(commandArray[1]) * -1);
                    map.changePlayerPos(player.getPosX(), player.getPosY(), player.getFOV());
                } else {
                    map.changePlayerPos(499 + player.getPosX(), player.getPosY(), player.getFOV());
                }
            } else if (commandArray[0].equals("вверх")) {
                int newPosY = player.getPosY() + Integer.parseInt(commandArray[1]);
                if(newPosY >= 0) {
                    player.moveY(Integer.parseInt(commandArray[1]) * -1);
                    map.changePlayerPos(player.getPosX(), player.getPosY(), player.getFOV());
                } else {
                    map.changePlayerPos(player.getPosX(), 499 + player.getPosY(), player.getFOV());
                }
            } else if (commandArray[0].equals("вниз")) {
                int newPosY = player.getPosY() - Integer.parseInt(commandArray[1]);
                if(newPosY <= 500) {
                    player.moveY(Integer.parseInt(commandArray[1]));
                    map.changePlayerPos(player.getPosX(), 0 + player.getPosY() - 499, player.getFOV());
                } else {
                    System.out.println(name + ": Какие-то нивидимые силы не дают мне пройти туда");
                }
            } else if (commandArray[0].equals("спать")) {
                player.sleep();
            } else if (commandArray[0].equals("осмотреть_себя")){
                System.out.println("Меня зовут: " + player.getName());
                System.out.println("HP: " + player.getHP());
                System.out.println("Энергия: " + player.getEnergy());
                System.out.println("Радиус обзора: " + player.getFOV());
            } else if (commandArray[0].equals("осмотреться")){
                map.changePlayerPos(player.getPosX(), player.getPosY(), player.fieldOfView);
            }
            else {
                System.out.println(name + randUnknownCommandPhrases[Random.randInt(0, randUnknownCommandPhrases.length - 1)]);
                printHelp();
            }
        }
    }

    public static void printHelp(){
        for(int i = 0; i < commands.length; i++){
            System.out.println(commands[i]);
        }
    }

    static void init(){
        map.generateMap();
    }
}
