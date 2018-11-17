package kubitz.client.gui;

import kubitz.client.components.Card;
import kubitz.client.components.Grid;

import javax.swing.*;
import java.awt.*;

public class CardUI extends JPanel {

    private Grid card;
    private CubeUI[][] cubes;


    public CardUI( Card card){

        setCard(card);

    }

    public void setCard( Card card){

        removeAll();

        this.card = card.getGrid();
        setLayout( new GridLayout(card.getSize(),card.getSize()));

        cubes = new CubeUI[card.getSize()][card.getSize()];

        for (int i = 0; i < card.getSize();i++){

            for (int j = 0; j< card.getSize(); j++){

                cubes[i][j] = new CubeUI( this.card.getCube(i,j));

                add(cubes[i][j]);
            }
        }

    }




}
