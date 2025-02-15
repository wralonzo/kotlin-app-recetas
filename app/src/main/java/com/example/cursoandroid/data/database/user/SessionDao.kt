package com.example.cursoandroid.data.database.user;


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sesion: Session)

    @Query("SELECT * FROM tbl_sessions WHERE id = 1 LIMIT 1")
    suspend fun findOne(): Session?

    @Query("DELETE FROM tbl_sessions")
    suspend fun clearSession()

    @Update
    suspend fun update(sesion: Session)

    @Query("SELECT * FROM tbl_sessions LIMIT 1")
    fun findOneUser(): kotlinx.coroutines.flow.Flow<Session>
}