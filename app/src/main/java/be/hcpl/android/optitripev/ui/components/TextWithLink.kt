package be.hcpl.android.optitripev.ui.components

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import be.hcpl.android.optitripev.ui.theme.primaryLight
import uz.kjuraev.linkify.LinkifyContent
import uz.kjuraev.linkify.LinkifyText

@OptIn(ExperimentalTextApi::class)
@Composable
fun TextWithLink(
    text: String,
    modifier: Modifier = Modifier,
    onUrlClicked: ((String) -> Unit)? = null,
) {
    if (onUrlClicked != null) {
        // linkify is expected here
        LinkifyText(
            modifier = modifier,
            content = LinkifyContent(
                originalText = text,
                spanStyle = SpanStyle(
                    color = primaryLight,
                    textDecoration = TextDecoration.Underline,
                ),
            ),
            style = MaterialTheme.typography.bodyLarge.copy(color = LocalContentColor.current),
            onUrlClicked = onUrlClicked,
        )
    } else {
        Text(
            modifier = modifier,
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}