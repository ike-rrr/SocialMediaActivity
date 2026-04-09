package com.iker.actaprenentatge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Capçalera del perfil
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imatge de perfil real (perf.jpg)
                Image(
                    painter = painterResource(id = R.drawable.perf),
                    contentDescription = "Imatge de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Nom
                Text(
                    text = "Sapphire Star",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Descripció
                Text(
                    text = "Monster Hunter and Member of The Fifth Fleet",
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Estadístiques
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem("142", "Posts")
                    StatItem("700k", "Seguidors")
                    StatItem("385", "Seguint")
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Botó editar perfil
                Button(
                    onClick = { /* No funcional */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Editar perfil")
                }
            }
        }

        // Línia separadora
        item {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Títol de la secció de publicacions
        item {
            Text(
                text = "Publicacions",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Llista de publicacions (10 items, repetint les fotos first, second, third)
        items(3) { index ->
            val imageRes = when (index % 3) {
                0 -> R.drawable.first
                1 -> R.drawable.second
                else -> R.drawable.third
            }
            PostItem(index, imageRes)
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}

@Composable
fun PostItem(index: Int, imageRes: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Imatge del post real
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Post image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descripció de la publicació
        val caption = when (index % 3) {
            0 -> "Welcome to the new world!"
            1 -> "He tried to steal our hunt."
            else -> "I think we might be cooked."
        }
        Text(
            text = "Publicació #${index + 1}. $caption",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Fila d'accions (Likes i Comentaris)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "M'agrada",
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
            Text("${125 + index}", modifier = Modifier.padding(start = 4.dp, end = 16.dp), fontSize = 14.sp)

            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Comentaris",
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Text("${12 + index}", modifier = Modifier.padding(start = 4.dp), fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Línia separadora entre posts
        HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFEEEEEE))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}
