package com.touhidapps.hackathonproject

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.touhidapps.hackathonproject.repository.VideoRepository

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.touhidapps.hackathonproject", appContext.packageName)
//    }

    @Test
    fun getMovieData() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val r = VideoRepository(appContext as Application)

        r.discoverMovie(null, "1a97f3b8d5deee1d649c0025f3acf75c", "2020", "vote_average.desc") {
            assertTrue("Is more than 0 page Movie: ", it.totalPages > 0)
        }

        r.discoverTv(null, "1a97f3b8d5deee1d649c0025f3acf75c", "2020", "vote_average.desc") {
            assertTrue("Is more than 0 page TV Show: ", it.totalPages > 0)
        }

    }


}