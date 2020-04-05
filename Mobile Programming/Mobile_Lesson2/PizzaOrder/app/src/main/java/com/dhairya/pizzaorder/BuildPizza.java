package com.dhairya.pizzaorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuildPizza extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 10;
    final int OLIVES_PRICE = 2;
    final int ONIONS_PRICE = 3;
    final int PINEAPPLE_PRICE= 2;
    final int SPICES_PRICE = 4;
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildpizza);

        Button Summary  = (Button)findViewById(R.id.summary);
        Summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSummary(view);
            }
        });
    }

    public void sendSummary(View view){
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        final String userInputName = userInputNameView.getText().toString();


        CheckBox Olives = (CheckBox) findViewById(R.id.Olives_checked);
        boolean hasOlives = Olives.isChecked();


        CheckBox Onions = (CheckBox) findViewById(R.id.Onions_checked);
        boolean hasOnions = Onions.isChecked();

        CheckBox spices = (CheckBox)findViewById(R.id.mixed_spices);
        boolean has_spices = spices.isChecked();

        CheckBox Pineapple = (CheckBox)findViewById(R.id.Pineapple);
        boolean has_Pineapple = Pineapple.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(hasOnions, hasOlives,has_spices ,has_Pineapple);

        // create and store the order summary
        final String orderSummaryMessage = createOrderSummary(userInputName, hasOlives,hasOnions,has_spices, has_Pineapple, totalPrice);
        Intent intent = new Intent(BuildPizza.this,Order.class);
        intent.putExtra("NAME",userInputName);
        intent.putExtra("SUMMARY",orderSummaryMessage);
        startActivity(intent);

    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasOlives,boolean hasPineapple,boolean hasSpices, boolean hasOnions, float price) {
        String orderSummaryMessage ="Order By: " + userInputName + "\n\n" + "Thank You for the Order, You Have Selected these items \n\n"+
                getString(R.string.order_summary_Olives,boolToString(hasOlives)) + "\n" +
                getString(R.string.order_summary_Pineapple, boolToString(hasPineapple)) + "\n" +
                getString(R.string.order_summary_Onions, boolToString(hasOnions)) + "\n" +
                getString(R.string.order_summary_spices, boolToString(hasSpices)) + "\n\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, calculatePrice(hasOlives,hasOnions,hasPineapple,hasSpices)) + "\n" +
                getString(R.string.thank_you)+"\n\n"+"Note: Pizza= $10, Olives = $2, Onions = $3, Pineapple = $2, mixed spices= $4";
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasOlives, boolean hasOnions,boolean hasPineapple, boolean hasSpices) {
        int basePrice = PIZZA_PRICE;
        if (hasOlives) {
            basePrice += OLIVES_PRICE;
        }
        if (hasOnions) {
            basePrice += ONIONS_PRICE;
        }
        if(hasPineapple){
            basePrice+=PINEAPPLE_PRICE;
        }
        if(hasSpices){
            basePrice+=SPICES_PRICE;
        }
        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one small pizza");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**

     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one small pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
