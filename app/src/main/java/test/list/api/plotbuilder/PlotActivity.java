package test.list.api.plotbuilder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

import test.list.api.R;

public class PlotActivity extends Activity {

    private EditText formula;
    private GraphicView graphic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot);
        //
        formula = (EditText) findViewById(R.id.edit_text_formula);
        graphic = (GraphicView) findViewById(R.id.view_graphic);
    }

    public void drawGraphic(View view) {
        String t = formula.getText().toString();
        String s1 = t.replaceAll("\\s+", "").toLowerCase(Locale.getDefault());
        if (!s1.matches("-?\\d*?x([-+]\\d+?)?")) {
            String mes = getString(R.string.formula_validation_message);
            ForegroundColorSpan spanColor = new ForegroundColorSpan(Color.RED);
            SpannableStringBuilder builder = new SpannableStringBuilder(mes);
            builder.setSpan(spanColor, 0, mes.length(), 0);
            formula.setError(builder);
            return;
        }

        String s = s1.replaceAll("\\+", "");
        int xPos = s.indexOf("x");
        // parse "k" from formula
        String sk = s.substring(0, xPos);
        int k;
        if (sk.length() != 0) {
            if ("-".equals(sk)) {
                k = -1;
            } else {
                k = Integer.parseInt(sk);
            }
        } else {
            k = 1;
        }
        // parse "b" from formula
        String sb = s.substring(xPos + 1);
        int b = (sb.length() == 0) ? 0 : Integer.parseInt(sb);

        graphic.setFormula(k, b);
    }
}
