package com.alma.fepc.ui.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alma.fepc.R
import com.alma.fepc.ui.components.ImageOption
import com.alma.fepc.ui.components.ImageQuestion

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonFiveScreen(onCorrectAnswer: () -> Unit) {

    // ----- 1. PREGUNTA Y DATOS -------------------------------------------------
    val question = ImageQuestion(
        questionText = "Sélectionne les fruits qui figurent dans le menu puis appuie sur « Vérifier »",
        options = listOf(
            ImageOption("Abricot", R.drawable.abricot),
            ImageOption("Banane", R.drawable.banane),
            ImageOption("Cerise", R.drawable.cerise),
            ImageOption("Datte", R.drawable.datte),
            ImageOption("Figue", R.drawable.epine_vinette),
            ImageOption("Groseille", R.drawable.fraise),
            ImageOption("Framboise", R.drawable.goyave),
            ImageOption("Mangue", R.drawable.icaque),
            ImageOption("Orange", R.drawable.jacque),
            ImageOption("Kiwi", R.drawable.kiwi),
            ImageOption("Pomme", R.drawable.lime),
            ImageOption("Poire", R.drawable.mangue),
            ImageOption("Pêche", R.drawable.noix),
            ImageOption("Raisin", R.drawable.orange),
            ImageOption("Fraise", R.drawable.pasteque),
            ImageOption("Pamplemousse", R.drawable.quetsche),
            ImageOption("Tomate", R.drawable.raisins),          // incorrecta
            ImageOption("Citron", R.drawable.sapote),
            ImageOption("Pastèque", R.drawable.tamarin),
            ImageOption("Ananas", R.drawable.ugli),
            ImageOption("Grenade", R.drawable.vavangue),
            ImageOption("Litchi", R.drawable.wampi),
            ImageOption("Mûre", R.drawable.ximenia),
            ImageOption("Cassis", R.drawable.yuzu),
            ImageOption("Myrtille", R.drawable.zatte)
        ),
        correctAnswerIndex = -1          // ya no lo usamos
    )

    // ----- 2. LISTA VÁLIDA (frutas mencionadas en la Leçon 1) -------------------
    val validFruits = setOf(
        "Abricot", "Ananas", "Banane", "Citron", "Fraise",
        "Groseille", "Kiwi", "Mangue", "Noix", "Orange",
        "Papaye", "Pastèque", "Pêche", "Poire", "Pomme",
        "Raisin", "Tamarin", "Pamplemousse", "Goyave", "Corossol",
        "Datte", "Mûre", "Sapote", "Zatte", "Cerise"
    )

    // ----- 3. ESTADOS -----------------------------------------------------------
    val selected = remember { mutableSetOf<Int>() }          // índices tocados
    val evaluation = remember { mutableStateMapOf<Int, Boolean>() } // resultado tras verificar
    var verified by remember { mutableStateOf(false) }

    // ----- 4. UI ----------------------------------------------------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFF8E1), Color(0xFFFFECB3))))
            .padding(16.dp)
    ) {

        Text(
            text = "🍏 Leçon 2 : L’alphabet des fruits",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color(0xFF4E342E)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = question.questionText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(16.dp))

        // --- GRID DE OPCIONES --------------------------------------------------
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(question.options) { index, option ->

                // Color según estado
                val bgColor = when {
                    !verified && index in selected      -> Color(0xFFFFE082)          // solo seleccionado
                    verified && evaluation[index] == true -> Color(0xFFA5D6A7)        // correcto
                    verified && evaluation[index] == false -> Color(0xFFEF9A9A)       // incorrecto
                    else                                 -> Color(0xFFF5F5F5)          // default
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clickable(enabled = !verified) {    // no se puede tocar después de verificar
                            if (index in selected) selected.remove(index)
                            else selected.add(index)
                        },
                    colors = CardDefaults.cardColors(bgColor),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Image(
                            painter = painterResource(option.imageResId),
                            contentDescription = option.label,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            option.label,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // --- BOTÓN VERIFICAR ---------------------------------------------------
        Button(
            onClick = {
                // Evaluar cada fruta seleccionada
                selected.forEach { idx ->
                    val fruitName = question.options[idx].label
                    evaluation[idx] = fruitName in validFruits
                }
                verified = true

                // ¿Todo correcto?
                val allCorrect = evaluation.values.all { it } && evaluation.size == selected.size
                if (allCorrect) onCorrectAnswer()
            },
            enabled = selected.isNotEmpty() && !verified,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Vérifier")
        }
    }
}



@Preview
@Composable
fun LessonFiveScreenPreview() {
    LessonFiveScreen(onCorrectAnswer = {})
}