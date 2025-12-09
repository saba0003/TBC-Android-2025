package com.example.tbc_android_2025.data.remote.dtos

import java.util.Locale

enum class Genre {

    ACTION, ADVENTURE, ANIMATION, COMEDY, CRIME, DOCUMENTARY, DRAMA, FANTASY, HORROR, MYSTERY,
    ROMANCE, SCIENCE_FICTION, THRILLER, WAR, WESTERN, MAFIA, GANGSTER, SUSPENSE, CLASSIC, SILENT,
    SHORT, HISTORY, ADAPTATION, NOIR, CYBERPUNK, FAMILY, GRAPHIC_NOVEL, INDIE, DETECTIVE_FICTION,
    HEIST, PRISON, MELODRAMA;

    @JsonValue
    override fun toString() =
        when (this) {
            SCIENCE_FICTION -> "Sci-Fi"
            GRAPHIC_NOVEL -> "Graphic Novel"
            DETECTIVE_FICTION -> "Detective Fiction"
            else -> {
                val lowercase = this.name.lowercase(Locale.getDefault())
                lowercase[0].uppercaseChar().toString()
                    .plus(other = lowercase.substring(startIndex = 1))
            }
        }
}
