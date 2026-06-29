package com.example.kapitalchallenge.architecture.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData
import com.example.kapitalchallenge.architecture.data.local.DisneyCharacterDao
import com.example.kapitalchallenge.architecture.data.local.DisneyCharactersConverters
import com.example.kapitalchallenge.architecture.data.local.FavoriteEntity

@Database(
    entities = [DisneyCharactersData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DisneyCharactersConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun disneyCharacterDao(): DisneyCharacterDao
}