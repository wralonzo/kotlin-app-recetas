import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController

// Definir un CompositionLocal para NavController
val LocalNavController = compositionLocalOf<NavController> { error("NavController no disponible") }
