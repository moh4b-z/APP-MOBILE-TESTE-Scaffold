package br.senai.sp.jandira.clienteapp.screens

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.senai.sp.jandira.clienteapp.model.Cliente
import br.senai.sp.jandira.clienteapp.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun FormCliente(navController: NavHostController) {
    var nomeCliente by remember {
        mutableStateOf("")
    }
    var emailCliente by remember {
        mutableStateOf("")
    }

    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    val clienteApi = RetrofitFactory().getClienteService()

    fun validar(): Boolean{
        isNomeError = nomeCliente.length < 1
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()
        return !isNomeError && !isEmailError
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = nomeCliente,
            onValueChange = {nome ->
                nomeCliente = nome
            },
            label = {
                Text(
                    text = "Nome do cliente"
                )
            },
            supportingText = {
                if(isNomeError){
                    Text(text = "Nome é obrigatorio")
                }

            },
            trailingIcon = {
                if(isNomeError){
                    Icon(imageVector = Icons.Default.Info, contentDescription = "erro")
                }
            },
            isError = isNomeError,
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = emailCliente,
            onValueChange = {email ->
                emailCliente = email
            },
            label = {
                Text(
                    text = "Email do cliente"
                )
            },
            supportingText = {
                if(isEmailError){
                    Text(text = "Email é obrigatorio")
                }
            },
            trailingIcon = {
                if(isEmailError){
                    Icon(imageVector = Icons.Default.Info, contentDescription = "erro")
                }
            },
            isError = isEmailError,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = {
                if(validar()){
                    // criar cliente
                    val cliente = Cliente(
                        nome = nomeCliente,
                        email = emailCliente
                    )
                    GlobalScope.launch(Dispatchers.IO) {
                        val novoCliente = clienteApi.gravar(cliente).await()
                    }
                }else{
                    println("************** Tudo errado")
                }


            },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Salvar Cliente"
            )
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//private fun FormClientePreview() {
//    FormCliente(navController)
//}