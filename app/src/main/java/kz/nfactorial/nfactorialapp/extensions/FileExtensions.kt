package kz.nfactorial.nfactorialapp.extensions

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun createImageFile(context: Context): Uri {
    // Создаем временный файл
    val imageFile = File(
        context.getExternalFilesDir("photos"),
        "picture_${System.currentTimeMillis()}.jpg"
    )

    // Конвертируем путь к файлу в Uri через FileProvider
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        imageFile
    )
}