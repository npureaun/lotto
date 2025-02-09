package com.example.solution.domain.registration.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "REGISTRATION")
class Registration (
    @Column
    val number:String,

    @Column
    val lottoID:Long,

    @Column
    val createdAt:String=LocalDateTime.now().toString(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    ){

}