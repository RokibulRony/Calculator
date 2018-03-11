package com.android.rokibulrony.calculator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private TextView displayPrimary;
    private TextView displaySecondary;
    private HorizontalScrollView hsv;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    int r=0, w=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        setContentView(R.layout.activity_main);
        displayPrimary = (TextView) findViewById(R.id.display_primary);
        displaySecondary = (TextView) findViewById(R.id.display_secondary);
        hsv = (HorizontalScrollView) findViewById(R.id.display_hsv);
        Typeface font = Typeface.createFromAsset(getAssets(), "Monotype Corsiva.ttf");
        displaySecondary.setTypeface(font);
        displayPrimary.setTypeface(font);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.ad_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));


        final TextView[] digits = {
                (TextView) findViewById(R.id.button_0),
                (TextView) findViewById(R.id.button_1),
                (TextView) findViewById(R.id.button_2),
                (TextView) findViewById(R.id.button_3),
                (TextView) findViewById(R.id.button_4),
                (TextView) findViewById(R.id.button_5),
                (TextView) findViewById(R.id.button_6),
                (TextView) findViewById(R.id.button_7),
                (TextView) findViewById(R.id.button_8),
                (TextView) findViewById(R.id.button_9),};
        for (int i = 0; i < digits.length; i++) {
            final String id = (String) digits[i].getText();
            final Typeface font2 = Typeface.createFromAsset(getAssets(), "Monotype Corsiva.ttf");
            digits[i].setTypeface(font2);
            digits[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    calculator.digit(id.charAt(0));
                    w=0;
                }
            });
        }
        final TextView[] buttons = {
                (TextView) findViewById(R.id.button_sin),
                (TextView) findViewById(R.id.button_cos),
                (TextView) findViewById(R.id.button_tan),
                (TextView) findViewById(R.id.btnCot),
                (TextView) findViewById(R.id.button_ln),
                (TextView) findViewById(R.id.btnLg),
                (TextView) findViewById(R.id.button_ib),
                (TextView) findViewById(R.id.button_log),
                (TextView) findViewById(R.id.btnPercent),
                (TextView) findViewById(R.id.button_pi),
                (TextView) findViewById(R.id.btnClear),
                (TextView) findViewById(R.id.button_e),
                (TextView) findViewById(R.id.button_exponent),
                (TextView) findViewById(R.id.btnSquare),
                (TextView) findViewById(R.id.button_answer),
                (TextView) findViewById(R.id.button_square_root),
                (TextView) findViewById(R.id.btnCuberoot),
                (TextView) findViewById(R.id.btnFourthroot),
                (TextView) findViewById(R.id.button_add),
                (TextView) findViewById(R.id.button_subtract),
                (TextView) findViewById(R.id.button_multiply),
                (TextView) findViewById(R.id.button_divide),
                (TextView) findViewById(R.id.button_decimal),
                (TextView) findViewById(R.id.button_equals),
                (TextView) findViewById(R.id.button_delete)};


        for (int i = 0; i < buttons.length; i++) {
            final String id = (String) buttons[i].getText();
            final Typeface font3 = Typeface.createFromAsset(getAssets(), "Monotype Corsiva.ttf");
            buttons[i].setTypeface(font3);
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (id.equals("sin"))
                        calculator.opNum('s');
                    if (id.equals("cos"))
                        calculator.opNum('c');
                    if (id.equals("tan"))
                        calculator.opNum('t');
                    if (id.equals("cot"))
                        calculator.opNum('b');
                    if (id.equals("lb"))
                        calculator.opNum('n');
                    if (id.equals("ln"))
                        calculator.opNum('m');
                    if (id.equals("lg"))
                        calculator.opNum('f');
                    if (id.equals("log"))
                        calculator.opNum('l');
                    if (id.equals("exp"))
                        calculator.opNum('x');
                    if (id.equals("π"))
                        calculator.num('π');
                    if (id.equals("e"))
                        calculator.num('e');
                    if (id.equals("%"))
                        calculator.numOpNum('%');
                    if (id.equals("√"))
                        calculator.opNum('√');
                    if (id.equals("x√y"))
                        calculator.numOpNum('^');
                    if (id.equals("3√"))
                        calculator.opNum('∛');
                    if (id.equals("4√"))
                        calculator.opNum('∜');
                    if (id.equals("/")){
                        if(w==2){
                            calculator.add();
                            w=0;
                        }
                        calculator.numOpNum('/');
                    }
                    if (id.equals("×")) {
                        if(w==2) {
                            calculator.add();
                            w = 0;
                        }
                        calculator.numOpNum('*');
                    }
                    if (id.equals("-")) {
                        if(w==2){
                            calculator.add();
                            w = 0;
                    }
                        calculator.numOpNum('-');
                    }
                    if (id.equals("+")) {
                        if(w==2){
                            w=0;
                            calculator.add();
                        }
                        calculator.numOpNum('+');
                    }
                    if (id.equals("."))
                        calculator.decimal();
            }});
        }
        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.delete();
            }
        });

        findViewById(R.id.button_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getText().equals("")) {
                    calculator.equal();
                    if ((r / 2) == 1) {
                        AdRequest adRequest = new AdRequest.Builder().build();
                        mInterstitialAd.loadAd(adRequest);
                        mInterstitialAd.show();
                        r = 0;
                    } else
                        r++;
                    w =2;
                }
            }
        });
        findViewById(R.id.button_delete).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!displayPrimary.getText().toString().trim().equals("")) {
                    final View displayOverlay = findViewById(R.id.display_overlay);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        Animator circle = ViewAnimationUtils.createCircularReveal(
                                displayOverlay,
                                displayOverlay.getMeasuredWidth() / 2,
                                displayOverlay.getMeasuredHeight(),
                                0,
                                (int) Math.hypot(displayOverlay.getWidth(), displayOverlay.getHeight()));
                        circle.setDuration(300);
                        circle.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                calculator.setText("");
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                calculator.setText("");
                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                            }
                        });
                        ObjectAnimator fade = ObjectAnimator.ofFloat(displayOverlay, "alpha", 0f);
                        fade.setInterpolator(new DecelerateInterpolator());
                        fade.setDuration(200);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playSequentially(circle, fade);
                        displayOverlay.setAlpha(1);
                        animatorSet.start();
                    } else
                        calculator.setText("");
                }
                return false;
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.setText("");

            }
        });
        findViewById(R.id.button_answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.answer();
                calculator.add();
                w=0;
            }
        });

        calculator = new Calculator(this);
        if (savedInstanceState != null)
            setText(savedInstanceState.getString("text"));
        if (sp.getInt("launch_count", 5) == 0) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("launch_count", -1);
            editor.apply();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("text", getText());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setText(savedInstanceState.getString("text"));
    }

    public String getText() {
        return calculator.getText();
    }

    public void setText(String s) {
        calculator.setText(s);
    }

    public void displayPrimaryScrollLeft(String val) {
        displayPrimary.setText(formatToDisplayMode(val));
        ViewTreeObserver vto = hsv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hsv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                hsv.fullScroll(View.FOCUS_LEFT);
            }
        });
    }

    public void displayPrimaryScrollRight(String val) {
        displayPrimary.setText(formatToDisplayMode(val));
        ViewTreeObserver vto = hsv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                hsv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                hsv.fullScroll(View.FOCUS_RIGHT);
            }
        });
    }

    public void displaySecondary(String val) {
        displaySecondary.setText(formatToDisplayMode(val));
    }
    private String formatToDisplayMode(String s) {
        return s.replace("/", "÷").replace("*", "×").replace("-", "−")
                .replace("%", "%").replace("x", "exp")
                .replace("n ", "lb").replace("f ", "lg")
                .replace("l ", "log").replace("√ ", "√")
                .replace("^ ", "√").replace("∛ ", "∛")
                .replace("∜ ", "∜")
                .replace("s ", "sin").replace("c ", "cos")
                .replace("t ", "tan").replace("b ", "cot")
                .replace("∞", "Infinity").replace("m ", "ln")
                .replace("NaN", "Undefined");
    }
}
