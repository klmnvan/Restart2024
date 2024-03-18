package com.example.restartchempionat2024.objects

import com.example.restartchempionat2024.models.Profile
import com.example.restartchempionat2024.objects.SupabaseClient.supabase
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from
import java.util.Locale.filter

/** Requests - объект, в котором находятся функции для отправления запросов к Supabase */
object Requests {

    /** В функции просиходит отправка запроса для регистрации */
    suspend fun signUp(emailUser: String, passwordUser: String) {
        supabase.auth.signUpWith(Email) {
            password = passwordUser
            email = emailUser
        }
    }

    /** Функиця возвращает текущий профиль по email */
    suspend fun getProfile(emailUser: String): Profile {
        return supabase.from("profiles").select()
        {
            filter {
                eq("email", emailUser)
            }
        }.decodeSingle<Profile>()
    }

    /** Функиця для вставки профиля в БД */
    suspend fun updateProfile(name: String, phone: String, uuid: String) {
        supabase.from("profiles").update({
            set("name", name)
            set("phone", phone)
        }) {
            filter {
                eq("uuid", uuid)
            }
        }
    }

    /** В функции просиходит отправка запроса для авторизации */
    suspend fun signIn(emailUser: String, passwordUser: String) {
        supabase.auth.signInWith(Email){
            email = emailUser
            password = passwordUser
        }
    }

    /** В функции просиходит отправка запроса на отправление кода верификации пользователю на почту */
    suspend fun sendOtp(emailUser: String){
        supabase.auth.signInWith(OTP){
            email = emailUser
            createUser = false
        }
    }

    /** В функции просиходит отправка запроса на проверку кода верификации в supabase */
    suspend fun verifyCode (emailUser: String, codeUser: String){
        supabase.auth.verifyEmailOtp(
            type = OtpType.Email.MAGIC_LINK,
            email = emailUser,
            token = codeUser
        )
    }

    /** В функции просиходит отправка запроса на выход из аккаунта */
    suspend fun logOut() {
        supabase.auth.signOut()
    }

    /** В функции просиходит отправка запроса на сброс пароля */
    suspend fun resetPassword (emailUser: String){
        supabase.auth.resetPasswordForEmail(emailUser)
    }

    /** В функции просиходит отправка запроса на изменение пароля */
    suspend fun updatePassword (newPassword: String){
        supabase.auth.modifyUser (true) {
            password = newPassword
        }
    }

    /** Функиця возвращает статус текущей сессии */
    fun getCurrentSession(): String{
        return supabase.auth.sessionStatus.value.toString()
    }

}