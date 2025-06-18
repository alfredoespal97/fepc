package com.alma.fepc.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alma.fepc.R
import com.alma.fepc.ui.components.ImageOption
import com.alma.fepc.ui.components.ImageQuestion

@Composable
fun LessonOneScreen(onCorrectAnswer: () -> Unit) {
    val context = LocalContext.current
    val question = ImageQuestion(
        questionText = "Est-ce que c’est un menu de… tape sur la bonne image",
        options = listOf(
            ImageOption("fruits", R.drawable.fruits),
            ImageOption("légumes", R.drawable.legumes)
        ),
        correctAnswerIndex = 0
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text("Le chef cuisinier écrit une liste des produits pour son menu du jour", fontSize = 18.sp)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        "fraises - bananes - goyaves - noix – kiwis – limes – amandes – raisins – papayes – pêches – tamarins – poires – chérimoles – pommes – cerises – ananas – pamplemousses – corossols – pastèques – dattes – abricots – mûres – zatte – sapote – mangues",
        fontSize = 16.sp
    )
    Spacer(modifier = Modifier.height(24.dp))
    Text(question.questionText, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(16.dp))

    question.options.forEachIndexed { index, option ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable {
                    val isCorrect = index == question.correctAnswerIndex

                    if (isCorrect) {
                        Toast
                            .makeText(context, "Bonne réponse !", Toast.LENGTH_SHORT)
                            .show()
                        onCorrectAnswer()
                    } else {
                        Toast
                            .makeText(context, "Essaie encore !", Toast.LENGTH_SHORT)
                            .show()
                    }
                },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = option.imageResId),
                    contentDescription = option.label,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
                Text(option.label, modifier = Modifier.padding(8.dp))
            }
        }
    }
}
