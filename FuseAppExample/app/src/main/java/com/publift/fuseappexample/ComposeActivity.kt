package com.publift.fuseappexample

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.publift.fuseappsdk.ads.ComposeFuseAdView
import com.publift.fuseappsdk.ads.FuseAdViewBehaviour
import com.publift.fuseappsdk.ads.FuseAdViewParams

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val placeholder = AppCompatResources.getDrawable(context, R.drawable.placeholder)
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        /*
                         * Example 1: Display a banner ad
                         */

                        Text("Banner Ad", Modifier.padding(12.dp))

                        ComposeFuseAdView("banner")

                        HorizontalDivider()

                        /*
                         * Example 2: Display a banner ad with custom parameters
                         */

                        Text("Banner Ad with Custom Parameters", Modifier.padding(12.dp))

                        ComposeFuseAdView(
                            code = "banner",
                            params = FuseAdViewParams.Builder()
                                .setCustomTargeting(mapOf(
                                    "sport" to listOf("basketball"),
                                    "size" to listOf("small"),
                                ))
                                .setContentUrl("https://www.publift.com/about")
                                .build(),
                            onEvent = { adView, event ->
                                // You can listen to banner ad events.
                                println("Fuse Event: $event")
                            }
                        )

                        HorizontalDivider()

                        /*
                         * Example 3: Display a banner ad with custom placeholder image
                         */

                        Text("Banner Ad With Custom Placeholder Image", Modifier.padding(12.dp))

                        ComposeFuseAdView(
                            code = "example_no_fill_banner",
                            loadingBehaviour = FuseAdViewBehaviour.Background(placeholder),
                            errorBehaviour = FuseAdViewBehaviour.Background(placeholder),
                        )

                        HorizontalDivider()

                        /*
                         * Example 4: Display a native ad (instead of a banner ad)
                         */

                        Text("Native Ad", Modifier.padding(12.dp))

                        ComposeFuseAdView(
                            code = "example_native",
                            loadingBehaviour = FuseAdViewBehaviour.ProgressBar,
                            errorBehaviour = FuseAdViewBehaviour.DoNothing,
                        )

                        HorizontalDivider()
                    }
                }
            }
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
