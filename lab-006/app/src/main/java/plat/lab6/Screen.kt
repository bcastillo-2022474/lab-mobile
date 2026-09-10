package plat.lab6

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.min

@Composable
fun Screen() {
    var counter by remember { mutableIntStateOf(0) }
    var increments by remember { mutableIntStateOf(0) }
    var decrements by remember { mutableIntStateOf(0) }
    var maxValue by remember { mutableIntStateOf(0) }
    var minValue by remember { mutableIntStateOf(0) }
    var historicalOperations = remember {
        mutableStateListOf<Operation>()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
//                    .weight(1f)
                    ,
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Joao Castillo",
                        fontSize = 30.sp,
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    counter -= 1
                                    decrements += 1
                                    minValue = min(minValue, counter)
                                    historicalOperations.add(
                                        Operation(
                                            counter,
                                            OperationKind.DECREMENT
                                        )
                                    )
                                }
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xff445e91)),

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                "-",
                                color = Color.White
                            )
                        }
                        Text("$counter", fontSize = 60.sp)
                        Row(
                            modifier = Modifier
                                .clickable {
                                    counter += 1
                                    increments += 1
                                    maxValue = max(maxValue, counter)
                                    historicalOperations.add(
                                        Operation(
                                            counter,
                                            OperationKind.INCREMENT
                                        )
                                    )
                                }
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(Color(0xff445e91)),

                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "+",
                                color = Color.White
                            )
                        }
                    }
                    HorizontalDivider()
                    StatRow("Total incrementos:", increments)
                    StatRow("Total decrementos:", decrements)
                    StatRow("Valor máximo", maxValue)
                    StatRow("Valor minimo", minValue)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 50.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Historial:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    FlowRow(
                        modifier = Modifier
                            .padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)

                    ) {
                        for (historicalOperation in historicalOperations) {

                            val color = when (historicalOperation.operation) {
                                OperationKind.INCREMENT -> Color(0xff1a7d28)
                                OperationKind.DECREMENT -> Color(0xffb3261e)
                            }

                            Column(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(60.dp)
                                    .clip(RoundedCornerShape(10))
                                    .background(color),

                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "${historicalOperation.value}",
                                    fontSize = 20.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                ,

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff445e91)
                ),

                onClick = {
                    counter = 0
                    increments = 0
                    decrements = 0
                    maxValue = 0
                    minValue = 0
                    historicalOperations = SnapshotStateList()
                }
            ) {
                Text("Reiniciar")
            }
        }
    }

}

@Preview
@Composable
fun ScreenPreview() {
    Screen()
}

@Composable
fun StatRow(
    label: String,
    value: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("$value", fontSize = 16.sp)
    }

}

enum class OperationKind {
    INCREMENT, DECREMENT
}

data class Operation(val value: Int, val operation: OperationKind)