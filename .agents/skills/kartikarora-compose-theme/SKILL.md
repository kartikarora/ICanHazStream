---
name: kartikarora-compose-theme
description: Implements the @kartikarora brand design system for Jetpack Compose (Android) and Compose Multiplatform (CMP) projects. Defines the complete Material 3 theme including ColorScheme (Akihabara Cyan & Caution Yellow, Obsidian/Concrete), Typography (Chivo & JetBrains Mono), Shapes, Spacing, and full component registry (BrandButton, StatusBadge, CLICommandPill, ArtifactRow, StanceView, KeynoteRow, BrandStatement, SpecCard, TelemetryMetric, FeatureCard, SlidingTabs, TokyoCard).
version: 2.0.0
last-updated: 2026-11-06
maintained-by: Kartik Arora (@kartikarora)
---

# @kartikarora Brand Jetpack Compose & Multiplatform Theme (v2.0.0)

This skill defines the official **Tokyo Neo-Brutalist Material 3 Design System & Component Registry** for Jetpack Compose on Android and Compose Multiplatform (CMP - iOS, Desktop, Web/Wasm). It ensures all native UI automatically adheres to `@kartikarora` brand guidelines across colors, typography, shapes, spacing, and animations, maintaining 100% visual parity with the web baseline.

---

## ⚠️ Strict Mandates for AI Agents

1. **Brand Fidelity:** The Material 3 `ColorScheme`, `Typography`, and `Shapes` must strictly follow the brand tokens.
2. **No Dynamic Color:** Avoid Android 12+ Wallpaper/Dynamic Color (`dynamicLightColorScheme` / `dynamicDarkColorScheme`) to ensure visual brand consistency across all platforms.
3. **Typography Standard:** Use **Chivo** as the primary font family (`FontWeight.Black` for headings with `-0.03em` tracking, `FontWeight.Normal`/`Medium` for body) and **JetBrains Mono** for code/monospace blocks. (Albert Sans has been retired).
4. **The Tokyo Punch in Compose:**
   - **Solid Block Shadows:** Cards, badges, and primary action buttons must feature solid unblurred offset drop-shadows (using `border(2.dp, ...)` and solid background offsets, avoiding diffuse blurred elevation).
   - **Kinetic Micro-Interactions:** Buttons and interactive cards animate with micro-press physics (`graphicsLayer { translationX = ...; translationY = ... }`).
   - **Caution Yellow Secondary Pop:** Secondary indicators and contrasting punch cards must use `KartikAroraColors.LightSignalSecondary` / `DarkSignalSecondary`.
5. **Spacing System:** Use `MaterialTheme.spacing` (8dp base scale: 8dp, 16dp, 24dp, 32dp, 48dp, 64dp, 96dp) instead of arbitrary hardcoded padding.
6. **Authoritative Editorial Statements:** Render pull quotes with the `BrandStatement` composable featuring the 3.dp signal left border and balanced semibold styling (no faux italics).
7. **Fast Transitions:** UI animations and state transitions must use standard Android emphasized easing (`FastTransitionSpec = tween(220, easing = FastOutSlowInEasing)`).
8. **System Theme Tracking:** Respect `isSystemInDarkTheme()`. Automatically switch between Deep Space Obsidian (`#070a10` canvas) and Architectural Concrete (`#ffffff` canvas).
9. **Brand Synchronization:** This skill derives directly from [`kartikarora-brand`](../kartikarora-brand/SKILL.md). Any changes to brand tokens, CSS, or guidelines must immediately be reflected here.

---

## 🎨 Design Tokens & Specifications

### 1. Color Scheme Mapping

| Brand CSS Token | Hex (Light) | Hex (Dark) | Material 3 Semantic Role | Components / Usage |
| :--- | :--- | :--- | :--- | :--- |
| `--color-signal` | `#0084C7` | `#00F0FF` | `primary` | Akihabara Cyan: active states, key interactive accents |
| On Signal | `#FFFFFF` | `#000000` | `onPrimary` | Text/icons on `primary` components |
| `--color-signal-secondary` | `#E6B800` | `#FFE600` | `secondary` | Caution Yellow: contrast pop cards, highlight tags |
| On Signal Secondary | `#0A111C` | `#000000` | `onSecondary` | Text on caution yellow badges/cards |
| `--color-ink-muted` | `#49586E` | `#8A9BB4` | `onSurfaceVariant` | Secondary controls, subtitles, metadata |
| `--color-ink-faint` | `#7F90A6` | `#4E5F7A` | `tertiary` | Tertiary elements, subtle indicators |
| `--color-paper` | `#FFFFFF` | `#070A10` | `background` | Screen background (`Scaffold`, `Surface`) |
| `--color-ink` | `#0A111C` | `#F0F6FC` | `onBackground`, `onSurface` | Main body text, headings, prominent icons |
| `--color-paper-alt` | `#F4F6FA` | `#0D121C` | `surface`, `surfaceContainer` | `Card`, `ElevatedCard`, Sheets, Dialogs |
| `--color-paper-sunken` | `#E6EBF3` | `#04060A` | `surfaceVariant`, `surfaceContainerLowest` | Sunken terminals, code blocks, input containers |
| `--color-rule` | `#CDD8E6` | `#1A2436` | `outline` | Hairline borders and rules |
| `--color-rule-strong` | `#0A111C` | `#273752` | `outlineVariant` | Active card borders, neo-brutalist dividers |

