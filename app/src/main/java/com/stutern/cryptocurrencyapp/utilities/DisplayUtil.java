package com.stutern.cryptocurrencyapp.utilities;

import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.stutern.cryptocurrencyapp.R;

public class DisplayUtil {


    public static void displayArrow(ImageView imageViewArrow, String arrowCondition) {
        double arrowConditionDouble = Double.valueOf(arrowCondition);
        if (arrowConditionDouble < 0) {
            imageViewArrow.setImageResource(R.drawable.ic_arrow_downward_black_24dp);
            imageViewArrow.setColorFilter(ContextCompat
                    .getColor(imageViewArrow.getContext(), R.color.colorArrowDown));
        }
        else {
            imageViewArrow.setImageResource(R.drawable.ic_arrow_upward_black_24dp);
            imageViewArrow.setColorFilter(ContextCompat
                    .getColor(imageViewArrow.getContext(), R.color.colorArrowUp));
        }
    }

    public static void displayTextDrawable(ImageView imageViewSymbol, String text) {
        float density = imageViewSymbol.getContext().getResources().getDisplayMetrics().density;

        ColorGenerator colorGenerator = ColorGenerator.MATERIAL;
        int color = colorGenerator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(StringUtil.threeCharSymbol(text), color,5 * (int) density);

        imageViewSymbol.setImageDrawable(drawable);
    }
}
