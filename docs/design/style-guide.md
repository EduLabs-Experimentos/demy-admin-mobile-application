# Style Guide – Demy Android App

This document defines the **UI/UX design guidelines** for the Demy Android mobile application.  
It ensures a consistent look and feel across all screens, aligned with **Material Design 3**, accessibility (a11y), and internationalization (i18n) best practices.

---

## 1. Typography
We use **Google Fonts** integrated with Jetpack Compose.

- **Headings (H1, H2, H3)** → *Montserrat* (bold).
- **Body text** → *Poppins* (regular).
- **Captions/Labels** → *Poppins* (medium).

Example (MaterialTheme):
```
Typography(
    headlineLarge = TextStyle(fontFamily = Montserrat, fontWeight = FontWeight.Bold),
    bodyMedium = TextStyle(fontFamily = Poppins, fontWeight = FontWeight.Normal)
)
```

---

## 2. Color Palette
The app follows a **Material Design 3 color scheme**:

- **Primary:** `#3F51B5` (Indigo)
- **Secondary:** `#F57C00` (Orange)
- **Tertiary:** `#AB47BC` (Purple)
- **Background:** `#FFFFFF` (Light) / `#121212` (Dark mode)

---

## 3. Components
- Use **Material 3 Compose components** (Buttons, Cards, Navigation Bar).
- All interactive components must include:
    - **Minimum touch target:** 48x48 dp.
    - **Ripple effect** for tap feedback.
    - **Content description** for icons/images (a11y).

---

## 4. Layout and Spacing
- Follow an **8dp grid system** (margins, paddings, spacing).
- Use `Modifier.padding()` and `Modifier.fillMaxWidth()` consistently.
- Keep screens scrollable if content exceeds viewport.

---

## 5. Accessibility (a11y)
- Provide **contentDescription** for all non-text UI elements.
- Ensure **color contrast ratio ≥ 4.5:1** (WCAG AA).
- Support **dark theme**.
- Verify that the app works with **TalkBack** enabled.

---

## 6. Internationalization (i18n)
- All text must be defined in `strings.xml` (no hardcoded text).
- Provide base support for **English** and **Spanish**.
- Use `stringResource(id = R.string.example)` in Compose.


USAR BIEN LOS COLORCITOS CARAJO




























































































































































































































































