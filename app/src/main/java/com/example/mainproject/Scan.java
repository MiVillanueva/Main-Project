package com.example.mainproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Scan extends AppCompatActivity {

    private EditText answerKeyEditText;
    private SurfaceView surfaceView;
    private TextView tv;
    private CameraSource cameraSource;
    private ImageView capturedImageView;
    private Button captureButton;
    private static final int Permission = 100;

    private Bitmap capturedImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        surfaceView = findViewById(R.id.surfaceView);
        tv = findViewById(R.id.script);
        answerKeyEditText = findViewById(R.id.answerKeyEditText);
        capturedImageView = findViewById(R.id.capturedImageView);
        captureButton = findViewById(R.id.captureButton);

        tv.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

        startCameraSource();

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImageAndProcessText();
            }
        });
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
                public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {}

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
                    // No action needed for real-time text detection here
                }
            });
        }
    }

    private void captureImageAndProcessText() {
        cameraSource.takePicture(null, new CameraSource.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes) {
                capturedImageBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                capturedImageBitmap = rotateImageIfNeeded(capturedImageBitmap);
                capturedImageView.setImageBitmap(capturedImageBitmap);
                processCapturedImage();
            }
        });
    }

    private Bitmap rotateImageIfNeeded(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void processCapturedImage() {
        if (capturedImageBitmap != null) {
            TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
            if (!textRecognizer.isOperational()) {
                Log.w("Tag", "Text recognizer dependencies are not yet available.");
            } else {
                Frame frame = new Frame.Builder().setBitmap(capturedImageBitmap).build();
                SparseArray<TextBlock> items = textRecognizer.detect(frame);

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    TextBlock item = items.valueAt(i);
                    stringBuilder.append(item.getValue());
                    stringBuilder.append("\n");
                }

                String detectedText = stringBuilder.toString().trim();

                if (!detectedText.isEmpty()) {
                    final String userInput = answerKeyEditText.getText().toString().trim();
                    if (!userInput.isEmpty()) {
                        final Set<String> userKeywords = new HashSet<>(Arrays.asList(userInput.split("\\s*,\\s*")));

                        String highlightedText = detectedText;
                        for (String keyword : userKeywords) {
                            highlightedText = highlightedText.replaceAll("(?i)" + keyword, "<font color='green'>" + keyword + "</font>");
                        }

                        final String finalHighlightedText = highlightedText;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(Html.fromHtml(finalHighlightedText + "\n"));
                            }
                        });

                        boolean matchFound = false;
                        for (String keyword : userKeywords) {
                            if (detectedText.toLowerCase().contains(keyword.toLowerCase())) {
                                matchFound = true;
                                break;
                            }
                        }

                        if (matchFound) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.append(Html.fromHtml("\n<font color='green'> \n(Match found for keywords).</font>"));
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv.append(Html.fromHtml("\n<font color='red'> \n(No match found for keywords).</font>"));
                                }
                            });
                        }
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(detectedText);
                            }
                        });
                    }
                }
            }
        }
    }
}
