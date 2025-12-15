package com.publift.fuseappexample

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.content.res.AppCompatResources
import com.publift.fuseappsdk.ads.FuseAdView
import com.publift.fuseappsdk.ads.FuseAdViewBehaviour
import com.publift.fuseappsdk.ads.FuseAdViewEvent
import com.publift.fuseappsdk.ads.FuseAdViewParams
import com.publift.fuseappsdk.ads.FuseAdViewListener

class LayoutActivity : ComponentActivity(), FuseAdViewListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val linearLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
        }

        /*
         * Example 1: Display a banner ad
         */

        val textView1 = TextView(this)
        textView1.text = "Banner Ad"
        linearLayout.addView(textView1)

        val adView1 = FuseAdView(this, code = "banner_zone_code")
        linearLayout.addView(adView1)

        /*
         * Example 2: Display a banner ad with custom parameters
         */

        val textView2 = TextView(this)
        textView2.text = "Banner Ad with Custom Parameters"
        linearLayout.addView(textView2)

        val adView2 = FuseAdView(
            context = this,
            code = "banner_zone_code",
            params = FuseAdViewParams.Builder()
                .setCustomTargeting(mapOf(
                    "sport" to listOf("basketball"),
                    "size" to listOf("small"),
                ))
                .setContentUrl("https://www.publift.com/about")
                .build(),
        )
        linearLayout.addView(adView2)

        // You can listen to banner ad events.
        adView2.addListener(this)

        /*
         * Example 3: Display a banner ad with custom placeholder image
         */

        val textView3 = TextView(this)
        textView3.text = "Banner Ad With Custom Placeholder Image"
        linearLayout.addView(textView3)

        val placeholder = AppCompatResources.getDrawable(this, R.drawable.placeholder)

        val adView3 = FuseAdView(
            context = this,
            code = "banner_zone_code",
            loadingBehaviour = FuseAdViewBehaviour.Background(placeholder),
            errorBehaviour = FuseAdViewBehaviour.Background(placeholder),
        )
        linearLayout.addView(adView3)

        /*
         * Example 4: Display a native ad (instead of a banner ad)
         */
        val textView4 = TextView(this)
        textView4.text = "Native Ad"
        linearLayout.addView(textView4)

        val adView4 = FuseAdView(
            context = this,
            code = "native_zone_code",
            loadingBehaviour = FuseAdViewBehaviour.ProgressBar,
            errorBehaviour = FuseAdViewBehaviour.DoNothing,
        )
        linearLayout.addView(adView4)

        val scrollView = ScrollView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            addView(linearLayout)
        }
        setContentView(scrollView)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEvent(adView: FuseAdView, event: FuseAdViewEvent) {
        // Listen to banner ad events (loading, loaded, etc.)
        println("Fuse Event: $event")
    }
}
