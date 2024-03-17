package com.example.restartchempionat2024.objects

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.ExternalAuthAction
import io.github.jan.supabase.postgrest.Postgrest

/** General - объект, в котором создаётся supabase клиент */
object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://ufssymwxlebmsbcxhkax.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVmc3N5bXd4bGVibXNiY3hoa2F4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDYyNzEzOTMsImV4cCI6MjAyMTg0NzM5M30.UANA1YRqNjZO7gw1IXhBmEp3NufjoSatn5qTr4YxGzA"
    ) {
        install(Auth)
        {
            scheme = "com.example.restartchempionat2024"
            host = "login-callback"
            alwaysAutoRefresh = true
            autoLoadFromStorage = true
            autoSaveToStorage = true
            defaultExternalAuthAction = ExternalAuthAction.CUSTOM_TABS
        }
        install(Postgrest)
    }

}