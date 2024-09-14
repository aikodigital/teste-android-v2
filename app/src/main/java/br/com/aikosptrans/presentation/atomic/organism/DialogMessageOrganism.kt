package br.com.aikosptrans.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import br.com.aikosptrans.presentation.atomic.atom.BaseContainedButton
import br.com.aikosptrans.presentation.atomic.atom.BaseOutlinedButton
import br.com.aikosptrans.ui.theme.Colors
import br.com.aikosptrans.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogMessageOrganism(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    mainButtonText: String,
    mainButtonAction: () -> Unit,
    auxiliaryButtonText: String,
    auxiliaryButtonAction: () -> Unit,
    dismissAction: () -> Unit = {},
    canBeCancelled: Boolean = false
) {
    BasicAlertDialog(
        onDismissRequest = dismissAction,
        modifier = modifier,
        properties = DialogProperties(
            dismissOnBackPress = canBeCancelled,
            dismissOnClickOutside = canBeCancelled
        )
    ) {
        Surface(
            modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(24.dp),
            color = Colors.white
        ) {
            Column(modifier = modifier.padding(24.dp)) {
                Text(
                    text = title,
                    style = Typography.titleLarge,
                    modifier = modifier.padding(top = 16.dp),
                    color = Colors.black
                )
                Text(
                    text = message,
                    style = Typography.bodyMedium,
                    modifier = modifier.padding(top = 16.dp),
                    color =  Colors.black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    BaseOutlinedButton(
                        onClick = auxiliaryButtonAction,
                        label = auxiliaryButtonText
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    BaseContainedButton(
                        onClick = mainButtonAction,
                        label = mainButtonText
                    )
                }
            }
        }
    }
}