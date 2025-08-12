package br.senai.sp.jandira.clienteapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.clienteapp.R
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import br.senai.sp.jandira.clienteapp.ui.theme.ClienteAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await


@Composable
fun HomeScreens(modifier: Modifier = Modifier){

    val retrofit = RetrofitFactory().getClienteService()

    //Variavel para amazenar a lista de personagens da API
    var clienteList by remember {
        mutableStateOf(listOf<Cliente>())
    }

    //Fazer uma chamadada na API
    LaunchedEffect(Dispatchers.IO) {
        clienteList = retrofit.exibirTodos().await()
    }

    Scaffold(
        topBar = {
            BarraDeTiTulo()
        },
        bottomBar = {
            BarraDeNavegacao()
        },
        floatingActionButton = {
            BotoaoFlutuante()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Icone",
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Lista de clientes"
                )
            }
            LazyColumn {
                items(clienteList){
                    ClienteCard(
                        nome = it.nome,
                        email = it.email,
                    )
                }
            }
        }
    }
}


@Composable
fun ClienteCard (
    id: Long? = null,
    nome: String = "nome",
    email: String = "email"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 4.dp
            ),
        colors = CardDefaults
            .cardColors(
                contentColor = MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = nome,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = email,
                    color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "texto",
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
private fun ClienteCardPreview() {
    ClienteCard()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraDeTiTulo (modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        colors = TopAppBarDefaults
            .topAppBarColors(
                containerColor = MaterialTheme
                    .colorScheme.primary
            ),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Texto atoa",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "email atoa",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimary
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

//@Preview
@Composable
private fun BarraDeTiTuloPreview() {
    BarraDeTiTulo()
}

@Composable
fun BarraDeNavegacao (modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        )
    }
}

//@Preview
@Composable
private fun BarraDeNavegacaoPreview() {
    BarraDeNavegacao()
}

@Composable
fun BotoaoFlutuante (modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão adicionar",
            tint = MaterialTheme.colorScheme.onTertiary
        )
    }
}

//@Preview
@Composable
private fun BotoaoFlutuantePreview() {
    BotoaoFlutuante()
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreensPreview(){
    ClienteAppTheme {
        HomeScreens()
    }
}