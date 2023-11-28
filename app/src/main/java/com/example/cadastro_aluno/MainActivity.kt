package com.example.cadastro_aluno

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.cadastro_aluno.ui.theme.Cadastro_alunoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Cadastro_alunoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var currentScreen by remember { mutableStateOf(Screen.Main) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Formulários") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .clickable {
                                currentScreen = Screen.Main
                            }
                            .padding(8.dp)
                    )
                }
            )
        },
        content = {
            when (currentScreen) {
                Screen.Main -> MainScreen(
                    onNavigateToAlunoForm = { currentScreen = Screen.AlunoForm },
                    onNavigateToCursoForm = { currentScreen = Screen.CursoForm }
                )
                Screen.AlunoForm -> AlunoForm(
                    onNavigateBack = { currentScreen = Screen.Main }
                )
                Screen.CursoForm -> CursoForm(
                    onNavigateBack = { currentScreen = Screen.Main }
                )
            }
        }
    )
}

@Composable
fun MainScreen(
    onNavigateToAlunoForm: () -> Unit,
    onNavigateToCursoForm: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Cadastro de Aluno",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { onNavigateToAlunoForm() }
            )
            Text("Aluno", modifier = Modifier.padding(top = 8.dp))
        }
        Spacer(modifier = Modifier.width(32.dp)) // Adiciona um espaço entre os ícones
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Cadastro de Curso",
                modifier = Modifier
                    .size(100.dp)
                    .clickable { onNavigateToCursoForm() }
            )
            Text("Curso", modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlunoForm(onNavigateBack: () -> Unit) {
    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = idade,
            onValueChange = { idade = it },
            label = { Text("Idade") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Cadastrar Aluno")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Voltar",
            modifier = Modifier
                .size(50.dp)
                .clickable { onNavigateBack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CursoForm(onNavigateBack: () -> Unit) {
    var nomeCurso by remember { mutableStateOf("") }
    var cargaHoraria by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nomeCurso,
            onValueChange = { nomeCurso = it },
            label = { Text("Nome do Curso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = cargaHoraria,
            onValueChange = { cargaHoraria = it },
            label = { Text("Carga Horária") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição do Curso") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Cadastrar Curso")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Voltar",
            modifier = Modifier
                .size(50.dp)
                .clickable { onNavigateBack() }
        )
    }
}

enum class Screen {
    Main,
    AlunoForm,
    CursoForm
}