---

## 📦 Component Registry & Usage

All components are located in [`references/Components.kt`](references/Components.kt) and can be used directly in any Jetpack Compose or CMP screen:

### 1. `BrandButton`
High-contrast neo-brutalist buttons with solid offset block shadows and kinetic press physics:
```kotlin
BrandButton(
    text = "Launch Platform",
    onClick = { launch() },
    variant = BrandButtonVariant.Primary
)

BrandButton(
    text = "View Architecture",
    onClick = { openSpecs() },
    variant = BrandButtonVariant.Outline
)
```

### 2. `StatusBadge`
Tokyo neo-brutalist status badges with solid 2dp drop shadow:
```kotlin
StatusBadge(label = "GDE Android", variant = StatusBadgeVariant.Pop)
StatusBadge(label = "Experimental", variant = StatusBadgeVariant.Secondary)
```

### 3. `CLICommandPill`
Terminal command execution container with solid 4dp cyan left border rule:
```kotlin
CLICommandPill(
    command = "klaat compile --source ./spec.gdoc",
    onCopy = { copyToClipboard("klaat compile --source ./spec.gdoc") }
)
```

### 4. `SlidingTabs`
Accessible neo-brutalist sliding tabs with Caution Yellow pop shadow on active Cyan pill:
```kotlin
var selectedTab by remember { mutableStateOf("Overview") }
val tabs = listOf("Overview", "Architecture", "Telemetry", "Specs")

SlidingTabs(
    tabs = tabs,
    selectedTab = selectedTab,
    onTabSelected = { selectedTab = it },
    tabLabel = { it }
)
```

### 5. `FeatureCard` & `TokyoCard`
Solid block shadow card containers with 2dp border rule:
```kotlin
FeatureCard(
    title = "High-Performance Runtimes",
    description = "Optimized GraalVM native image binaries delivering sub-10ms cold starts."
)

TokyoCard {
    Text(
        text = "Deep Architecture Ledger",
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black)
    )
}
```

### 6. `ArtifactRow`
Full-bleed software showcase row with tag, title, summary, and action link:
```kotlin
ArtifactRow(
    tag = "Tooling / 2026",
    badge = "GraalVM Native",
    title = "Klaat",
    summary = "High-performance CLI compiler converting Google Docs into interactive codelabs.",
    cliCommand = "klaat compile --source ./spec.gdoc",
    actionTitle = "Launch codelab",
    onAction = { openKlaat() }
)
```

### 7. `StanceView`
Architectural invariant statement with hairline bottom divider:
```kotlin
StanceView(
    title = "Declarative State Performance",
    bodyText = "UI is a pure reflection of state. Hoisting state cleanly guarantees 120Hz frame rates."
)
```

### 8. `KeynoteRow`
Conference presentation ledger row:
```kotlin
KeynoteRow(
    year = "2026",
    location = "Sydney",
    title = "High-Performance Multiplatform Runtimes",
    event = "Android Developer Summit",
    onOpen = { openTalk() }
)
```

### 9. `BrandStatement`
Authoritative editorial statement with 3dp Cyan left accent rule:
```kotlin
BrandStatement(
    quote = "Code is an artifact of thought; design is the discipline of making it inevitable.",
    citation = "Kartik Arora · Lead Engineer"
)
```

### 10. `SpecCard` & `TelemetryMetric`
Technical specification ledger and high-impact metric counters:
```kotlin
SpecCard(
    title = "Runtime Specifications",
    specs = listOf("Target" to "CMP", "Architecture" to "ARM64", "Startup" to "8.2ms")
)

TelemetryMetric(
    value = "150K+",
    label = "Active Developer Sessions"
)
```

---

## 🗂 Reference Implementation Files

- [`references/Color.kt`](references/Color.kt) &mdash; Material 3 ColorScheme tokens with dynamic light/dark support
- [`references/Typography.kt`](references/Typography.kt) &mdash; Chivo & JetBrains Mono typography definition
- [`references/Shape.kt`](references/Shape.kt) &mdash; Corner radii tokens (`KartikAroraShapes`)
- [`references/Spacing.kt`](references/Spacing.kt) &mdash; 8dp spacing scale (`MaterialTheme.spacing`)
- [`references/Components.kt`](references/Components.kt) &mdash; Complete 12-component production library
- [`references/Theme.kt`](references/Theme.kt) &mdash; `KartikAroraTheme` composable entry point
