package com.todos.app.ui.theme

import androidx.compose.ui.graphics.Color

// Brand & neutrals
val Blue40 = Color(0xFF3B82F6)  // primary (brand)
val Blue80 = Color(0xFF93C5FD)  // primaryContainer (light)
val Blue20 = Color(0xFF1D4ED8)  // inversePrimary (dark-ish)

val BlueGrey40 = Color(0xFF64748B) // secondary
val BlueGrey80 = Color(0xFFCBD5E1) // secondaryContainer

val Teal40 = Color(0xFF14B8A6)     // tertiary
val Teal80 = Color(0xFFA7F3D0)     // tertiaryContainer

val Gray10 = Color(0xFF111827)     // onBackground (dark text for light bg)
val Gray98 = Color(0xFFFAFAFA)     // background/surface (very light)
val Gray20 = Color(0xFF1F2937)
val Gray30 = Color(0xFF374151)
val Gray90 = Color(0xFFE5E7EB)
val Gray95 = Color(0xFFF3F4F6)

val Error40 = Color(0xFFB3261E)
val Error80 = Color(0xFFF2B8B5)

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

// LIGHT scheme (default)
val LightColors = androidx.compose.material3.lightColorScheme(
    primary = Blue40,
    onPrimary = White,
    primaryContainer = Blue80,
    onPrimaryContainer = Color(0xFF0A2A6B),

    secondary = BlueGrey40,
    onSecondary = White,
    secondaryContainer = BlueGrey80,
    onSecondaryContainer = Color(0xFF0F172A),

    tertiary = Teal40,
    onTertiary = White,
    tertiaryContainer = Teal80,
    onTertiaryContainer = Color(0xFF064E3B),

    error = Error40,
    onError = White,
    errorContainer = Error80,
    onErrorContainer = Color(0xFF601410),

    background = Gray98,
    onBackground = Gray10,

    surface = Gray98,
    onSurface = Gray10,
    surfaceVariant = Gray95,
    onSurfaceVariant = Gray30,

    outline = Color(0xFF9CA3AF),
    outlineVariant = Gray90,

    inverseSurface = Gray20,
    inverseOnSurface = Gray95,
    inversePrimary = Blue20,

    scrim = Black
)

// DARK scheme (opsional; aktifkan kalau kamu pakai mode gelap)
val DarkColors = androidx.compose.material3.darkColorScheme(
    primary = Blue80,
    onPrimary = Color(0xFF062042),
    primaryContainer = Blue20,
    onPrimaryContainer = Blue80,

    secondary = Color(0xFF94A3B8),
    onSecondary = Color(0xFF0B1220),
    secondaryContainer = Color(0xFF293241),
    onSecondaryContainer = Color(0xFFDCE3EC),

    tertiary = Color(0xFF5EEAD4),
    onTertiary = Color(0xFF06281F),
    tertiaryContainer = Color(0xFF115E59),
    onTertiaryContainer = Color(0xFFD0FFF4),

    error = Color(0xFFFFB4A9),
    onError = Color(0xFF680003),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFFFDAD4),

    background = Color(0xFF0B1220),
    onBackground = Color(0xFFE5E7EB),

    surface = Color(0xFF0B1220),
    onSurface = Color(0xFFE5E7EB),
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = Color(0xFFCBD5E1),

    outline = Color(0xFF94A3B8),
    outlineVariant = Color(0xFF334155),

    inverseSurface = Gray98,
    inverseOnSurface = Gray10,
    inversePrimary = Blue40,

    scrim = Black
)
