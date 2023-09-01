package cl.samf.superheroescomics.data

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import cl.samf.superheroescomics.data.local.HeroeDao
import cl.samf.superheroescomics.data.local.HeroeDataBase
import cl.samf.superheroescomics.data.local.HeroeEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class MapperKtTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var heroeDao: HeroeDao
    private lateinit var db: HeroeDataBase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, HeroeDataBase::class.java).build()
        heroeDao = db.getHeroeDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertBreeds_empty() = runBlocking {
        // Given
        val heroeList = listOf<HeroeEntity>()

        // When
        heroeDao.insertHeroe(heroeList)

        // Then A
        val it = heroeDao.getHeroe().getOrAwaitValue()
        assertThat(it).isNotNull()
        assertThat(it).isEmpty()

        // Then B
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()
        }
    }

    @Test
    fun insertBreeds_happyCase_1element() = runBlocking {
        // Given
        val heroeList = listOf(HeroeEntity(1,"name","santa","example.com", "fuerza", 2023))

        // When
        heroeDao.insertHeroe(heroeList)

        // Then
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(1)
        }
    }

    @Test
    fun insertBreeds_happyCase_3elements() = runBlocking {
        // Given
        val heroeList = listOf(HeroeEntity(2,"name2","santa2","example2.com", "fuerza2", 2024), HeroeEntity(3,"name3","santa3","example3.com", "fuerza3", 2025), HeroeEntity(4,"name4","santa4","example4.com", "fuerza4", 2026))

        // When
        heroeDao.insertHeroe(heroeList)

        // Then
        heroeDao.getHeroe().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isNotEmpty()
            assertThat(it).hasSize(3)
        }
    }
}


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserve.invoke()

        // Don't wait indefinitely if the LiveData is not set.
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}