package me.kartikarora.icanhazstream.ui.components

import me.kartikarora.icanhazstream.ui.theme.*

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Brand Motion Guideline: Fast 0.22s Android standard emphasized transition.
 */
val FastTransitionSpec: TweenSpec<Float> = tween(
    durationMillis = 220,
    easing = FastOutSlowInEasing
)

/**
 * 1. BrandButton (Tokyo Neo-Brutalist Kinetic Button)
 * Features:
 * - 2.dp solid rule outline
 * - Solid offset block drop shadow (Caution Yellow for Primary, Akihabara Cyan for Secondary)
 * - Micro-kinetic press offset (1.dp translation)
 */
enum class BrandButtonVariant {
    Primary, Secondary, Outline, Ghost, Small
}

@Composable
fun BrandButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: BrandButtonVariant = BrandButtonVariant.Primary,
    icon: (@Composable () -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val signal = MaterialTheme.colorScheme.primary
    val signalSecondary = MaterialTheme.colorScheme.secondary
    val onSignal = MaterialTheme.colorScheme.onPrimary
    val onSignalSecondary = MaterialTheme.colorScheme.onSecondary
    val surface = MaterialTheme.colorScheme.surface
    val onSurface = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    val (bgColor, textColor, borderColor, shadowColor, defaultShadowOffset) = when (variant) {
        BrandButtonVariant.Primary -> listOf(signal, onSignal, strongRule, signalSecondary, 4.dp)
        BrandButtonVariant.Secondary -> listOf(signalSecondary, onSignalSecondary, strongRule, signal, 4.dp)
        BrandButtonVariant.Outline -> listOf(surface, onSurface, strongRule, strongRule, 3.dp)
        BrandButtonVariant.Ghost -> listOf(Color.Transparent, onSurfaceVariant, Color.Transparent, Color.Transparent, 0.dp)
        BrandButtonVariant.Small -> listOf(MaterialTheme.colorScheme.surfaceVariant, onSurface, strongRule, strongRule, 2.dp)
    }

    val baseShadowOffset = defaultShadowOffset as androidx.compose.ui.unit.Dp
    val targetShadowOffset = when {
        isPressed -> 1.dp
        isHovered && baseShadowOffset > 0.dp -> baseShadowOffset + 2.dp
        else -> baseShadowOffset
    }
    val targetTranslation = when {
        isPressed -> 2.dp
        isHovered && baseShadowOffset > 0.dp -> (-1).dp
        else -> 0.dp
    }
    val shadowOffsetDp by animateDpAsState(
        targetValue = targetShadowOffset,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "brandButtonShadow"
    )
    val pressTranslation by animateDpAsState(
        targetValue = targetTranslation,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "brandButtonTranslation"
    )
    val activeBorderColor = if (isFocused) signal else (borderColor as Color)

    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource)
            .focusable(interactionSource = interactionSource)
            .graphicsLayer {
                translationX = pressTranslation.toPx()
                translationY = pressTranslation.toPx()
            }
            .drawBehind {
                if (shadowColor != Color.Transparent) {
                    drawRoundRect(
                        color = shadowColor as Color,
                        topLeft = Offset(shadowOffsetDp.toPx(), shadowOffsetDp.toPx()),
                        size = size,
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                    )
                }
            }
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor as Color)
            .then(
                if (activeBorderColor != Color.Transparent) {
                    Modifier.border(if (isFocused) 2.5.dp else 2.dp, activeBorderColor, RoundedCornerShape(6.dp))
                } else Modifier
            )
            .clickable(interactionSource = interactionSource, indication = null, onClick = onClick)
            .padding(
                horizontal = if (variant == BrandButtonVariant.Small) 12.dp else 20.dp,
                vertical = if (variant == BrandButtonVariant.Small) 6.dp else 10.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            icon?.let { it() }
            Text(
                text = text,
                style = if (variant == BrandButtonVariant.Small) {
                    MaterialTheme.typography.labelSmall.copy(
                        fontFamily = JetBrainsMonoFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    MaterialTheme.typography.labelMedium.copy(
                        fontFamily = JetBrainsMonoFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                color = textColor as Color
            )
        }
    }
}

/**
 * 2. StatusBadge (Tokyo Neo-Brutalist Status Tag)
 * Features:
 * - 6.dp corner radius
 * - Solid 2.dp block shadow
 * - High-contrast uppercase JetBrains Mono typography
 */
enum class StatusBadgeVariant {
    Pop, Secondary, Win, Loss, Draft
}

@Composable
fun StatusBadge(
    label: String,
    modifier: Modifier = Modifier,
    variant: StatusBadgeVariant = StatusBadgeVariant.Pop
) {
    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val signal = MaterialTheme.colorScheme.primary
    val signalSecondary = MaterialTheme.colorScheme.secondary
    val onSignal = MaterialTheme.colorScheme.onPrimary
    val onSignalSecondary = MaterialTheme.colorScheme.onSecondary
    val danger = MaterialTheme.colorScheme.error

    val (bg, textColor, border) = when (variant) {
        StatusBadgeVariant.Pop, StatusBadgeVariant.Win -> listOf(signal, onSignal, signal)
        StatusBadgeVariant.Secondary -> listOf(signalSecondary, onSignalSecondary, signalSecondary)
        StatusBadgeVariant.Loss -> listOf(danger.copy(alpha = 0.18f), danger, danger.copy(alpha = 0.35f))
        StatusBadgeVariant.Draft -> listOf(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant, MaterialTheme.colorScheme.outline)
    }

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = strongRule,
                    topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(bg as Color)
            .border(1.dp, border as Color, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(
                fontFamily = JetBrainsMonoFontFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
            ),
            color = textColor as Color
        )
    }
}

