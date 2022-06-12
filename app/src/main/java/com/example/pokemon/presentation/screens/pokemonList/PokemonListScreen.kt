package com.example.pokemon.presentation.screens.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.pokemon.R
import com.example.pokemon.domain.models.Pokemon
import com.example.pokemon.ui.theme.RobotoCondensed
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination(start = true)
@Composable
fun PokemonListScreen(
    navigator: DestinationsNavigator
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.pokelogo),
                contentDescription = "pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navigator = navigator)
        }

    }

}

@Composable
fun PokemonList(
    navigator: DestinationsNavigator,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val pokemonListState = viewModel.pokemonList.value

    Timber.d("UI data: ${pokemonListState.pokemonList}")


    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(pokemonListState.pokemonList) { pokemon ->
            PokemonEntry(entry = pokemon, navigator = navigator)
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (pokemonListState.isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (pokemonListState.loadError != null) {
            RetrySection(error = pokemonListState.loadError) {
                viewModel.loadPokemon()
            }
        }
    }
}

@Composable
fun PokemonEntry(
    entry: Pokemon,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,

    ) {


    Card(
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Box(
            contentAlignment = Center,
            modifier = modifier
                .shadow(5.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                }
        ) {
            Column {

                Image(
                    painter = rememberImagePainter(
                        data = entry.imageUrl,
                        builder = {
                            placeholder(R.drawable.ic_launcher_background)
                            crossfade(true)
                        }
                    ),
                    modifier = Modifier
                        .size(100.dp)
                        .align(CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    contentDescription = entry.pokemonName
                )
                Text(
                    text = entry.pokemonName,
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = RobotoCondensed,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }


}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}
