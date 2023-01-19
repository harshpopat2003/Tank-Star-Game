package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Iterator;


public class choosetank implements Screen {

    //make array list tanks
    Array <Texture> tanks = new Array<Texture>();
    //print all tanks in array list as function
    public void printtanks1(){
        for (int i = 0; i < tanks.size; i++) {
            Iterator iter = tanks.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
            game.batch.draw(tanks.get(i), 100 + i * 300, 100, 200, 200);
        }
    }
    int i = 0;
    //make array list tanks
    Array <Texture> tanks2 = new Array<Texture>();
    //print all tanks in array list
    int j = 0;

    //make functionto store the tank
//    void storetank(int tanknumber) {
//        //store the tank number in the arraylist tanks
//        tanks.add(tanknumber);
//
//    }
    //make a function to check if the tank is already selected
//    boolean checktank(int tanknumber) {
//        //check if the tank is already selected
//        if (tanks.contains(tanknumber)) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
    //make a function to remove the tank from the arraylist
//    void removetank(int tanknumber) {
//        //remove the tank from the arraylist
//        tanks.remove(tanknumber);
//    }


    TankStarsGame game;
    Texture logo;
    Texture side;
    Texture middle;
    Texture settings;
    Texture player1;
    Texture player2;
    Texture playbutton;
    Texture choooseTank;
    Texture mark1Tank;
    Texture mark1Tankside1;
    Texture mark1Tankside2;
    Texture frostTank;
    Texture frostTankside1;
    Texture frostTankside2;
    Texture quit;
    Texture choose1;
    Texture choose2;
    Texture buratinotank;
    Texture buratinotankside1;
    Texture buratinotankside2;
    Texture buratinolabel;
    Texture arrowright1;
    Texture arrowright2;
    Texture arrowleft1;
    Texture arrowleft2;
    Texture loadgame;
    Texture frostlabel;
    Texture mark1label;
    Texture greentick1;
    Texture greentick2;
    Music beatbackgroud2;



    boolean greentick1bool = false;
    boolean greentick2bool = false;
    boolean arrowright1chilck = false;
    boolean arrowrleft1chilck = false;

    boolean arrowright2chilck = false;
    boolean arrowrleft2chilck = false;


