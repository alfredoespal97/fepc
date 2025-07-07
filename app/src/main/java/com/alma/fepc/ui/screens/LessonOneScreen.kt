package com.alma.fepc.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alma.fepc.R
import com.alma.fepc.ui.components.ImageOption
import com.alma.fepc.ui.components.ImageQuestion

@Composable
fun LessonOneScreen(onCorrectAnswer: () -> Unit) {
    val context = LocalContext.current

    val question = ImageQuestion(
        questionText = "❓ Est-ce que c’est un menu de… Tape sur la bonne image 👇",
        options = listOf(
            ImageOption("🍇 Fruits", R.drawable.fruits),
            ImageOption("🥦 Légumes", R.drawable.legumes)
        ),
        correctAnswerIndex = 0
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFF8E1), Color(0xFFFFECB3))))
            .padding(16.dp)
    ) {
        Text(
            text = "🍓 Leçon 1: Épeler les noms des fruits",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4E342E)
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "👨‍🍳 Le chef cuisinier écrit une liste des produits pour son menu du jour.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "📜 fraises - bananes - goyaves - noix – kiwis – limes – amandes – raisins – papayes – pêches – tamarins – poires – chérimoles – pommes – cerises – ananas – pamplemousses – corossols – pastèques – dattes – abricots – mûres – zatte – sapote – mangues",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = question.questionText,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            question.options.forEachIndexed { index, option ->
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .padding(vertical = 8.dp)
                        .clickable {
                            val isCorrect = index == question.correctAnswerIndex
                            if (isCorrect) {
                                Toast
                                    .makeText(context, "✅ Bonne réponse !", Toast.LENGTH_SHORT)
                                    .show()
                                onCorrectAnswer()
                            } else {
                                Toast
                                    .makeText(context, "❌ Essaie encore !", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE082)),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Image(
                            painter = painterResource(id = option.imageResId),
                            contentDescription = option.label,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = option.label,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun LessonOneScreenPreview() {
    LessonOneScreen(onCorrectAnswer = {})
}

