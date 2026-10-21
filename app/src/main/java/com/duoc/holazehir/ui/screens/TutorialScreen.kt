package com.duoc.holazehir.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.duoc.holazehir.R
import com.duoc.holazehir.model.TutorialUiState
import com.duoc.holazehir.ui.theme.MiAppTheme
import com.duoc.holazehir.utils.VibrationUtils
import com.duoc.holazehir.viewmodel.MainViewModel
import com.duoc.holazehir.viewmodel.TutorialViewModel


@Suppress("UNUSED_PARAMETER")
@Composable
fun TutorialScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    tutorialViewModel: TutorialViewModel
) {
    val estado by tutorialViewModel.estado.collectAsState()

    TutorialScreenContent(
        estado = estado,
        onPalabraChange = { tutorialViewModel.onPalabraChange(it) },
        onComprobar = { tutorialViewModel.comprobar() },
        onBackClick = { viewModel.navigateBack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreenContent(
    estado: TutorialUiState,
    onPalabraChange: (String) -> Unit,
    onComprobar: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current


    // Efecto para la vibración háptica
    LaunchedEffect(estado.esCorrecto) {
        when (estado.esCorrecto) {
            true -> VibrationUtils.vibrarAcierto(context)
            false -> VibrationUtils.vibrarError(context)
            null -> { /* Estado inicial */
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial - Lección 1") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                windowInsets = WindowInsets(0.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "¿Cómo se dice 'Auto' en turco?",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )


            // Imagen del auto devuelta a la vista
            Image(
                painter = painterResource(id = R.drawable.auto),
                contentDescription = "Imagen de un auto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Fit
            )


            OutlinedTextField(
                value = estado.palabra,
                onValueChange = onPalabraChange,
                label = { Text("Escribe la palabra") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            Button(
                onClick = onComprobar,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                Text(text = "Comprobar", fontSize = 18.sp)
            }


            estado.mensajeFeedback?.let { mensaje ->
                val colorTexto =
                    if (estado.esCorrecto == true) Color(0xFF2E7D32) else Color(0xFFC62828)

                Text(
                    text = mensaje,
                    color = colorTexto,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(height = 30.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TutorialScreenPreview() {
    MiAppTheme {
        TutorialScreenContent(
            estado = TutorialUiState(palabra = "araba"),
            onPalabraChange = {},
            onComprobar = {},
            onBackClick = {}
        )
    }
}
