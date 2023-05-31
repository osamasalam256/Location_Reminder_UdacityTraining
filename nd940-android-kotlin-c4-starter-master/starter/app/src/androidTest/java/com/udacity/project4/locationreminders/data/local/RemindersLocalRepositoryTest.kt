package com.udacity.project4.locationreminders.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
<<<<<<< HEAD
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
=======
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.FakeReminderDao
import com.udacity.project4.FakeRemindersRepository
import com.udacity.project4.MainCoroutineRule
>>>>>>> a2f1b96 (update project with testing)
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
<<<<<<< HEAD
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
=======
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.junit.Assert
>>>>>>> a2f1b96 (update project with testing)
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Medium Test to test the repository
@MediumTest
class RemindersLocalRepositoryTest {

//    TODO: Add testing implementation to the RemindersLocalRepository.kt
<<<<<<< HEAD

}
=======
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeRemindersDao: FakeReminderDao
    private lateinit var fakeRemindersRepository: FakeRemindersRepository

    val list = listOf<ReminderDTO>(
        ReminderDTO("title1", "description1", "location1", 1.0, 10.0),
        ReminderDTO("title2","description2","location2",2.0,20.0),
        ReminderDTO("title3","description3","location3", 3.0, 30.0),
        ReminderDTO("title4","description4","location4", 4.0, 40.0)
    )
    val reminder1 = list[0]
    val reminder2 = list[1]
    val reminder3 = list[2]
    val reminder4 = list[3]
    @Before
    fun setup() {
        fakeRemindersDao = FakeReminderDao()
        fakeRemindersRepository = FakeRemindersRepository(fakeRemindersDao, Dispatchers.Unconfined)
    }

    @Test
    fun savesToLocalCache() = runBlockingTest {
        // When a reminder is saved to the tasks repository
        fakeRemindersRepository.saveReminder(reminder4)

        var list = mutableListOf<ReminderDTO>()
        list.addAll(fakeRemindersDao.remindersData.values)
        // Then the local sources are called and the cache is updated
        Assert.assertThat(fakeRemindersRepository.remindersServiceData.values.contains(reminder4) ,CoreMatchers.`is` ( true) )
        val result = fakeRemindersRepository.getReminders() as? Result.Success
        Assert.assertThat<ReminderDTO>(result?.data?.first(), CoreMatchers.`is` (reminder4))
    }
    @Test
    fun getReminderByIdThatExistsInLocalCache() = runBlockingTest {
        // given addition of a reminder
        fakeRemindersRepository.saveReminder(reminder1)
        // When reminder is fetch by id
        val getReminder = (fakeRemindersRepository.getReminder(reminder1.id) as? Result.Success)?.data
        // Then test should be done for all properties of reminder
        Assert.assertThat<ReminderDTO>(getReminder, CoreMatchers.notNullValue())
        Assert.assertThat(getReminder?.id, CoreMatchers.`is`(reminder1.id))
        Assert.assertThat(getReminder?.title, CoreMatchers.`is`(reminder1.title))
        Assert.assertThat(getReminder?.description, CoreMatchers.`is`(reminder1.description))
        Assert.assertThat(getReminder?.location, CoreMatchers.`is`(reminder1.location))
        Assert.assertThat(getReminder?.latitude, CoreMatchers.`is`(reminder1.latitude))
        Assert.assertThat(getReminder?.longitude, CoreMatchers.`is`(reminder1.longitude))
    }
    @Test
    fun getReminderByIdThatDoesNotExistInLocalCache() = runBlockingTest {

        val message = (fakeRemindersRepository.getReminder(reminder1.id) as? Result.Error)?.message
        Assert.assertThat<String>(message, CoreMatchers.notNullValue())
        Assert.assertThat(message, CoreMatchers.`is`("Could not find reminder"))

    }

    @Test
    fun deleteAllReminders_EmptyListFetchedFromLocalCache() = runBlockingTest {
        // Given addition of 3 reminders
        fakeRemindersDao.saveReminder(reminder1)
        fakeRemindersDao.saveReminder(reminder2)
        fakeRemindersDao.saveReminder(reminder3)

        // When - reminders are fetched from  repository - should not be empty
        Assert.assertThat((fakeRemindersRepository.getReminders() as? Result.Success)?.data?.size , CoreMatchers.not(0))

        fakeRemindersRepository.deleteAllReminders()

        // Then - fetching should return empty list after delete all reminders
        Assert.assertThat((fakeRemindersRepository.getReminders() as? Result.Success)?.data?.size , CoreMatchers.`is`(0))
    }

}

>>>>>>> a2f1b96 (update project with testing)