/**
 * 3. CLICommandPill
 * Features:
 * - Sunken code background
 * - 4.dp solid Akihabara Cyan left border
 * - Monospace command presentation with copy interaction
 */
@Composable
fun CLICommandPill(
    command: String,
    onCopy: () -> Unit,
    modifier: Modifier = Modifier
) {
    val signal = MaterialTheme.colorScheme.primary
    val rule = MaterialTheme.colorScheme.outline
    val surfaceSunken = MaterialTheme.colorScheme.surfaceVariant
    val onSurface = MaterialTheme.colorScheme.onSurface

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(surfaceSunken)
            .border(1.dp, rule, RoundedCornerShape(6.dp))
            .drawBehind {
                val strokeWidth = 4.dp.toPx()
                drawLine(
                    color = signal,
                    start = Offset(strokeWidth / 2, 0f),
                    end = Offset(strokeWidth / 2, size.height),
                    strokeWidth = strokeWidth
                )
            }
            .clickable(onClick = onCopy)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$",
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = JetBrainsMonoFontFamily,
                fontWeight = FontWeight.Bold
            ),
            color = signal
        )
        Text(
            text = command,
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = JetBrainsMonoFontFamily,
                fontWeight = FontWeight.SemiBold
            ),
            color = onSurface
        )
    }
}

/**
 * 4. ArtifactRow (Full-bleed Software Showcase Row)
 */
@Composable
fun ArtifactRow(
    tag: String,
    title: String,
    summary: String,
    modifier: Modifier = Modifier,
    badge: String? = null,
    cliCommand: String? = null,
    actionTitle: String? = null,
    onAction: (() -> Unit)? = null
) {
    val spacing = MaterialTheme.spacing
    val signal = MaterialTheme.colorScheme.primary
    val rule = MaterialTheme.colorScheme.outline
    val onSurface = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = rule,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(vertical = spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.width(140.dp)) {
                Text(
                    text = tag,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontFamily = JetBrainsMonoFontFamily,
                        fontWeight = FontWeight.Bold
                    ),
                    color = signal
                )
                badge?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontFamily = JetBrainsMonoFontFamily
                        ),
                        color = onSurfaceVariant
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = (-0.02).sp
                        ),
                        color = onSurface
                    )
                    actionTitle?.let { title ->
                        onAction?.let { action ->
                            Text(
                                text = title,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = JetBrainsMonoFontFamily,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = signal,
                                modifier = Modifier.clickable(onClick = action)
                            )
                        }
                    }
                }
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodyMedium,
                    color = onSurfaceVariant,
                    modifier = Modifier.padding(top = spacing.extraSmall)
                )
                cliCommand?.let { cmd ->
                    CLICommandPill(
                        command = cmd,
                        onCopy = {},
                        modifier = Modifier.padding(top = spacing.small)
                    )
                }
            }
        }
    }
}

