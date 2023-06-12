package com.aibles.finance2upkmm

import java.awt.Desktop

actual fun getPlatform(): Platform = DesktopPlatform()

class DesktopPlatform : Platform {
    override val name: String
        get() = "ubuntu"
}
