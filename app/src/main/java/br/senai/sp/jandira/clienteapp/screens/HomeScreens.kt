package br.senai.sp.jandira.clienteapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.clienteapp.R
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme

@Composable
fun HomeSreens(modifier: Modifier = Modifier){
    Scaffold(
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            Text(text = "Barra baixo")
        },
        floatingActionButton = {}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            Text(text = "Conteudo")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTiTulo (modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Texto atoa",
                        fontSize = 18.sp
                    )
                    Text(
                        text = "email atoa",
                        fontSize = 16.sp
                    )
                }
                Card(
                    modifier = Modifier
                        .size(60.dp),
                    shape = CircleShape
                ) {
                    Image(
                        painter = painterResource(R.drawable.mim),
                        contentDescription = "foto perfil"
                    )
                }

            }
        }
    )
}

@Preview
@Composable
private fun BarraDeTiTuloPreview() {
    BarraDeTiTulo()
}

@Preview
@Composable
private fun HomeSreensPreview(){
    ClienteAppTheme {
        HomeSreens()
    }
}