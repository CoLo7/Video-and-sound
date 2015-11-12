package ctec.myapplication;

import android.app.Activity;
import android.widget.MediaController;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.net.Uri;
/**
 * Created by gagu5735 on 11/10/15.
 */
public class VideoActivity extends Activity
    {
        private VideoView myPlayer;
        private Button returnButton;
        private MediaController myVideoController;
        private Uri videoLocation;

    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_video);

            myPlayer = (VideoView) findViewById(R.id.videoView);
            returnButton = (Button) findViewById(R.id.homeButton);

            videoLocation = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.funny);
            myVideoController = new MediaController(this);
            //Prepare the Video
            setupMedia();
            setupListeners();
        }

        private void setupMedia()
            {
                myPlayer.setMediaController(myVideoController);
                myPlayer.setVideoURI(videoLocation);

            }

        private void setupListeners()
            {
                returnButton.setOnClickListener(new View.OnClickListener()
                {
                public void onClick(View currentView)
                    {
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                    }
                });
            }
    }
