package com.todos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.todos.app.feature.TodoDetail.TodoDetailScreenUI
import com.todos.app.feature.TodoList.TodoListScreenUI
import com.todos.app.feature.TodoList.TodoListViewModel
import com.todos.app.feature.TodoDetail.TodoDetailViewModel
import com.todos.app.ui.component.TwoPaneMasterDetail
import com.todos.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.res.stringResource
import com.todos.app.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val windowSize = calculateWindowSizeClass(this)
                val isTwoPane = windowSize.widthSizeClass >= WindowWidthSizeClass.Medium
                AppScaffold(isTwoPane = isTwoPane)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(isTwoPane: Boolean) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
        modifier = Modifier.fillMaxSize()
    ) { inner ->
        if (isTwoPane) {
            // tablet 2-pane
            var selectedId by remember { mutableStateOf<Int?>(null) }
            TwoPaneMasterDetail(
                listPane = {
                    TodoListScreenUI(
                        onSelect = { selectedId = it },
                        vm = hiltViewModel<TodoListViewModel>(),
                        modifier = Modifier.padding(inner)
                    )
                },
                detailPane = {
                    if (selectedId != null) {
                        TodoDetailScreenUI(
                            id = selectedId!!,
                            vm = hiltViewModel<TodoDetailViewModel>(),
                            modifier = Modifier.padding(inner)
                        )
                    } else {
                        Box(
                            Modifier.fillMaxSize().padding(inner),
                            contentAlignment = Alignment.Center
                        ) { Text(stringResource(R.string.select_item_on_left)) }
                    }
                }
            )
        } else {
            // phone: single-pane nav
            val nav = rememberNavController()
            NavHost(
                navController = nav,
                startDestination = "list",
                modifier = Modifier.padding(inner)
            ) {
                composable("list") {
                    TodoListScreenUI(
                        onSelect = { id -> nav.navigate("detail/$id") },
                        vm = hiltViewModel(),
                        modifier = Modifier
                    )
                }
                composable(
                    "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStack ->
                    val id = backStack.arguments?.getInt("id") ?: 0
                    TodoDetailScreenUI(
                        id = id,
                        vm = hiltViewModel(),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}