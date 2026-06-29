package com.example.kapitalchallenge.architecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kapitalchallenge.architecture.data.disney.DisneyCharactersData
import kotlinx.coroutines.flow.Flow

@Dao
interface DisneyCharacterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(
        characters: List<DisneyCharactersData>
    )

    @Query("""
        SELECT * 
        FROM disney_characters
        ORDER BY Id ASC
    """)
    fun getAllCharactersFlow(): Flow<List<DisneyCharactersData>>

    @Query("""
        SELECT * 
        FROM disney_characters
        WHERE isFavorite = 1
        ORDER BY Id ASC
    """)
    fun getFavoritesFlow(): Flow<List<DisneyCharactersData>>

    @Query("""
        UPDATE disney_characters
        SET isFavorite = NOT isFavorite
        WHERE Id = :id
    """)
    suspend fun toggleFavorite(id: Int)

    @Query("""
        SELECT isFavorite
        FROM disney_characters
        WHERE Id = :id
    """)
    suspend fun isFavorite(id: Int): Boolean

    @Query("DELETE FROM disney_characters")
    suspend fun clearAll()

    @Query("""
        SELECT COUNT(*)
        FROM disney_characters
    """)
    suspend fun countCharacters(): Int
}