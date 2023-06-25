package com.smartdev.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartdev.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipCalculatorLayout()
                }
            }
        }
    }
}

@Composable
fun TipCalculatorLayout() {

    var txtAmount by remember { mutableStateOf("") }
    var txtpercentage by remember { mutableStateOf("") }

   var amount=txtAmount.toDoubleOrNull()?:0.0
   var percent=txtpercentage.toDoubleOrNull()?:0.0

    var tip= calculateTip(amount,percent,false)
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

       Text(text = "Calculator", modifier = Modifier.padding(bottom = 20.dp))

       TextField(

           value =txtAmount ,
           modifier = Modifier.fillMaxWidth()
               .padding(bottom = 20.dp),
           label = { Text(text = "Enter amount value")},
           onValueChange ={ txtAmount = it},
               keyboardOptions = KeyboardOptions.Default.copy(
               keyboardType = KeyboardType.Number,
           imeAction = ImeAction.Next
       )
       )

        TextField(
            value =txtpercentage ,
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Enter percentage value")},
            onValueChange ={ txtpercentage = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        Text(text = "Tip Amount : $tip",
        fontSize = 20.sp,

            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(14.dp).align(Alignment.CenterHorizontally)
            )


    }
}

private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if (roundUp)
        tip = kotlin.math.ceil(tip)
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipCalculator() {
    TipCalculatorTheme {
        TipCalculatorLayout()
    }
}