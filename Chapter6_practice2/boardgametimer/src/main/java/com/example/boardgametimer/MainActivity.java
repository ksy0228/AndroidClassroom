package com.example.boardgametimer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;
import android.speech.tts.TextToSpeech;
import android.text.NoCopySpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //region 변수 선언
    EditText editTextEnteredSeconds;
    Button btnTimerSwitch, btnReset, btnPauseRestart;
    TextView textViewCopyright;
    CountDownTimer countDownTimer;
    TextToSpeech textToSpeech;

    long fullTime = 40, halfTime = fullTime / 2;
    boolean isPaused = false; //pause / restart 버튼 상태
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region 참조 변수에 UI 객체 연결
        editTextEnteredSeconds = findViewById(R.id.editTextEnteredSeconds);
        btnTimerSwitch = findViewById(R.id.btnTimerSwitch);
        btnReset = findViewById(R.id.btnReset);
        btnPauseRestart = findViewById(R.id.btnPauseRestart);
        textViewCopyright = findViewById(R.id.textViewCopyright);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.ENGLISH);
                }
            }
        });
        //endregion

        //region btnTimerSwitch 클릭 처리
        btnTimerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer.create(getApplicationContext(), R.raw.bell_sound).start();

                cancelTimer();
                resetTimer();
                //reset countDownTimer

                countDownTimer = countDownTimer(fullTime);
                countDownTimer.start();
            }
        });
        //endregion

        //region btnReset 버튼 클릭 이벤트
        btnReset.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //editText Focus 없애기, 키보드 숨기기
                if (editTextEnteredSeconds.hasFocus()) {
                    editTextEnteredSeconds.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextEnteredSeconds.getWindowToken(), 0);
                }

                resetTimer(); //타이머 초기화
                cancelTimer(); //타이머 멈추기

                String msg = String.format("Reset to %d seconds", fullTime);
                textToSpeech.speak(msg, TextToSpeech.QUEUE_FLUSH, null);

                btnPauseRestart.setEnabled(true);
                return false;
            }
        });
        //endregion

        //region btnPauseRestart 버튼 클릭 이벤트
        btnPauseRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPaused) {
                    textToSpeech.speak("restart", TextToSpeech.QUEUE_FLUSH, null);
                    btnPauseRestart.setText(R.string.pause);
                    countDownTimer = countDownTimer(Long.parseLong(btnTimerSwitch.getText().toString()));
                    countDownTimer.start();
                    isPaused = false;
                } else {
                    textToSpeech.speak("pause", TextToSpeech.QUEUE_FLUSH, null);
                    countDownTimer.cancel(); //타이머 멈추기
                    btnPauseRestart.setText(R.string.restart);
                    isPaused = true;
                }
            }
        });
        //endregion

    }

    //기존에 실행되던 CountDownTimer 멈추기
    private void cancelTimer() {
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    //CountDownTimer 생성 및 반환
    private CountDownTimer countDownTimer(long t) {
        return new CountDownTimer(t * 1000 + 1000, 1000) {
            @Override
            public void onTick(long l) {
                long currentTime = l / 1000;
                changeTextSize(currentTime);

                if (currentTime == halfTime) {
                    MediaPlayer.create(getApplicationContext(), R.raw.warning_sound).start();
                }
                if (currentTime <= 10) {
                    btnTimerSwitch.setTextColor(Color.RED);
                    textToSpeech.speak(String.valueOf(currentTime), TextToSpeech.QUEUE_FLUSH, null);
                }

                btnTimerSwitch.setText(String.valueOf(currentTime));
            }

            @Override
            public void onFinish() {
                btnTimerSwitch.setBackgroundColor(Color.DKGRAY);
                btnTimerSwitch.setTextColor(Color.GRAY);
                MediaPlayer.create(getApplicationContext(), R.raw.gameover_sound).start();
                btnTimerSwitch.setEnabled(false); //버튼 비활성화
            }
        };
    }

    //타이머 리셋 메소드
    private void resetTimer() {
        //잘못된 입력값 처리
        if (editTextEnteredSeconds.getText().equals("")) {
            Toast.makeText(MainActivity.this, "입력값이 없습니다.", Toast.LENGTH_SHORT).show();
            editTextEnteredSeconds.setText(String.valueOf(fullTime));
            return;
        }

        fullTime = Long.parseLong(editTextEnteredSeconds.getText().toString());
        halfTime = Math.round(fullTime / 2);

        changeTextSize(fullTime);

        btnTimerSwitch.setBackgroundColor(Color.YELLOW);
        btnTimerSwitch.setTextColor(Color.BLACK);
        btnTimerSwitch.setText(String.valueOf(fullTime));
    }

    //숫자(초) 크기에 따른 텍스트 크기 조정
    private void changeTextSize(long seconds) {
        if (seconds >= 100) {
            btnTimerSwitch.setTextSize(200);
        } else if (seconds >= 10) {
            btnTimerSwitch.setTextSize(300);
        } else {
            btnTimerSwitch.setTextSize(400);
        }
    }
}
