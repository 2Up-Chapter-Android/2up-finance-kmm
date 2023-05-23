# 2Up Finance
Personal finance app

## Tech stack
- Kotlin Multiplatform Mobile (KMM)
- Kotlin
- Dependency injection: Koin
- Jetbrain Compose 
- Kotlin Flow
- Connect to network: Ktor & Ktorfit
- Database: SQLDelight
- Kotlinx serialization
- Navigation: Voyager
- Resource: Mobile Kotlin resources (moko)
  - String: Create a file strings.xml in commonMain/resources/MR/base. Usage: stringResource(MR.strings.my_string.desc().localized())
  - Image/SVG: Place in the commonMain/resources/MR/images directory. Nested directories are also supported. Usage: painterResource(MR.images.my_image)
  - Color: Colors resources directory is commonMain/resources/MR/colors. Usage: colorResource(MR.colors.myColor
- MVVM: Replace ViewModel with ScreenModel from Voyager (to use coroutine scope)

## Conventions
- Commit: `feat|fix|chore: [TG-123] commit name`
- Branch: `feature/TG-123-branch-name`, `bugfix/TG-123-branch-name`, `hotfix/TG-123-branch-name`
- PR title: `TG-123: Pull request title`
