package com.publift.fuseappexample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.publift.fuseappsdk.FuseSDK
import com.publift.fuseappsdk.ads.FuseFullScreenAdView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Request POST_NOTIFICATIONS permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1001,
            )
        }
        FuseSDK.init(application)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val interstitialAd = FuseFullScreenAdView(context, "interstitial_zone_code")
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    Column(
                        modifier = Modifier.padding(paddingValues).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {
                                val intent = Intent(context, ComposeActivity::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier.padding(12.dp),
                        ) {
                            Text(text = "Compose Example")
                        }

                        Button(
                            onClick = {
                                val intent = Intent(context, XmlActivity::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier.padding(12.dp),
                        ) {
                            Text(text = "XML Example")
                        }

                        Button(
                            onClick = {
                                val intent = Intent(context, LayoutActivity::class.java)
                                context.startActivity(intent)
                            },
                            modifier = Modifier.padding(12.dp),
                        ) {
                            Text(text = "Layout Example")
                        }

                        Button(
                            onClick = {
                                /*
                                 * Example: Display a full-screen interstitial ad
                                 */
                                interstitialAd.show(this@MainActivity, 10_000)
                            },
                            modifier = Modifier.padding(12.dp),
                        ) {
                            Text(text = "Interstitial Example")
                        }
                    }
                }
            }
        }
    }
}
