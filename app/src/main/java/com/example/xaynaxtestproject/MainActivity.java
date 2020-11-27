package com.example.xaynaxtestproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.graphics.Paint.STRIKE_THRU_TEXT_FLAG;

public class MainActivity extends AppCompatActivity {
    private ImageView more,search,chabron;
    private TextView previousAmount;
   // private ImageView productOne,productTwo,productThree,productFour,productFive;
    private BottomSheetDialog bottomSheetDialog;
    LinearLayout productLayout;


    ArrayAdapter<String> adapter;
    Spinner sp;
    String[] currency = {"Cost: BDT. 4000","लागत:  ₹ . 4000","لاگت: پی کے آر۔ 4000"};

    int[] horof = {
            R.drawable.productioptionone,
            R.drawable.productoptiontwo,
            R.drawable.style1,
            R.drawable.style2,
            R.drawable.style3,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        StrikTrhouthSet();

        openBottomSheet();

        sp = findViewById(R.id.homeSpinnerId);
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,currency);
        sp.setAdapter(adapter);

        more = findViewById(R.id.more);
        search = findViewById(R.id.search);
        chabron = findViewById(R.id.backCabron);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    more.setVisibility(View.VISIBLE);
                    search.setVisibility(View.VISIBLE);
                    chabron.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if(isShow) {
                    more.setVisibility(View.INVISIBLE);
                    search.setVisibility(View.INVISIBLE);
                    chabron.setVisibility(View.INVISIBLE);
                    isShow = false;
                }
            }
        });



    }

    private void openBottomSheet() {

        productLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] position = {0};
                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogtheme);

                final View[] sheetView = {LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
                        (ViewGroup) findViewById(R.id.layoutBottomSheet))};


                   final ImageSwitcher imageSwitcher = sheetView[0].findViewById(R.id.imageSwitcher);

                        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
                    @Override
                    public View makeView() {
                        ImageView imageView = new ImageView(MainActivity.this);
                        imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT));
                        imageSwitcher.setBackgroundResource(R.drawable.productioptionone);
                        return imageView;
                    }
                });





                sheetView[0].findViewById(R.id.backSlide).setVisibility(View.INVISIBLE);

                sheetView[0].findViewById(R.id.nextSlide).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(position[0] <horof.length-1){

                            position[0] = position[0] +1;
                            imageSwitcher.setBackgroundResource(horof[position[0]]);

                            if (position[0] ==4){
                                sheetView[0].findViewById(R.id.nextSlide).setVisibility(View.INVISIBLE);

                            }else {
                                sheetView[0].findViewById(R.id.backSlide).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });


                sheetView[0].findViewById(R.id.backSlide).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if((position[0] <=horof.length-1) && (position[0] >0)){

                                position[0] = position[0] -1;
                                imageSwitcher.setBackgroundResource(horof[position[0]]);

                            if (position[0] ==0){

                                sheetView[0].findViewById(R.id.backSlide).setVisibility(View.INVISIBLE);
                            }else {

                                sheetView[0].findViewById(R.id.nextSlide).setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });

                sheetView[0].findViewById(R.id.product_option_one).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        imageSwitcher.setBackgroundResource(horof[0]);
                        sheetView[0].findViewById(R.id.layout1).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.layout2).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout3).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout4).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout5).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                    }
                });

                sheetView[0].findViewById(R.id.product_option_two).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        imageSwitcher.setBackgroundResource(horof[1]);
                        sheetView[0].findViewById(R.id.layout1).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout2).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.layout3).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout4).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout5).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));


                    }
                });

                sheetView[0].findViewById(R.id.product_option_three).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        imageSwitcher.setBackgroundResource(horof[2]);
                        sheetView[0].findViewById(R.id.layout1).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout2).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout3).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.layout4).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout5).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));




                    }
                });
                sheetView[0].findViewById(R.id.product_option_four).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        imageSwitcher.setBackgroundResource(horof[3]);
                        sheetView[0].findViewById(R.id.layout1).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout2).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout3).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout4).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.layout5).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));



                    }
                });

                sheetView[0].findViewById(R.id.product_option_five).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        imageSwitcher.setBackgroundResource(horof[4]);
                        sheetView[0].findViewById(R.id.layout1).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout2).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout3).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout4).setBackground(getResources().getDrawable(R.drawable.product_cardview_blank));
                        sheetView[0].findViewById(R.id.layout5).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                    }
                });


                sheetView[0].findViewById(R.id.size_s).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView[0].findViewById(R.id.size_s).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.size_m).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_x).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_xl).setBackground(getResources().getDrawable(R.drawable.button_design));
                    }
                });


                sheetView[0].findViewById(R.id.size_m).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView[0].findViewById(R.id.size_s).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_m).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.size_x).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_xl).setBackground(getResources().getDrawable(R.drawable.button_design));
                    }
                });
                sheetView[0].findViewById(R.id.size_x).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView[0].findViewById(R.id.size_s).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_m).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_x).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                        sheetView[0].findViewById(R.id.size_xl).setBackground(getResources().getDrawable(R.drawable.button_design));
                    }
                });
                sheetView[0].findViewById(R.id.size_xl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetView[0].findViewById(R.id.size_s).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_m).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_x).setBackground(getResources().getDrawable(R.drawable.button_design));
                        sheetView[0].findViewById(R.id.size_xl).setBackground(getResources().getDrawable(R.drawable.product_card_view));
                    }
                });


                sheetView[0].findViewById(R.id.quantity_decrement).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = sheetView[0].findViewById(R.id.counterTV);
                        int currentNumber = Integer.parseInt((String) textView.getText());

                        if (!textView.equals(null) && currentNumber>1){
                            currentNumber--;
                            String count = String.valueOf(currentNumber);
                            textView.setText(count);
                        }


                    }
                });

                sheetView[0].findViewById(R.id.quantity_increment).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = sheetView[0].findViewById(R.id.counterTV);

                        int currentNumber = Integer.parseInt((String) textView.getText());

                        if (!textView.equals(null)){

                            currentNumber++;
                            String count = String.valueOf(currentNumber);
                            textView.setText(count);
                        }
                    }
                });


                sp = sheetView[0].findViewById(R.id.spinnerId);
                adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_expandable_list_item_1,currency);
                sp.setAdapter(adapter);


                bottomSheetDialog.setContentView(sheetView[0]);
                bottomSheetDialog.show();
            }
        });

