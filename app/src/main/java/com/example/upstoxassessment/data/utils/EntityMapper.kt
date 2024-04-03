package com.example.upstoxassessment.data.utils

interface DomainMapper<T,DomainModel> {
  fun mapToDomainModel(data:T) : DomainModel
}