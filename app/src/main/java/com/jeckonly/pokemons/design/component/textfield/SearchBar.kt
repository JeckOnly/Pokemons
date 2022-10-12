package com.jeckonly.pokemons.design.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.theme.Gray10
import com.jeckonly.pokemons.design.theme.Gray5
import com.jeckonly.pokemons.design.theme.Gray9

@Composable
fun SearchBar(
    text: String,
    onValueChange: (String) -> Unit,
    onHelpIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = Gray5,
    ) {
        ConstraintLayout {
            val (searchIcon, textField, helpIcon) = createRefs()

            Icon(
                painter = painterResource(R.drawable.icon_search_line),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(searchIcon) {
                        start.linkTo(parent.start, 15.dp)
                        top.linkTo(parent.top, 14.dp)
                        bottom.linkTo(parent.bottom, 14.dp)
                    },
                tint = Gray9
            )

            BasicTextFieldWithPlaceHolder(
                text = text,
                onValueChange = {
                    onValueChange(it)
                },
                placeHolder = "Name or number",
                textSize = 17.sp,
                textColor = Gray10,
                placeHolderTextColor = Gray10,
                cursorColor = Gray10,
                isPassword = false,
                singleLine = true,
                modifier = Modifier.constrainAs(textField) {
                    start.linkTo(searchIcon.end, 10.dp)
                    top.linkTo(searchIcon.top)
                    end.linkTo(helpIcon.start, 10.dp)
                    bottom.linkTo(searchIcon.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }.fillMaxWidth()
            )

            Icon(
                painter = painterResource(R.drawable.ic_help),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(helpIcon) {
                        end.linkTo(parent.end, 15.dp)
                        top.linkTo(parent.top, 14.dp)
                        bottom.linkTo(parent.bottom, 14.dp)
                    }.clickable {
                        onHelpIconClicked()
                    }
                ,
                tint = Gray9
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSearchBar() {
    var text by remember {
        mutableStateOf("")
    }
    SearchBar(
        text = text,
        onValueChange = {
            text = it
        },
        onHelpIconClicked = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}