    public choosetank(TankStarsGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        logo = new Texture("logogame.png");
        side = new Texture("side1tank.jpg");
        middle = new Texture("background.jpg");
        settings = new Texture("settings.png");
        player1 = new Texture("player1.png");
        player2 = new Texture("player2.png");
        playbutton = new Texture("playbutton.jpg");
        choooseTank = new Texture("choosetank.png");
        mark1Tank = new Texture("mark1tank.png");
        mark1Tankside1 = new Texture("mark1tank.png");
        mark1Tankside2 = new Texture("mark1tank.png");
        frostTank = new Texture("frosttank.png");
        frostTankside1 = new Texture("frosttank.png");
        frostTankside2 = new Texture("frosttank.png");
        quit = new Texture("quit.jpg");
        choose1 = new Texture("choose.jpg");
        choose2 = new Texture("choose.jpg");
        arrowright1 = new Texture("arrowright.png");
        arrowright2 = new Texture("arrowright.png");
        arrowleft1 = new Texture("arrowleft.png");
        arrowleft2 = new Texture("arrowleft.png");
        frostlabel = new Texture("frostlabel.png");
        mark1label = new Texture("mark1label.png");
        greentick1 = new Texture("greentick.png");
        greentick2 = new Texture("greentick.png");
        loadgame = new Texture("loadgame.png");
        buratinotank = new Texture("buratinotank.png");
        buratinotankside1 = new Texture("buratinotank.png");
        buratinotankside2 = new Texture("buratinotank.png");
        buratinolabel = new Texture("buratinolabel.png");
        beatbackgroud2 = Gdx.audio.newMusic(Gdx.files.internal("beat2.mp3"));
        beatbackgroud2.setLooping(true);
        beatbackgroud2.play();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
        game.batch.draw(middle, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch.draw(side, 0, 20, 610, 500);
        game.batch.draw(side, 890, 20,615, 500);
        game.batch.draw(logo, 450, 480, 600 ,400);




        ///player1
        game.batch.draw(player1, 175, 600, 325, 100);
        game.batch.draw(choooseTank, 195, 525, 280, 75);
        game.batch.draw(mark1Tankside1, 15, 135, 600, 325);
        game.batch.draw(mark1label, 180, 420, 300, 75);
        game.batch.draw(arrowright1, 470, 425, 65, 63);
        if (Gdx.input.getX() > 470 && Gdx.input.getX() < 535 && Gdx.input.getY() > 425 && Gdx.input.getY() < 488) {
            game.batch.draw(arrowright1, 468, 423, 70, 68);
            if (Gdx.input.isTouched()) {
                arrowright1chilck = true;
            }
        }
        if (arrowright1chilck == true) {
            //remove the mark1tank
            mark1Tankside1.dispose();
            game.batch.draw(side, 0, 20, 615, 500);
            game.batch.draw(frostTankside1, 70, 20, 500, 500);
            game.batch.draw(frostlabel, 170, 420, 310, 80);
            game.batch.draw(arrowright1, 470, 425, 65, 63);
        }

        game.batch.draw(arrowleft1, 120, 430, 55, 55);
        if (Gdx.input.getX() > 120 && Gdx.input.getX() < 175 && Gdx.input.getY() > 430 && Gdx.input.getY() < 485) {
            game.batch.draw(arrowleft1, 118, 428, 60, 60);
            if (Gdx.input.isTouched()) {
                arrowrleft1chilck = true;
            }
        }
        if (arrowrleft1chilck == true) {
            //remove the mark1tank
            frostTankside1.dispose();
            mark1Tankside1.dispose();
            game.batch.draw(side, 0, 20, 615, 500);
            game.batch.draw(buratinotankside1, 60, 40, 480, 480);
            game.batch.draw(buratinolabel, 180, 415, 300, 85);
            //game.batch.draw(arrowleft1, 120, 430, 55, 55);
            game.batch.draw(arrowright1, 470, 420, 65, 63);
            if (Gdx.input.getX() > 470 && Gdx.input.getX() < 535 && Gdx.input.getY() > 425 && Gdx.input.getY() < 488) {
                game.batch.draw(arrowright1, 468, 419, 70, 68);
//                if (Gdx.input.isTouched()) {
//                    arrowright1chilck = true;
//                }
            }
        }
        game.batch.draw(choose1, 250, 32, 150, 75);
        if (Gdx.input.getX() > 250 && Gdx.input.getX() < 400 && Gdx.input.getY() > 800 && Gdx.input.getY() < 850) {
            game.batch.draw(choose1, 248, 30, 155, 80);
            if (Gdx.input.isTouched()) {
                greentick1bool = true;
                //game.setScreen(new gameIn(game));
                //dispose();
            }
        }
        if (greentick1bool == true) {
            game.batch.draw(greentick1, 380, 75, 50, 50);
        }






        ///player2
        //add tanks to an array list and then use a for loop to cycle through them

        game.batch.draw(player2, 1020, 600, 325, 80);
        game.batch.draw(choooseTank, 1050, 525, 280, 75);
        game.batch.draw(frostlabel, 1040, 420, 310, 80);
        game.batch.draw(frostTankside2, 950, 20, 500, 500);
        game.batch.draw(arrowright2, 1350, 425, 65, 63);
        if (Gdx.input.getX() > 1350 && Gdx.input.getX() < 1415 && Gdx.input.getY() > 425 && Gdx.input.getY() < 488) {
            game.batch.draw(arrowright2, 1348, 423, 70, 68);
            if (Gdx.input.isTouched()) {
                arrowright2chilck = true;
            }
        }
        if (arrowright2chilck == true) {
            //remove the mark1tank
            frostTankside2.dispose();
            game.batch.draw(side, 890, 20, 610, 500);
            game.batch.draw(mark1Tankside2, 900, 135, 600, 325);
            game.batch.draw(mark1label, 1040, 420, 300, 75);
            game.batch.draw(arrowright2, 1350, 425, 65, 63);
        }
        game.batch.draw(arrowleft2, 975, 425, 58, 58);
        if (Gdx.input.getX() > 975 && Gdx.input.getX() < 1033 && Gdx.input.getY() > 425 && Gdx.input.getY() < 483) {
            game.batch.draw(arrowleft2, 973, 423, 63, 63);
            if (Gdx.input.isTouched()) {
                arrowrleft2chilck = true;
            }
        }
        if (arrowrleft2chilck == true) {
            //remove the mark1tank
            frostTankside2.dispose();
            mark1Tankside2.dispose();
            game.batch.draw(side, 890, 20, 610, 500);
            game.batch.draw(buratinotankside2, 930, 40, 480, 480);
            game.batch.draw(buratinolabel, 1040, 420, 300, 75);
            game.batch.draw(arrowright2, 1350, 425, 65, 63);
        }
        game.batch.draw(choose2, 1120, 32, 150, 75);
        if (Gdx.input.getX() > 1120 && Gdx.input.getX() < 1270 && Gdx.input.getY() > 800 && Gdx.input.getY() < 850) { // check this not working properly
            game.batch.draw(choose2, 1115, 27, 160, 85);
            //when is clicked the green tick appears and stayes there
            if (Gdx.input.isTouched()) {
                greentick2bool = true;
                //game.batch.draw(greentick2, 1240, 75, 50, 50);
                //game.setScreen(new gameIn(game));
                //dispose();
            }
        }
        if (greentick2bool == true) {
            game.batch.draw(greentick2, 1250, 75, 50, 50);
        }





        game.batch.draw(loadgame, 640, 210, 220, 130);
        if (Gdx.input.getX() > 640 && Gdx.input.getX() < 860 && Gdx.input.getY() > 600 && Gdx.input.getY() < 700) {
            game.batch.draw(loadgame, 638, 208, 225, 135);
//            if (Gdx.input.isTouched()) {
//                game.setScreen(new gameIn(game));
//                dispose();
//            }
        }

        game.batch.draw(quit, 650, 80, 200, 100);
        if (Gdx.input.getX() > 650 && Gdx.input.getX() < 800 && Gdx.input.getY() > 750 && Gdx.input.getY() < 800) {
            game.batch.draw(quit, 648, 78, 205, 105);
            if (Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
        }
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(StartScreen.class.getName()).log(PlatformLogger.Level.SEVERE, null, ex);
//        }
        try {
            game.batch.draw(playbutton, 650, 370, 200, 100);
            if (Gdx.input.getX() > 650 && Gdx.input.getX() < 850 && Gdx.input.getY() > 440 && Gdx.input.getY() < 540) {
                game.batch.draw(playbutton, 648, 368, 205, 105);
                if (Gdx.input.isTouched()) {
                    if (greentick1bool == true && greentick2bool == true) {
                        game.setScreen(new gameIn(game));
//                        dispose();
                   }
                    else {
                        throw new Exception();
//
                    }
                    game.setScreen(new gameIn(game));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Tanks Not Selected");
        }



        game.batch.draw(settings, 5, 725, 140, 90);
        if (Gdx.input.getX() > 5 && Gdx.input.getX() < 145 && Gdx.input.getY() > 100 && Gdx.input.getY() < 200) {
            game.batch.draw(settings, 0, 720, 150, 100);
//            if (Gdx.input.isTouched()) {
//                game.setScreen(new gameIn(game));
//            }
        }
        game.batch.end();




    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.batch.dispose();
        logo.dispose();
        side.dispose();
        middle.dispose();
        settings.dispose();
        player1.dispose();
        player2.dispose();
        playbutton.dispose();
        choooseTank.dispose();
        mark1Tank.dispose();
        quit.dispose();
        choose1.dispose();
        choose2.dispose();
        arrowright1.dispose();
        arrowright2.dispose();
        arrowleft1.dispose();
        arrowleft2.dispose();
        frostlabel.dispose();
        mark1label.dispose();
    }
}

