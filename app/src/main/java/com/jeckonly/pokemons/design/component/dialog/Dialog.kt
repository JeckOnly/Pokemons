package com.jeckonly.pokemons.design.component.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.theme.Blue10
import com.jeckonly.pokemons.design.theme.Blue8

@Composable
fun TitleBody2ButtonDialog(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    buttonLeft: @Composable () -> Unit,
    buttonRight: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title()
                Spacer(modifier = Modifier.height(6.dp))
                body()
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    buttonLeft()
                    buttonRight()
                }
            }
        }
    }
}

@Composable
fun TitleBody1ButtonDialog(
    title: @Composable () -> Unit,
    body: @Composable () -> Unit,
    button: @Composable () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title()
                Spacer(modifier = Modifier.height(6.dp))
                body()
                Spacer(modifier = Modifier.height(6.dp))
                button()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTitleBody2ButtonDialog() {
    TitleBody2ButtonDialog(
        title = {
            Text(
                text = stringResource(id = R.string.exact_mode),
                color = Blue10,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        body = {
            Text(
                text = stringResource(id = R.string.exact_mode_words),
                color = Blue8,
                fontSize = 14.sp
            )
        },
        buttonLeft = {
            Text(text = stringResource(id = R.string.later), color = Blue10, fontSize = 14.sp)
        },
        buttonRight = {
            Button(
                onClick = {

                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue10,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.download))
            }
        }) {
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTitleBody1ButtonDialog() {
    TitleBody1ButtonDialog(
        title = {
            Text(
                text = stringResource(id = R.string.fuzzy_mode),
                color = Blue10,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        },
        body = {
            Text(
                text = stringResource(id = R.string.fuzzy_mode_words),
                color = Blue8,
                fontSize = 14.sp
            )
        },
        button = {
            Button(
                onClick = {

                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Blue10,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = stringResource(id = R.string.get_it))
            }
        }) {
    }
}

