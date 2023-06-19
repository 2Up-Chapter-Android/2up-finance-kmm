package com.aibles.transaction.presentation

import Finance2upKMM.feature.transaction.MR
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.aibles.transaction.data.remote.dto.login.LoginTestRequest
import com.aibles.transaction.data.remote.services.TransactionDataSource
import com.aibles.transaction.presentation.theme.buttonHeight_transaction_buttonNextAction
import com.aibles.transaction.presentation.theme.create_transaction_padding_horizontal
import com.aibles.transaction.presentation.theme.create_transaction_padding_row
import com.aibles.transaction.presentation.theme.create_transaction_padding_start_text
import com.aibles.transaction.presentation.theme.create_transaction_spacer_padding_bottom
import com.aibles.transaction.presentation.theme.create_transaction_spacer_padding_top
import com.aibles.transaction.presentation.theme.textSize_transaction_textField
import com.aibles.transaction.presentation.theme.thickness_transaction_borderStroke
import dev.icerock.moko.resources.compose.colorResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TransactionDashboardScreen : Screen, KoinComponent {
    @Composable
    override fun Content() {
        TransactionDashboardScreen()
    }

    @Composable
    private fun TransactionDashboardScreen() {
        val dataSource by inject<TransactionDataSource>()
        val text = remember { mutableStateOf("Hello Transaction") }
        GlobalScope.launch {
            text.value = dataSource.login(
                LoginTestRequest(
                    "Abcd!234",
                    "a@yopmail.com"
                )
            ).data?.data?.access_token ?: "nothing bla bla bla"
        }
//        Text(text.value, fontSize = 50.sp)
        TransScreen()
    }


    @Composable
    fun TransScreen() {

        val viewModel = rememberScreenModel { CreateTransViewModel() }
        val createTransUiState = viewModel.createTransUiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val selectedTabIndex = remember { mutableStateOf(0) }

            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                contentColor = Color.Transparent,
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                tabLayoutTrans(index = 0, value = "Income", selectedTabIndex = selectedTabIndex)
                tabLayoutTrans(index = 1, value = "Expense", selectedTabIndex = selectedTabIndex)
                tabLayoutTrans(index = 2, value = "Transfer", selectedTabIndex = selectedTabIndex)

            }

            /**
             * when (selectedTabIndex.value) {
            0 -> IncomeTab()
            1 -> ExpenseTab()
            2 -> TransferTab()
            }
             * */
            LineTransInfor(
                text = createTransUiState.value.date,
                textLabel = "Date",
                onTextChange = { viewModel.onDateChange(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            )

            LineTransInfor(
                text = createTransUiState.value.amount,
                textLabel = "Amount",
                onTextChange = { viewModel.onAmountChange(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            )

            LineTransInfor(
                text = createTransUiState.value.category,
                textLabel = "Category",
                onTextChange = { viewModel.onCategoryChange(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            )

            LineTransInfor(
                text = createTransUiState.value.account,
                textLabel = "Account",
                onTextChange = { viewModel.onAccountChange(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            )

            LineTransInfor(
                text = createTransUiState.value.note,
                textLabel = "Note",
                onTextChange = { viewModel.onNoteChange(it) },
                keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
            )

            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .padding(
                        top = create_transaction_spacer_padding_top,
                        bottom = create_transaction_spacer_padding_bottom
                    )
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( create_transaction_padding_horizontal)
            ) {
                Button(
                    onClick = { /* Handle create button click */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = create_transaction_padding_row)
                        .height(buttonHeight_transaction_buttonNextAction),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = colorResource(MR.colors.color_tab_expense))


                ) {
                    Text(text = "Create", color = Color.White)
                }
                Button(
                    onClick = { /* Handle image button click */ },
                    modifier = Modifier
                        .height(buttonHeight_transaction_buttonNextAction),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color.White),
                    border = BorderStroke(thickness_transaction_borderStroke, Color.Black)
                ) {
                    Text(text = "Continue", color = Color.Black)

                }
            }


        }
    }

    @Composable
    fun tabLayoutTrans(index: Int, value: String, selectedTabIndex: MutableState<Int>) {
        Tab(
            selected = selectedTabIndex.value == index,
            onClick = { selectedTabIndex.value = index },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = { selectedTabIndex.value = index },
                modifier = Modifier.padding(vertical = create_transaction_padding_row),
                shape = RoundedCornerShape(20),
                border = if (selectedTabIndex.value == index) {
                    when (selectedTabIndex.value) {
                        0 -> BorderStroke(thickness_transaction_borderStroke, Color.Blue)
                        1 -> BorderStroke(
                            thickness_transaction_borderStroke, colorResource(MR.colors.color_tab_expense)
                        )

                        else -> BorderStroke(thickness_transaction_borderStroke, Color.Black)
                    }
                } else {
                    BorderStroke(thickness_transaction_borderStroke, Color.Gray)
                },
                colors = if (selectedTabIndex.value == index) {
                    when (selectedTabIndex.value) {
                        0 -> ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Blue, backgroundColor = Color.White
                        )

                        1 -> ButtonDefaults.outlinedButtonColors(
                            contentColor = colorResource(MR.colors.color_tab_expense),
                            backgroundColor = Color.White
                        )

                        else -> ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.Black, backgroundColor = Color.White
                        )
                    }
                } else {
                    ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Gray,
                        backgroundColor = colorResource(MR.colors.color_tab_unselected)
                    )
                }
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.button,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Composable
    fun LineTransInfor(
        textLabel: String,
        text: String,
        onTextChange: (String) -> Unit,
        keyboardOption: KeyboardOptions,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = androidx.compose.ui.Modifier.padding(
                create_transaction_padding_row
            )
        ) {
            Text(
                text = textLabel,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = create_transaction_padding_start_text)
                    .weight(2f)
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f),
                value = text,
                onValueChange = { onTextChange(it) },
                textStyle = TextStyle(
                    fontSize = textSize_transaction_textField
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.textFieldColors(

                    backgroundColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
        }
    }

}