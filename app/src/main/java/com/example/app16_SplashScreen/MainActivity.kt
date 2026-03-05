package com.example.app16_SplashScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.app16_SplashScreen.ui.theme.MyApp_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp_Theme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyExample(modifier = Modifier.padding( innerPadding))
                }
            }
        }
    }
}


@Composable
fun MyExample(modifier: Modifier = Modifier) {
    Column(
        Modifier.fillMaxSize().verticalScroll( rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.padding(10.dp))
        //MyDialog1()

        Spacer(modifier = Modifier.padding(10.dp))
        MyDialog2()

        Spacer(modifier = Modifier.padding(10.dp))
        //MyDialog3()

        Spacer(modifier = Modifier.padding(10.dp))
        MyAlertDialog4()

        Spacer(modifier = Modifier.padding(10.dp))
        Text("--------")
    }
}


@Composable
fun MyDialog1(){
    Text("Dialog bàsic")
    Dialog(onDismissRequest = { }) {
        Column(
            Modifier
                .background(Color.Red)
                .padding(10.dp)
                .fillMaxWidth()) {
            Text(text = "Això és un Dialog bàsic. No té sentit mostrar-lo d'entrada!")
        }
    }
}

@Composable
fun MyDialog2() {
    Text("Button que activa un Dialog")
    Box( contentAlignment = Alignment.Center) {
        var show by remember { mutableStateOf(false) }
        Button(onClick = { show = true }) {
            Text(text = "Show dialog")
        }
        MyDialog21(show, {show=false})
    }
}

@Composable
fun MyDialog21(show: Boolean, onDismiss: () -> Unit){
    if(show){
        Dialog(onDismissRequest = { onDismiss()}) {
            Column(
                Modifier.background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()) {
                Text(text = "Per tancar, pica fora del Dialog o enrera")
            }
        }
    }
}


@Composable
fun MyDialog3() {
    Text("Button que activa un Dialog")
    Box( contentAlignment = Alignment.Center) {
        var show by remember { mutableStateOf(false) }
        Button(onClick = { show = true }) {
            Text(text = "Show dialog")
        }
        MyDialog31(show, {show=false})
    }
}

@Composable
fun MyDialog31(show: Boolean, onDismiss: () -> Unit){
    Text("Button que activa un Dialog que no es pot tancar")
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            Modifier.background(Color.White).padding(24.dp).fillMaxWidth()
        )
        {
            Text(text = "Aquest Dialog no es pot tancar\nRevisa el DialogProperties")
        }
    }
}

@Composable
fun MyAlertDialog4() {
    var result by remember { mutableStateOf("---") }
    var show by remember { mutableStateOf(false) }

    Text("AlertDialog ")
    Box(contentAlignment = Alignment.Center) {
        Button(onClick = { show = true }) {
            Text(text = "Show dialog")
        }
        MyAlertDialog41(show,
                { show = false; result = "Cancel" },
                { show = false; result ="OK" })
    }
    Text("El resultat ha estat: $result")
}


@Composable
fun MyAlertDialog41(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(
            onDismissRequest = {},
            title = { Text("This is an Alert Dialog") },
            text = { Text("Enjoy the creation of this Alert Dialog") },
            confirmButton = {TextButton(onClick = {onConfirm()}) {Text(text = "OK")}},
            dismissButton = {TextButton(onClick = {onDismiss()}) {Text(text = "Cancel")}}
        )
    }
}