/**
 * 5. StanceView (Architectural Invariant)
 */
@Composable
fun StanceView(
    title: String,
    bodyText: String,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val rule = MaterialTheme.colorScheme.outline
    val onSurface = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = rule,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(vertical = spacing.medium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = (-0.02).sp
            ),
            color = onSurface,
            modifier = Modifier.padding(bottom = spacing.extraSmall)
        )
        Text(
            text = bodyText,
            style = MaterialTheme.typography.bodyMedium,
            color = onSurfaceVariant
        )
    }
}

/**
 * 6. KeynoteRow (Symposium Ledger)
 */
@Composable
fun KeynoteRow(
    year: String,
    location: String,
    title: String,
    event: String,
    modifier: Modifier = Modifier,
    onOpen: (() -> Unit)? = null
) {
    val spacing = MaterialTheme.spacing
    val rule = MaterialTheme.colorScheme.outline
    val signal = MaterialTheme.colorScheme.primary
    val onSurface = MaterialTheme.colorScheme.onSurface
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    Row(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawLine(
                    color = rule,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .clickable(enabled = onOpen != null, onClick = { onOpen?.invoke() })
            .padding(vertical = spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.width(130.dp)) {
            Text(
                text = "$year · $location",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = JetBrainsMonoFontFamily,
                    fontWeight = FontWeight.Bold
                ),
                color = signal
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = onSurface
            )
            Text(
                text = event,
                style = MaterialTheme.typography.bodySmall,
                color = onSurfaceVariant
            )
        }
    }
}

/**
 * 7. BrandStatement (Authoritative Editorial Statement)
 */
@Composable
fun BrandStatement(
    quote: String,
    modifier: Modifier = Modifier,
    citation: String? = null
) {
    val signalColor = MaterialTheme.colorScheme.primary
    val spacing = MaterialTheme.spacing

    Column(
        modifier = modifier
            .drawBehind {
                val strokeWidth = 3.dp.toPx()
                drawLine(
                    color = signalColor,
                    start = Offset(strokeWidth / 2, 0f),
                    end = Offset(strokeWidth / 2, size.height),
                    strokeWidth = strokeWidth
                )
            }
            .padding(start = spacing.medium, top = spacing.extraSmall, bottom = spacing.extraSmall)
    ) {
        Text(
            text = quote,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.02).sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
        citation?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = JetBrainsMonoFontFamily
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = spacing.extraSmall)
            )
        }
    }
}

/**
 * 8. SpecCard (Engineering Spec Card)
 */
