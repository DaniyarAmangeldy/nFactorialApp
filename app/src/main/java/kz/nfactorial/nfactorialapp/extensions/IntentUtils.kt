package kz.nfactorial.nfactorialapp.extensions

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.provider.Settings

class IntentUtils {
    companion object {
        // Web Browser Intents
        fun openWebsite(context: Context, url: String) {
            var processedUrl = url
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                processedUrl = "https://$url"
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(processedUrl))
            context.startActivity(intent)
        }

        // Phone Intents
        fun dialNumber(context: Context, phoneNumber: String) {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(intent)
        }

        fun callNumber(context: Context, phoneNumber: String) {
            // Note: Requires CALL_PHONE permission
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(intent)
        }

        fun sendSMS(context: Context, phoneNumber: String, message: String = "") {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:$phoneNumber")
            intent.putExtra("sms_body", message)
            context.startActivity(intent)
        }

        // Email Intents
        fun sendEmail(context: Context, email: String, subject: String = "", body: String = "") {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, body)
            context.startActivity(intent)
        }

        // Share Intents
        fun shareText(context: Context, title: String, text: String) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            context.startActivity(Intent.createChooser(intent, title))
        }

        // Settings Intents
        fun openSettings(context: Context) {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }

        fun openWifiSettings(context: Context) {
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            context.startActivity(intent)
        }

        fun openBluetoothSettings(context: Context) {
            val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
            context.startActivity(intent)
        }

        // App Store Intent
        fun openAppInStore(context: Context, packageName: String = context.packageName) {
            try {
                val intent = Intent(Intent.ACTION_VIEW, 
                    Uri.parse("market://details?id=$packageName"))
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // Fallback to browser if Play Store not installed
                openWebsite(context, 
                    "https://play.google.com/store/apps/details?id=$packageName")
            }
        }

        // File Intents
        fun openPdfFile(context: Context, uri: Uri) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(uri, "application/pdf")
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.startActivity(intent)
        }

        // Calendar Intent
        fun addCalendarEvent(context: Context, title: String, description: String, 
                            beginTime: Long, endTime: Long) {
            val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, description)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
            context.startActivity(intent)
        }

        // Maps Intents
        fun showLocation(context: Context, latitude: Double, longitude: Double, 
                        label: String = "") {
            val uri = if (label.isEmpty()) {
                Uri.parse("geo:$latitude,$longitude")
            } else {
                Uri.parse("geo:$latitude,$longitude?q=${Uri.encode(label)}")
            }
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        }
    }
}