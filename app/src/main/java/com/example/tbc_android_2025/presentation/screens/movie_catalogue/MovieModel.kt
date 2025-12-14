package com.example.tbc_android_2025.presentation.screens.movie_catalogue

import kotlinx.datetime.LocalDate

data class MovieModel(
    val id: Int,
    val title: String,
    val description: String,
    val releaseDate: LocalDate,
    val duration: String,
    val genres: List<Genre>,
    val languages: List<Language>,
    val ageRating: AgeRating,
    val director: String,
    val country: String,
    val postersUrls: List<String>,
    val trailersUrls: List<String>,
    val budget: String,
    val boxOfficeGross: String
) {
    enum class Genre {
        ACTION, ADVENTURE, ANIMATION, COMEDY, CRIME, DOCUMENTARY, DRAMA, FANTASY, HORROR, MYSTERY,
        ROMANCE, SCIENCE_FICTION, THRILLER, WAR, WESTERN, MAFIA, GANGSTER, SUSPENSE, CLASSIC, SILENT,
        SHORT, HISTORY, ADAPTATION, NOIR, CYBERPUNK, FAMILY, GRAPHIC_NOVEL, INDIE, DETECTIVE_FICTION,
        HEIST, PRISON, MELODRAMA
    }

    enum class Language {
        ENGLISH, SPANISH, FRENCH, GERMAN, ITALIAN, JAPANESE, KOREAN, CHINESE, HINDI, RUSSIAN, GEORGIAN;

        override fun toString() = name.take(n = 3)
    }

    enum class AgeRating {
        G,         // General Audience
        PG,        // Parental Guidance Suggested
        PG_13,     // Parents Strongly Cautioned
        R,         // Restricted
        NC_17,     // Restricted to Under 18
        UNRATED;   // Not Officially Rated

        override fun toString() = when (this) {
            UNRATED -> name.replaceFirstChar { it.titlecase() }
            else -> name.replaceFirst(
                oldChar = OLD_CHAR, newChar = NEW_CHAR, ignoreCase = IGNORE_CASE
            )
        }

        private companion object {
            private const val OLD_CHAR = '_'
            private const val NEW_CHAR = '-'
            private const val IGNORE_CASE = true
        }
    }
}
