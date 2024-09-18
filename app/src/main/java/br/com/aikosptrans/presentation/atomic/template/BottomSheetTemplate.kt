package br.com.aikosptrans.presentation.atomic.template

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.aikosptrans.ui.theme.Colors
import kotlinx.coroutines.launch

@Composable
fun BottomSheetDialogTemplate(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    sheetContent: @Composable (ModalBottomSheetState) -> Unit,
    show: Boolean,
    skipHalfExpanded: Boolean = false,
    isCancelable: Boolean = true,
    dismissCallBack: () -> Unit = {},
    backgroundColor: Color = Colors.white,
    sheetShape: Shape = RoundedCornerShape(
        topStart = 24.dp,
        topEnd = 24.dp,
    ),
    content: @Composable () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded,
        confirmValueChange = { newSheetValue ->
            val isHiddenState = newSheetValue == ModalBottomSheetValue.Hidden
            val canUpdateState = if (!isCancelable) !isHiddenState else true

            canUpdateState
        }
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = show) {
        if (show) modalBottomSheetState.show()
        else modalBottomSheetState.hide()
    }

    LaunchedEffect(modalBottomSheetState.isVisible) {
        if (!modalBottomSheetState.isVisible) {
            dismissCallBack()
        }
    }

    BackHandler {
        scope.launch {
            when (modalBottomSheetState.currentValue) {
                ModalBottomSheetValue.Hidden -> navController?.popBackStack()
                ModalBottomSheetValue.Expanded -> modalBottomSheetState.hide()
                ModalBottomSheetValue.HalfExpanded -> Unit
            }
        }
    }

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = modalBottomSheetState,
        scrimColor = Color.Black.copy(alpha = .4f),
        sheetBackgroundColor = backgroundColor,
        sheetShape = sheetShape,
        sheetContent = {
            sheetContent.invoke(modalBottomSheetState)
        },
        content = {
            content.invoke()
        }
    )
}