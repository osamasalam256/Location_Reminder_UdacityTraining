package com.udacity.project4.locationreminders.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
<<<<<<< HEAD
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import com.udacity.project4.locationreminders.data.dto.ReminderDTO

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Test
=======
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.*
import org.junit.runner.RunWith
import java.util.*
>>>>>>> a2f1b96 (update project with testing)

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class RemindersDaoTest {

<<<<<<< HEAD
//    TODO: Add testing implementation to the RemindersDao.kt

=======
//    testing implementation to the RemindersDao.kt
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RemindersDatabase

    @Before
    fun setupDataBase() {
        // Using an in-memory database so that the information stored here disappears when the
        // process is killed.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RemindersDatabase::class.java
        ).build()
    }


    @After
    fun close_DataBase() = database.close()
    @Test
    fun insertRm_getReminders() = runBlockingTest {
        // GIVEN - insert a reminder to database
        val reminder = ReminderDTO("title", "description","location",234.0, 200.0)

        database.reminderDao().saveReminder(reminder)

        // WHEN - Get reminders from the database
        val reminders = database.reminderDao().getReminders()

        // THEN - There is only 1 reminder in the database
        Assert.assertThat(reminders.size, `is`(1))
        Assert.assertThat(reminders[0].id, `is`(reminder.id))
        Assert.assertThat(reminders[0].title, `is`(reminder.title))
        Assert.assertThat(reminders[0].description, `is`(reminder.description))
    }

    @Test
    fun insertReminder_GetReminderById() = runBlockingTest {
        // GIVEN - Insert a reminder.
        val reminder = ReminderDTO("title1", "description1","location1", 330.0, 440.0)
        database.reminderDao().saveReminder(reminder)

        // WHEN - Get the reminder by id from the database.
        val reminder2 = database.reminderDao().getReminderById(reminder.id)

        // THEN - The reminder2 data contains the expected values.
        Assert.assertThat<ReminderDTO>(reminder2 as ReminderDTO, notNullValue())
        Assert.assertThat(reminder2.id, `is`(reminder.id))
        Assert.assertThat(reminder2.title, `is`(reminder.title))
        Assert.assertThat(reminder2.description, `is`(reminder.description))
        Assert.assertThat(reminder2.location, `is`(reminder.location))
        Assert.assertThat(reminder2.latitude, `is`(reminder.latitude))
        Assert.assertThat(reminder2.longitude, `is`(reminder.longitude))
    }
    @Test
    fun getReminderByIdNotFound() = runBlockingTest {
        // GIVEN - a random reminder id
        val reminderId = "title12345"
        // WHEN - Get the reminder by id from the database.
        val reminder = database.reminderDao().getReminderById(reminderId)
        // THEN - The reminder data should be  null.
        Assert.assertNull(reminder)
    }

    @Test
    fun deleteReminders() = runBlockingTest {
        // Given - reminders inserted the same data base no need to add something

        // WHEN - deleting all reminders
        database.reminderDao().deleteAllReminders()

        // THEN - The list is empty
        val reminders = database.reminderDao().getReminders()
        Assert.assertThat(reminders.isEmpty(), `is`(true))
    }
>>>>>>> a2f1b96 (update project with testing)
}