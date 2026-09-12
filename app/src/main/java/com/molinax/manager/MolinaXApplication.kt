package com.molinax.manager

import android.app.Application

/**
 * Application root MolinaX Manager.
 *
 * Titik inisialisasi lintas-subsystem (Player, Editor, Terminal, Utilities)
 * ditambahkan di sini saat masing-masing subsystem diimplementasikan
 * (Phase 4-8), mengikuti prinsip App Host di MolinaX-Manager-Blueprint-v1.md
 * SS5: App Host tidak boleh tahu detail internal subsystem.
 */
class MolinaXApplication : Application()
