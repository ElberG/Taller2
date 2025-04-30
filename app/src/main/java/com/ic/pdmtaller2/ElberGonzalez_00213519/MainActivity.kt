package com.ic.pdmtaller2.ElberGonzalez_00213519

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainApp()
            }
        }
    }
}

// ---------- Data ----------

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val categories: List<String>,
    val menu: List<Dish>
)

val sampleRestaurants = listOf(
    // Comida Rápida
    Restaurant(
        id = 1,
        name = "McDonald's",
        description = "La clásica cadena de hamburguesas.",
        categories = listOf("Comida Rápida"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/640px-McDonald%27s_Golden_Arches.svg.png",
        menu = listOf(
            Dish(1, "Big Mac", "Doble carne, lechuga, queso, pepinillos y cebolla", "https://s7d1.scene7.com/is/image/mcdonalds/DC_202302_0005-999_BigMac_1564x1564-1:nutrition-calculator-tile"),
            Dish(2, "Quarter Pounder", "Carne 100% res con queso, cebolla y pepinillos", "https://s7d1.scene7.com/is/image/mcdonalds/DC_202201_0007-005_QuarterPounderwithCheese_1564x1564-1:nutrition-calculator-tile"),
            Dish(3, "Cheeseburger", "Hamburguesa con queso cheddar, pepinillos y kétchup", "https://s7d1.scene7.com/is/image/mcdonalds/DC_202302_0003-999_CheeseburgerAlt_1564x1564-1:nutrition-calculator-tile"),
            Dish(4, "McNuggets", "Crujientes nuggets de pollo", "https://mcdonalds.com.sv/imagen/menu-products/1640816959_6.%20mcnuggets%20.jpg"),
            Dish(5, "McPollo Doble", "Pechuga empanizada, lechuga y mayonesa", "https://mcdonalds.com.sv/imagen/menu-products/1697241792_400x400_SANDWICH_MCPOLLO_DOBLE.png"),
            Dish(6, "Papas Fritas", "Clásicas papas doradas y crujientes", "https://mcdonalds.com.sv/imagen/menu-products/1640817564_papas.jpg"),
            Dish(7, "McFlurry", "Helado con toppings dulces a elección", "https://mcdonalds.com.sv/imagen/menu-products/1697241863_400x400_MCFLURRY_OREO.png")
        )
    ),
    Restaurant(
        id = 2,
        name = "Burger King",
        description = "Hamburguesas flameadas al carbón.",
        categories = listOf("Comida Rápida"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Burger_King_2020.svg/1200px-Burger_King_2020.svg.png",
        menu = listOf(
            Dish(8, "Whopper", "Hamburguesa con carne de res, tomate y lechuga", "https://cdn.sanity.io/images/czqk28jt/prod_bk_es/f98cd55b3ff9839560f96eadeed81ff9b3983fe2-1333x1333.png?w=750&q=40&fit=max&auto=format"),
            Dish(9, "Big King", "Hamburguesa con carne de res, tomate y lechuga", "https://cdn.sanity.io/images/czqk28jt/prod_bk_es/249e17a832814ba415a3e4fc80b96869d0d389a5-2000x1333.png?w=750&q=40&fit=max&auto=format"),
            Dish(10, "Papas Fritas", "Crujientes y doradas", "https://cdn.sanity.io/images/czqk28jt/prod_bk_es/9bcf3248750ff898640ef7ec398961c77e889570-400x400.png?w=750&q=40&fit=max&auto=format")
        )
    ),
    Restaurant(
        id = 3,
        name = "Wendy's",
        description = "Hamburguesas clásicas y papas crujientes.",
        categories = listOf("Comida Rápida"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/3/32/Wendy%27s_full_logo_2012.svg/1200px-Wendy%27s_full_logo_2012.svg.png",
        menu = listOf(
            Dish(11, "Baconator", "Hamburguesa con doble carne, queso y tocino", "https://images.unsplash.com/photo-1606756792962-02e5d8d65dc0"),
            Dish(12, "Spicy Nuggets", "Nuggets de pollo con un toque picante", "https://images.unsplash.com/photo-1620588452019-e8e9db9b1efb")
        )
    ),

    // Comida Mexicana
    Restaurant(
        id = 4,
        name = "Taco Bell",
        description = "Tacos tradicionales al carbón.",
        categories = listOf("Comida Mexicana"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/b/b3/Taco_Bell_2016.svg/800px-Taco_Bell_2016.svg.png",
        menu = listOf(
            Dish(13, "Taco al Pastor", "Carne de cerdo marinada", "https://images.unsplash.com/photo-1582452969132-818503a936a6"),
            Dish(14, "Taco de Bistec", "Bistec con cebolla y cilantro", "https://images.unsplash.com/photo-1600891964904-c9429b6b920b")
        )
    ),

    // Comida Italiana
    Restaurant(
        id = 5,
        name = "Pizza Hut",
        description = "Deliciosa pizza y variedad de pastas.",
        categories = listOf("Comida Italiana"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Pizza_Hut_%282019%29.svg/1024px-Pizza_Hut_%282019%29.svg.png",
        menu = listOf(
            Dish(15, "Margherita", "Tomate, mozzarella y albahaca", "https://images.unsplash.com/photo-1600628422019-19f37c0c0c95"),
            Dish(16, "Pepperoni", "Queso mozzarella y pepperoni", "https://images.unsplash.com/photo-1594007654729-ea6f2cde7f71")
        )
    ),
    Restaurant(
        id = 6,
        name = "Papa John's",
        description = "Clásicas pizzas con ingredientes de calidad.",
        categories = listOf("Comida Italiana"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Papa_John%27s_Logo_2019.svg/1920px-Papa_John%27s_Logo_2019.svg.png",
        menu = listOf(
            Dish(17, "Fettuccine Alfredo", "Pasta cremosa con parmesano", "https://images.unsplash.com/photo-1589308078054-832135edb5c7"),
            Dish(18, "Lasagna", "Capas de pasta, carne y salsa bechamel", "https://images.unsplash.com/photo-1613141411596-8ba965b2c65e")
        )
    ),

    // Comida Asiática
    Restaurant(
        id = 7,
        name = "Panda Express",
        description = "Sabores asiáticos en platos tradicionales.",
        categories = listOf("Comida Asiática"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/8/85/Panda_Express_logo.svg/1024px-Panda_Express_logo.svg.png",
        menu = listOf(
            Dish(19, "California Roll", "Surimi, aguacate y pepino", "https://images.unsplash.com/photo-1579584425555-c3ce17fd4351"),
            Dish(20, "Nigiri de Salmón", "Arroz y salmón fresco", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c")
        )
    ),
    Restaurant(
        id = 8,
        name = "China Wok",
        description = "Comida china rápida y deliciosa.",
        categories = listOf("Comida Asiática"),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/China_Wok_logo.svg/2560px-China_Wok_logo.svg.png",
        menu = listOf(
            Dish(21, "Pollo Teriyaki", "Pollo glaseado con salsa teriyaki", "https://images.unsplash.com/photo-1620161328604-4cda6fc16d89")
        )
    ),

    // Comida Saludable
    Restaurant(
        id = 9,
        name = "Go Green",
        description = "Ensaladas frescas y bowls saludables.",
        categories = listOf("Comida Saludable"),
        imageUrl = "https://grupogogreen.com/wp-content/uploads/2022/07/logo-footer-300x288.png",
        menu = listOf(
            Dish(22, "Bowl Vegano", "Quinoa, vegetales y garbanzos", "https://images.unsplash.com/photo-1551183053-bf91a1d81141"),
            Dish(23, "Ensalada César", "Lechuga romana, pollo y parmesano", "https://images.unsplash.com/photo-1589927986089-35812388d1d7")
        )
    ),
)

// ---------- Screens ----------

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object Orders : Screen("orders")
    object Menu : Screen("menu/{restaurantId}") {
        fun routeWithId(id: Int) = "menu/$id"
    }
}

// ---------- App ----------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                RestaurantListScreen(navController)
            }
            composable(Screen.Search.route) {
                CenteredText("Pantalla de búsqueda")
            }
            composable(Screen.Orders.route) {
                CenteredText("Pantalla de órdenes")
            }
            composable(Screen.Menu.route) { backStack ->
                val id = backStack.arguments?.getString("restaurantId")?.toIntOrNull()
                val restaurant = sampleRestaurants.find { it.id == id }
                restaurant?.let {
                    MenuScreen(it)
                }
            }
        }
    }
}

// ---------- Bottom Navigation ----------

@Composable
fun BottomNavBar(navController: NavHostController) {
    val current = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = current == Screen.Home.route,
            onClick = { navController.navigate(Screen.Home.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = current == Screen.Search.route,
            onClick = { navController.navigate(Screen.Search.route) },
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Buscar") }
        )
        NavigationBarItem(
            selected = current == Screen.Orders.route,
            onClick = { navController.navigate(Screen.Orders.route) },
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
            label = { Text("Pedidos") }
        )
    }
}

// ---------- Screens ----------

@Composable
fun RestaurantListScreen(navController: NavHostController) {
    val grouped = sampleRestaurants.groupBy { it.categories.firstOrNull() ?: "Otros" }

    LazyColumn {
        grouped.forEach { (category, restaurants) ->
            item {
                Text(
                    category,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                LazyRow {
                    items(restaurants) { restaurant ->
                        Card(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(8.dp)
                                .clickable {
                                    navController.navigate(Screen.Menu.routeWithId(restaurant.id))
                                }
                        ) {
                            Column(Modifier.padding(8.dp)) {
                                AsyncImage(
                                    model = restaurant.imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(100.dp)
                                        .fillMaxWidth()
                                )
                                Text(
                                    restaurant.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    restaurant.description,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(restaurant: Restaurant) {
    val context = LocalContext.current
    var search by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(restaurant.name, style = MaterialTheme.typography.headlineSmall)
        Text(restaurant.description, style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Buscar platillo") },
            modifier = Modifier.fillMaxWidth()
        )

        val filteredMenu = restaurant.menu.filter {
            it.name.contains(search.text, ignoreCase = true)
        }

        LazyColumn {
            items(filteredMenu) { dish ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        AsyncImage(
                            model = dish.imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .size(60.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Column(Modifier.weight(1f)) {
                            Text(dish.name, style = MaterialTheme.typography.titleMedium)
                            Text(dish.description, style = MaterialTheme.typography.bodyMedium)
                        }
                        Button(onClick = {
                            Toast.makeText(
                                context,
                                "${dish.name} agregado al carrito",
                                Toast.LENGTH_SHORT
                            ).show()
                        }) {
                            Text("Agregar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text)
    }
}
