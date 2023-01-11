package com.ibrahimkiceci.runawaybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class RunAwayBird extends ApplicationAdapter {

	SpriteBatch batch; // to create the object, it is necessary
	Texture background;
	Texture bird;
	Texture ufo1;
	Texture ufo2;
	Texture ufo3;
	float birdX = 0;
	float birdY = 0 ;
	int gameState = 0;
	float velocity = 0;
	float gravity = 0.7f;
	Random random;


	int enemies_number = 4;
	float[] enemyX = new float[enemies_number]; // X axis of ufos;
	float[] enemyOffSet = new float[enemies_number];
	float[] enemyOffSet2 = new float[enemies_number];
	float[] enemyOffSet3 = new float[enemies_number];

	float distance = 0;  // distance of enemies on the screen clearly, distance between one set of three and the other set of three
	float enemyVelocity = 2;








	
	@Override
	public void create () {

		// Here is the same as on create so when game is open what happen we are writing in here

		batch = new SpriteBatch();
		background = new Texture("bg.png");
		bird = new Texture("bird.png");
		ufo1 = new Texture("ufo.png");
		ufo2 = new Texture("ufo.png");
		ufo3 = new Texture("ufo.png");
		distance = Gdx.graphics.getWidth()/2; // it provides distance (the measure is half of screen) for each set ufo;
		random = new Random();


		birdX = Gdx.graphics.getWidth()/2 - bird.getHeight()/2;
		birdY = Gdx.graphics.getHeight()/3;


		for (int i = 0; i<enemies_number; i++) {

			enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
			enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
			enemyOffSet3[i] = (random.nextFloat() -0.5f) * (Gdx.graphics.getHeight()-200);


			enemyX[i] = Gdx.graphics.getWidth() - ufo1.getWidth()/2 + i * distance;


		}





	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		//you write the code here for things you want to be in the game constantly.
		// For instance flying birds

		if (gameState == 1){

			if (Gdx.input.justTouched()){
				velocity = -18;

			}
			for (int i = 0; i < enemies_number; i++){
				if (enemyX[i]<0){
					enemyX[i] = enemyX[i] +enemies_number*distance;
					enemyOffSet[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet2[i] = (random.nextFloat() - 0.5f) * (Gdx.graphics.getHeight()-200);
					enemyOffSet3[i] = (random.nextFloat() -0.5f) * (Gdx.graphics.getHeight()-200);


				}else{
					enemyX[i] = enemyX[i] - enemyVelocity ;
				}

				batch.draw(ufo1, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet[i], Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/10);
				batch.draw(ufo2, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet2[i], Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/10);
				batch.draw(ufo3, enemyX[i], Gdx.graphics.getHeight()/2 + enemyOffSet3[i], Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/10);
			}


			if (birdY > 0 || velocity < 0){
				velocity = velocity + gravity;
				birdY = birdY - velocity;
			}


		}else{

			if (Gdx.input.justTouched()){
				gameState = 1;
			}

		}


		batch.draw(bird,birdX,birdY,Gdx.graphics.getWidth()/15, Gdx.graphics.getHeight()/10 );



		batch.end();




	}
	
	@Override
	public void dispose () {

	}
}
