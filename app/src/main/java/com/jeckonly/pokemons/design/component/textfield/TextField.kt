package com.jeckonly.pokemons.design.component.textfield

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeckonly.pokemons.R
import com.jeckonly.pokemons.design.theme.Gray10
import com.jeckonly.pokemons.design.theme.Gray5
import com.jeckonly.pokemons.design.theme.Gray9


/**
 * 基本控件——带placeholder的textfield
 *
 * @param text
 * @param onValueChange
 * @param placeHolder 占位字符串
 * @param textSize 正式文字字体大小
 * @param textColor 正式文字字体颜色
 * @param placeHolderTextColor 占位字符串颜色
 * @param cursorColor 光标颜色
 * @param isPassword 是否是密码类型
 * @param singleLine 是否限制单行
 */
@Composable
fun BasicTextFieldWithPlaceHolder(
    text: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    textSize: TextUnit,
    textColor: Color,
    placeHolderTextColor: Color,
    cursorColor: Color,
    isPassword: Boolean,
    singleLine: Boolean,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        textStyle = TextStyle(
            color = textColor,
            fontSize = textSize,
        ),
        keyboardOptions = if (isPassword) {
            KeyboardOptions(keyboardType = KeyboardType.Password)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        },
        singleLine = singleLine,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        cursorBrush = SolidColor(cursorColor)
    ) { innerTextField ->
        Column {
            Box() {
                androidx.compose.animation.AnimatedVisibility(
                    visible = text.isEmpty(),
                    enter = fadeIn(), exit = fadeOut()
                ) {
                    Text(text = placeHolder, color = placeHolderTextColor, fontSize = textSize)
                }
                innerTextField()
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewBasicEditText() {
    var text by remember {
        mutableStateOf("")
    }
    BasicTextFieldWithPlaceHolder(
        text,
        {
            text = it
        },
        "placeHolder",
        17.sp,
        Color.Black,
        Color.Black,
        Color.Black,
        false,
        singleLine = false
    )
}
