package com.iqrasys.pamcity.utility;

import android.annotation.SuppressLint;
import android.view.Gravity;

import com.iqrasys.pamcity.R;

import de.keyboardsurfer.android.widget.crouton.Style;

@SuppressLint("ResourceAsColor")
public class DesignUtility {
	public static final int AlertRed = R.color.rojo_vivo;
	public static final int WarnOrange= R.color.naranja_resplandeciente;
	public static final int ConfirmGreen = R.color.verde_lima;
	public static final int InfoYellow = R.color.amarillo_canario;
	
	public static Style getStyle() {
		
		return  new Style.Builder()
        
        .setBackgroundColor(AlertRed)
        .setGravity(Gravity.CENTER)
        .setHeight(100)
        .setTextSize(16)
        .setImageResource(R.drawable.cat3)
        .build();
	}
}
