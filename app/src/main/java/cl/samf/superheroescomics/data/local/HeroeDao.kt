package cl.samf.superheroescomics.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlin.coroutines.Continuation

@Dao
interface HeroeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroe(heroeEntity: List<HeroeEntity>)

    @Query("select * from tableheroe order by id ASC")
    fun getHeroe(): LiveData<List<HeroeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailsHeroe(detailsHeroeEntity: DetailsHeroeEntity)

    @Query("select * from tabledetails where id = :id")
    fun getDetailsHeroe(id: Int): LiveData<DetailsHeroeEntity>
}