@Composable
fun SpecCard(
    title: String,
    specs: List<Pair<String, String>>,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val surface = MaterialTheme.colorScheme.surface

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = strongRule,
                    topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(surface)
            .border(2.dp, strongRule, RoundedCornerShape(6.dp))
            .padding(spacing.medium)
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = spacing.small)
            )
            specs.forEach { (key, value) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = spacing.extraSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = key,
                        style = MaterialTheme.typography.bodySmall.copy(fontFamily = JetBrainsMonoFontFamily),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = JetBrainsMonoFontFamily,
                            fontWeight = FontWeight.SemiBold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

/**
 * 9. TelemetryMetric
 */
@Composable
fun TelemetryMetric(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val surface = MaterialTheme.colorScheme.surface

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = strongRule,
                    topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(surface)
            .border(2.dp, strongRule, RoundedCornerShape(6.dp))
            .padding(spacing.medium)
    ) {
        Column {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Black
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = label.lowercase(),
                style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * 10. FeatureCard (Tokyo Neo-Brutalist Studio Feature Card)
 */
@Composable
fun FeatureCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    val spacing = MaterialTheme.spacing
    val cardBackground = MaterialTheme.colorScheme.surface
    val strongRule = MaterialTheme.colorScheme.outlineVariant

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = strongRule,
                    topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(cardBackground)
            .border(2.dp, strongRule, RoundedCornerShape(6.dp))
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(spacing.medium)
    ) {
        Column {
            icon?.let {
                Box(modifier = Modifier.padding(bottom = spacing.extraSmall)) {
                    it()
                }
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.02).sp
                ),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = spacing.extraSmall)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * 11. SlidingTabs (Accessible Tokyo Neo-Brutalist Tabs with Sliding Indicator)
 */
@Composable
fun <T> SlidingTabs(
    tabs: List<T>,
    selectedTab: T,
    onTabSelected: (T) -> Unit,
    tabLabel: (T) -> String,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val containerBg = MaterialTheme.colorScheme.surfaceVariant
    val signal = MaterialTheme.colorScheme.primary
    val signalSecondary = MaterialTheme.colorScheme.secondary
    val onSignal = MaterialTheme.colorScheme.onPrimary
    val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = strongRule,
                    topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(containerBg)
            .border(2.dp, strongRule, RoundedCornerShape(6.dp))
            .padding(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEach { tab ->
                val isSelected = tab == selectedTab
                Box(
                    modifier = Modifier
                        .then(
                            if (isSelected) {
                                Modifier
                                    .drawBehind {
                                        drawRoundRect(
                                            color = signalSecondary,
                                            topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                                            size = size,
                                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
                                        )
                                    }
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(signal)
                                    .border(1.dp, strongRule, RoundedCornerShape(4.dp))
                            } else {
                                Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color.Transparent)
                            }
                        )
                        .clickable { onTabSelected(tab) }
                        .padding(horizontal = spacing.medium, vertical = spacing.extraSmall)
                ) {
                    Text(
                        text = tabLabel(tab),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontFamily = JetBrainsMonoFontFamily,
                            fontWeight = FontWeight.Bold
                        ),
                        color = if (isSelected) onSignal else onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * 12. TokyoCard (Neo-brutalist Container with Hover & Press Tactile Motion)
 */
@Composable
fun TokyoCard(
    modifier: Modifier = Modifier,
    isContrast: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val spacing = MaterialTheme.spacing
    val cardBackground = MaterialTheme.colorScheme.surface
    val strongRule = MaterialTheme.colorScheme.outlineVariant
    val signal = MaterialTheme.colorScheme.primary

    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isPressed by interactionSource.collectIsPressedAsState()

    val targetOffset = when {
        isPressed -> 1.dp
        isHovered || isFocused -> 6.dp
        else -> 4.dp
    }
    val targetTranslation = when {
        isPressed -> 2.dp
        isHovered || isFocused -> (-2).dp
        else -> 0.dp
    }
    val shadowOffset by animateDpAsState(
        targetValue = targetOffset,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "tokyoCardShadowOffset"
    )
    val translation by animateDpAsState(
        targetValue = targetTranslation,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "tokyoCardTranslation"
    )
    val borderColor by animateColorAsState(
        targetValue = if (isHovered || isFocused || isPressed) signal else strongRule,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "tokyoCardBorder"
    )
    val shadowColor by animateColorAsState(
        targetValue = if (isHovered || isFocused) signal else strongRule,
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "tokyoCardShadowColor"
    )

    Box(
        modifier = modifier
            .hoverable(interactionSource = interactionSource)
            .focusable(interactionSource = interactionSource)
            .then(
                if (onClick != null) {
                    Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = onClick
                    )
                } else Modifier
            )
            .graphicsLayer {
                translationX = translation.toPx()
                translationY = translation.toPx()
            }
            .drawBehind {
                drawRoundRect(
                    color = shadowColor,
                    topLeft = Offset(shadowOffset.toPx(), shadowOffset.toPx()),
                    size = size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(cardBackground)
            .border(2.dp, borderColor, RoundedCornerShape(6.dp))
            .padding(spacing.large)
    ) {
        Column {
            content()
        }
    }
}
