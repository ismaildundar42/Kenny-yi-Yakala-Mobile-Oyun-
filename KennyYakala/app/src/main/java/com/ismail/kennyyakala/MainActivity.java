package com.ismail.kennyyakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    Runnable runnable;
    TextView scoreText;
    TextView sureText;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageViewArray[];
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



         scoreText = findViewById(R.id.ScoreText);
         sureText= findViewById(R.id.SureText);

         imageView1 = findViewById(R.id.imageView1);
         imageView2=findViewById(R.id.imageView2);
         imageView3=findViewById(R.id.imageView3);
         imageView4=findViewById(R.id.imageView4);
         imageView5=findViewById(R.id.imageView5);
         imageView6=findViewById(R.id.imageView6);
         imageView7=findViewById(R.id.imageView7);
         imageView8=findViewById(R.id.imageView8);
         imageView9=findViewById(R.id.imageView9);

         imageViewArray = new ImageView[]{
                 imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9
         };
        resimSakla();
         score = 0;

         new CountDownTimer(20000,1000)
         {
             @Override
             public void onTick(long l) {
                 sureText.setText("Kalan Süre : "+ l/1000);
             }

             @Override
             public void onFinish() {
                 sureText.setText("Süre bitti");
                 handler.removeCallbacks(runnable);

                 for(ImageView image:imageViewArray)
                 {
                     image.setVisibility(View.INVISIBLE);
                 }
                 AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                 alert.setTitle("Devam mı?");
                 alert.setMessage("Gerçekten devam etmek istiyor musun ?");
                 alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                         // restart
                         Intent intent = getIntent();
                         finish();
                         startActivity(intent);

                     }
                 });
                 alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(MainActivity.this,"Oyun Bitti !",Toast.LENGTH_LONG).show();
                     }
                 });
                 alert.show();
             }
         }.start();

    }
    public void SkorArttır(View view)
    {
        score++;
        scoreText.setText("Skor : "+score);
    }
    public void resimSakla()
    {

       handler = new Handler();

       runnable = new Runnable() {
           @Override
           public void run() {

               for(ImageView image:imageViewArray)
               {
                   image.setVisibility(View.INVISIBLE);
               }
               Random rnd = new Random();
               int i = rnd.nextInt(9);
               imageViewArray[i].setVisibility(View.VISIBLE);

               handler.postDelayed(this,500);
           }
       };
       handler.post(runnable);
    }
}