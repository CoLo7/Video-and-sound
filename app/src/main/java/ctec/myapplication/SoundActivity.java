package ctec.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SoundActivity extends Activity implements Runnable
    {
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    private Button videoButton;
    private MediaPlayer soundPlayer;
    private SeekBar soundSeekBar;
    private Thread soundThread;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        stopButton = (Button) findViewById(R.id.stopButton);
        startButton = (Button) findViewById(R.id.playButton);
        pauseButton = (Button) findViewById(R.id.pauseButton);
        videoButton = (Button) findViewById(R.id.videoButton);
        soundSeekBar = (SeekBar) findViewById(R.id.soundSeekBar);
        soundPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.boom);

        setupListeners();

        soundThread = new Thread(this);
        soundThread.start();

        }

    private void setupListeners()
        {
        startButton.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View v)
            {
            soundPlayer.start();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View v)
            {
            soundPlayer.pause();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View v)
            {
            soundPlayer.stop();
            soundPlayer = MediaPlayer.create(getBaseContext(), R.raw.boom);
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener()
        {
        public void onClick(View currentView)
            {
            Intent myIntent = new Intent(currentView.getContext(), VideoActivity.class);
            startActivityForResult(myIntent, 0);
            }
        });
        soundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
        public void onStopTrackingTouch(SeekBar seekBar)
            {
            }

        public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
            if (fromUser)
                {
                soundPlayer.seekTo(progress);
                }
            }
        });
        }

    public void run()
        {
        int currentPosition = 0;
        int soundTotal = soundPlayer.getDuration();
        soundSeekBar.setMax(soundTotal);

        while (soundPlayer != null && currentPosition < soundTotal)
            {
            try
                {
                Thread.sleep(300);
                currentPosition = soundPlayer.getCurrentPosition();
                } catch (InterruptedException soundException)
                {
                return;
                } catch (Exception otherException)
                {
                return;
                }
            soundSeekBar.setProgress(currentPosition);
            }
        }
    }



