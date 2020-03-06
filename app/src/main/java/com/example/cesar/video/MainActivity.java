package com.example.cesar.video;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{


    //Atributos de la clase MediaPlayer para administrar el archivo de audio
    MediaPlayer mp;
    Button b1; //Referencia de un objeto de la clase Button.
    int posicion = 0; //Un entero donde se almacena la posición actual de reproducción en milisegundos.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {

        }
        else if (id == R.id.nav_gallery)
        {

        }
        else if (id == R.id.nav_slideshow)
        {

        }
        else if (id == R.id.nav_manage)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void destruir() // Método destruir
    {
        if(mp!=null) // Si el objeto de la clase MediaPlayer está creado procede a liberar recursos y llamando al método reléase.
            mp.release();
    }

    public void iniciar(View view) // Método iniciar
    {
        destruir(); //Llama al método destruir
        mp = MediaPlayer.create(this,R.raw.audio); //Objeto de clase MediaPlayer llamando al método créate.
        mp.start();//Llama al método start
        String op=b1.getText().toString();
        if (op.equals("No duplicar audio")) //Verificar si el audio debe ejecutarse en forma consecutiva.
            mp.setLooping(false);
        else
            mp.setLooping(true);
    }

    public void pausar(View view) // Método pausar
    {
        if(mp != null && mp.isPlaying())//Verificar que el objeto de la clase MediaPlayer este creado y en ejecución
        {
            posicion = mp.getCurrentPosition();// Si es positive recuperamos la posición actual de reproducción y se llama al método pausar();
            mp.pause();
        }
    }

    public void continuar(View view) // Método continuar
    {
        if(mp != null && mp.isPlaying()==false) //La propiedad isPlaying retorna false para posicionar en que milisegundo continuar la reproducción;
        {
            mp.seekTo(posicion);
            mp.start();
        }
    }
    public void detener(View view) // Método detener interrumpe a ejecución.
    {
        if(mp != null)//Suspender la
        {
            mp.stop();
            posicion = 0;
        }
    }

    public void indefinite(View view) // Método para reproducción de audio continuo.
    {
        detener(null);
        String op=b1.getText().toString();
        if (op.equals("No duplicar audio "))
            b1.setText("Duplicar audio");
        else
            b1.setText("No duplicar audio");
    }
}




}

