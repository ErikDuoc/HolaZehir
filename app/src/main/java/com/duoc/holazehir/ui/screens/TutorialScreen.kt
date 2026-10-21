package com.duoc.holazehir.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import com.duoc.holazehir.R
import com.duoc.holazehir.utils.VibrationUtils
import com.duoc.holazehir.viewmodel.MainViewModel
import com.duoc.holazehir.viewmodel.TutorialViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    tutorialViewModel: TutorialViewModel
) {
    val estado by tutorialViewModel.estado.collectAsState()
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
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
                onValueChange = { tutorialViewModel.onPalabraChange(it) },
                label = { Text("Escribe la palabra") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


            Button(
                onClick = { tutorialViewModel.comprobar() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Comprobar", fontSize = 16.sp)
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
        }
    }
}
