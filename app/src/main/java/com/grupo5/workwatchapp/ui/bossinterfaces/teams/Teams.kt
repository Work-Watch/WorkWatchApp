package com.grupo5.workwatchapp.ui.bossinterfaces.teams


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.grupo5.workwatchapp.R
import com.grupo5.workwatchapp.network.dto.teams.ExpandableCardItem
import com.grupo5.workwatchapp.network.dto.teams.Team
import com.grupo5.workwatchapp.ui.theme.WorkWatchAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Preview(showSystemUi = true)
@Composable
fun CardPreview(){
    WorkWatchAppTheme {
        SearchBar()
    }
}
@Composable
fun ExpandableCardRow(
    expandableCardItem: ExpandableCardItem
) {
    //SearchBar()

   var expanded by remember { mutableStateOf(false) }

    Card {
        Column(modifier = Modifier
            .animateContentSize()
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = expandableCardItem.title, style = MaterialTheme.typography.displaySmall)
                    Text(
                        text = expandableCardItem.secondaryText,
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                ExpandableCardIcon(
                    expanded = expanded,
                    onIconClick = { expanded = !expanded },
                    modifier = Modifier.align(
                        Alignment.CenterEnd
                    )
                )

            }

            if (expanded)
                Divider(thickness = Dp.Hairline, modifier = Modifier.padding(horizontal = 16.dp))

            Text(
                text = expandableCardItem.details.moreTxt,
                modifier = Modifier
                    .height(if (expanded) 56.dp else 0.dp)
                    .padding(16.dp),
                style = MaterialTheme.typography.titleSmall

            )

            Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.End) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Edit task action",
                    tint = Color(android.graphics.Color.parseColor("#58AFB8")),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(26.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete task action",
                    tint = Color(android.graphics.Color.parseColor("#EC225E")),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(26.dp)
                )
            }
        }
    }
}

@Composable
fun ExpandableCardIcon(
    expanded: Boolean,
    onIconClick: () -> Unit,
    modifier: Modifier
) {
    IconButton(onClick = onIconClick, modifier = modifier) {
        Icon(
            Icons.Filled.KeyboardArrowDown,
            "Icono para expandir tarjeta",
            Modifier.rotate(
                if (expanded)
                    180f
                else
                    360f
            )
        )
    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier)
{
    Column(modifier = modifier.verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.teams),
            style = MaterialTheme.typography.titleLarge,
            color = colorResource(id = R.color.aqua_clear_custom)
        )

        var teams by remember {
            mutableStateOf("")
        }

        OutlinedTextField(value = teams, onValueChange = {teams = it},
            label = { Text(text = stringResource(id = R.string.enter_name))},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(id = R.dimen.common_padding_default),
                    start = dimensionResource(id = R.dimen.common_padding_default),
                    end = dimensionResource(id = R.dimen.common_padding_default)
                ),

            trailingIcon = {
                if(teams.isNotEmpty()){
                    Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clean",
                        modifier = Modifier
                            .clickable { teams = "" }
                    )
                }
            }
        )

        Spacer(modifier = Modifier.size(30.dp))

        var items = ExpandableCardItem.ItemDetail("detalle")

        ExpandableCardRow(expandableCardItem = ExpandableCardItem(title = "Messi", secondaryText = "chavalin", items))

    }

}




/*
@Composable
fun CardRow(cardItem: Team) {
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = cardItem.team, style = MaterialTheme.typography.titleLarge)
            Text(text = cardItem.description, style = MaterialTheme.typography.labelLarge)
        }
    }
}*/

/*
@Composable
fun CardList() {
    val items = List(5) { index ->
        val position = index + 1
        Team(
            team = "Título $position",
            description = "Texto secundario $position"
        )
    }

    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            CardRow(
                cardItem = item
            )
        }
    }
}*/



