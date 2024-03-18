package com.example.restartchempionat2024.objects

import com.example.chempionat2024.models.Order
import com.example.chempionat2024.models.OrderDestination
import com.example.chempionat2024.models.OriginDetail
import com.example.chempionat2024.models.PackageDetail
import com.example.restartchempionat2024.models.DestinationDetail
import com.example.restartchempionat2024.models.Profiles
import com.example.restartchempionat2024.objects.SupabaseClient.supabase
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import io.github.jan.supabase.postgrest.from

/** Requests - объект, в котором находятся функции для отправления запросов к Supabase */
object Requests {

    /** В функции просиходит отправка запроса для регистрации */
    suspend fun signUp(emailUser: String, passwordUser: String): String {
        return supabase.auth.signUpWith(Email) {
            password = passwordUser
            email = emailUser
        }!!.id
    }

    /** В функции просиходит запрос upsert к supabase на добавление или изменение пользователя */
    suspend fun upsertProfile(p: Profiles) {
        supabase.from("profiles").upsert(p)
    }


    /** Функиця возвращает текущий профиль по email */
    suspend fun getProfile(emailUser: String): Profiles {
        return supabase.from("profiles").select()
        {
            filter {
                eq("email", emailUser)
            }
        }.decodeSingle<Profiles>()
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

    /** Функиця вставляет, либо заменяет OriginDetail */
    suspend fun setOriginDet(data: OriginDetail): OriginDetail {
        return supabase.from("origin_details").upsert(data){
            select()
        }.decodeSingle<OriginDetail>()
    }

    /** Функиця вставляет, либо заменяет PackageDetail */
    suspend fun setPackDet(data: PackageDetail): PackageDetail {
        return supabase.from("package_details").upsert(data){
            select()
        }.decodeSingle<PackageDetail>()
    }

    /** Функиця вставляет, либо заменяет DestinationDetail */
    suspend fun setDestDet(data: DestinationDetail): DestinationDetail {
        return supabase.from("destination_details").upsert(data){
            select()
        }.decodeSingle<DestinationDetail>()
    }

    /** Функиця вставляет, либо заменяет Order */
    suspend fun setOrder(data: Order): Order {
        return supabase.from("orders").upsert(data){
            select()
        }.decodeSingle<Order>()
    }

    /** Функиця вставляет, либо заменяет OrderDestination */
    suspend fun setOrderDest(data: OrderDestination): OrderDestination {
        return supabase.from("order_destinations").upsert(data){
            select()
        }.decodeSingle<OrderDestination>()
    }

    /** Функиця получает заказ по id из supabase */
    suspend fun selOrder(id: Int): Order {
        return supabase.from("orders").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<Order>()
    }

    /** Функиця получает OriginDetail по id из supabase */
    suspend fun selOrDet(id: Int): OriginDetail {
        return supabase.from("origin_details").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<OriginDetail>()
    }

    /** Функиця получает PackageDetail по id из supabase */
    suspend fun selPackDet(id: Int): PackageDetail {
        return supabase.from("package_details").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<PackageDetail>()
    }

    /** Функиця получает PackageDetail по id из supabase */
    suspend fun selOrdDest(id: Int): List<OrderDestination> {
        return supabase.from("order_destinations").select {
            filter {
                eq("order_id", id)
            }
        }.decodeList<OrderDestination>()
    }

    /** Функиця получает PackageDetail по id из supabase */
    suspend fun selDestDet(id: Int): DestinationDetail {
        return supabase.from("destination_details").select {
            filter {
                eq("id", id)
            }
        }.decodeSingle<DestinationDetail>()
    }



}