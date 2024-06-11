package com.example.mainproject;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Scan extends AppCompatActivity {

    private EditText answerKeyEditText; // EditText for user input
    private SurfaceView surfaceView;
    private TextView tv;
    private CameraSource cameraSource;
    private static final int Permission = 100;

    // List of keywords
    private Set<String> keywords = new HashSet<>(Arrays.asList("Keyword1", "Keyword2", "Keyword3"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        surfaceView = findViewById(R.id.surfaceView);
        tv = findViewById(R.id.script);
        answerKeyEditText = findViewById(R.id.answerKeyEditText);

        startCameraSource();
    }

    private void startCameraSource() {
        final TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("Tag", "Dependencies not loaded yet");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK).setRequestedPreviewSize(1280, 1024)
                    .setAutoFocusEnabled(true).setRequestedFps(2.0f).build();

            surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                    try {
                        // Check CAMERA permission before starting the camera
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(Scan.this, new String[]{android.Manifest.permission.CAMERA}, Permission);
                            return;
                        }
                        cameraSource.start(surfaceView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
                }

                @Override
                public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {}

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0) {
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < items.size(); i++) {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                String detectedText = stringBuilder.toString().trim(); // Trim the detected text

                                // Get the user input from the EditText
                                String userInput = answerKeyEditText.getText().toString().trim();

                                // Check if the user input is not empty
                                if (!userInput.isEmpty()) {
                                    // Check if the detected text contains the user input (case-insensitive)
                                    if (detectedText.toLowerCase().contains(userInput.toLowerCase())) {
                                        tv.setText("Correct:\n" + detectedText);
                                    } else {
                                        tv.setText("Incorrect:\n" + detectedText);
                                    }
                                } else {
                                    // Clear the text view if the user input is empty
                                    tv.setText("");
                                }
                            }
                        });
                    }
                }
            });


        }
    }
}
