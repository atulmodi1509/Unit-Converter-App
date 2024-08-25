package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.math.RoundingMode
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        UnitConverter(modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp))
                    }
                )
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier)
{
    //variables declaration:
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember {mutableStateOf("") }
    var inputUnit by remember {mutableStateOf("Meter")}
    var outputUnit by remember {mutableStateOf("Meter")}
    var iMenuState by remember {mutableStateOf(false)}
    var oMenuState by remember {mutableStateOf(false)}
    val iConversionFactor = remember {mutableStateOf(1.00)}
    val oConversionFactor = remember {mutableStateOf(1.00)}

    fun calculate()
    {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.00  // ?: -> elvis operator kind of if statement
//        val result = (((inputValueDouble * iConversionFactor.value * 100.00) / oConversionFactor.value)).roundToInt() / 100.00
        val result = (inputValueDouble.toBigDecimal() * iConversionFactor.value.toBigDecimal() / oConversionFactor.value.toBigDecimal()).setScale(3, RoundingMode.HALF_UP)
        outputValue = result.toString()

    }


    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        // here all the things gets stacked on each other
        Text(text = "Unit Converter" , style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                calculate()
            },
            label = {Text(text = "Enter value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Row()
        {

            //input Box
            Box(modifier = Modifier)
            {
                Button(onClick = { iMenuState = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Down Arrow")
                }

                DropdownMenu(expanded = iMenuState, onDismissRequest = { iMenuState = false }) {

                    DropdownMenuItem(text = { Text(text = "Kilometer") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Kilometer"
                            iConversionFactor.value = 1000.00
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Meter") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Meter"
                            iConversionFactor.value = 1.00
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Centimeter") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Centimeter"
                            iConversionFactor.value = 0.01
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Millimeter") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Millimeter"
                            iConversionFactor.value = 0.0001
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Foot") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Foot"
                            iConversionFactor.value = 0.3048
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Inch") },
                        onClick = {
                            iMenuState = false
                            inputUnit = "Inch"
                            iConversionFactor.value = 0.0254
                            calculate()
                        })

                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Box
            Box(modifier = Modifier)
            {
                Button(onClick = { oMenuState = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Down Arrow")

                }

                DropdownMenu(expanded = oMenuState, onDismissRequest = { oMenuState = false }) {

                    DropdownMenuItem(text = { Text(text = "Kilometer") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Kilometer"
                            oConversionFactor.value = 1000.00
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Meter") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Meter"
                            oConversionFactor.value = 1.00
                            calculate()
                        })
                    DropdownMenuItem(text = { Text(text = "Centimeter") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Millimeter") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Millimeter"
                            oConversionFactor.value = 0.0001
                            calculate()
                        })
                    DropdownMenuItem(text = { Text(text = "Foot") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Foot"
                            oConversionFactor.value = 0.3048
                            calculate()
                        })

                    DropdownMenuItem(text = { Text(text = "Inch") },
                        onClick = {
                            oMenuState = false
                            outputUnit = "Inch"
                            oConversionFactor.value = 0.0254
                            calculate()
                        })

                }
            }



            

        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Result: $outputValue" , style = MaterialTheme.typography.headlineMedium)

    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview()
{
    UnitConverter(modifier = Modifier)
}


