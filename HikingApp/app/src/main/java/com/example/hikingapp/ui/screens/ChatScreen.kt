package com.example.hikingapp.ui.screens

import android.graphics.Color
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.hikingapp.ui.model.Hike
import com.example.hikingapp.ui.model.hikes
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import com.example.hikingapp.R



@Composable
fun ChatMapWithPopupScreen() {
    val context = LocalContext.current

    Configuration.getInstance().load(
        context,
        PreferenceManager.getDefaultSharedPreferences(context)
    )

    var selectedHike by remember { mutableStateOf<Hike?>(null) }

    Box {
        AndroidView(
            factory = { ctx ->
                MapView(ctx).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)

                    controller.setZoom(8.0)
                    controller.setCenter(GeoPoint(46.8, 8.3))

                    // Add markers for hikes
                    for (hike in hikes) {
                        val marker = Marker(this)
                        marker.position = GeoPoint(hike.lat, hike.lon)
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        marker.title = hike.name
                        marker.subDescription = hike.description

                        // 🎨 Color the pin by mark
                        val markValue = hike.mark ?: 0.0
                        val color = when {
                            markValue < 2.0 -> Color.RED
                            markValue < 3.5 -> Color.parseColor("#FFA500") // Orange
                            else -> Color.parseColor("#006400") // Dark green
                        }
                        val customPin = androidx.core.content.ContextCompat.getDrawable(ctx, R.drawable.icon_map)?.mutate()
                        customPin?.setTint(color)
                        marker.icon = customPin

                        marker.setOnMarkerClickListener { _, _ ->
                            selectedHike = hike
                            true
                        }

                        overlays.add(marker)
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (selectedHike != null) {
            HikePopup(hike = selectedHike!!, onDismiss = { selectedHike = null })
        }
    }
}

@Composable
fun HikePopup(hike: Hike, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = hike.name) },
        text = {
            var txt = hike.description
            hike.mark?.let { txt += "\n\nMark: $it / 5 ⭐" }
            hike.riskLevel?.let { txt += "\n\nRisk level: $it" }
            hike.insuranceUrl?.let { txt += "\n\nInsurance: $it" }
            Text(text = txt)
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