//        productTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogtheme);
//
//                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
//                        (ViewGroup) findViewById(R.id.layoutBottomSheet));
//
//
//                bottomSheetDialog.setContentView(sheetView);
//                bottomSheetDialog.show();
//            }
//        });

//        productThree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogtheme);
//
//                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
//                        (ViewGroup) findViewById(R.id.layoutBottomSheet));
//
//
//                bottomSheetDialog.setContentView(sheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        productFour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogtheme);
//
//                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
//                        (ViewGroup) findViewById(R.id.layoutBottomSheet));
//
//
//                bottomSheetDialog.setContentView(sheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        productFive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog = new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogtheme);
//
//                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_bottom_sheet,
//                        (ViewGroup) findViewById(R.id.layoutBottomSheet));
//
//
//                bottomSheetDialog.setContentView(sheetView);
//                bottomSheetDialog.show();
//            }
//        });
    }

    private void init() {
//        productOne = findViewById(R.id.product1);
//        productTwo = findViewById(R.id.product2);
//        productThree = findViewById(R.id.product3);
//        productFour = findViewById(R.id.product4);
//        productFive = findViewById(R.id.product5);

        productLayout = findViewById(R.id.productLayout);
    }

    private void StrikTrhouthSet() {
        previousAmount = findViewById(R.id.previous_ammount);

        SpannableStringBuilder spnBuilder = new SpannableStringBuilder("BDT. 30,50");
        StrikethroughSpan strikSpn = new StrikethroughSpan();
        spnBuilder.setSpan(strikSpn,0,10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        previousAmount.setText(spnBuilder);
    }

}
