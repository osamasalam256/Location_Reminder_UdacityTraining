package com.udacity.project4.locationreminders.savereminder

<<<<<<< HEAD
import androidx.test.ext.junit.runners.AndroidJUnit4

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
=======
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.R
import com.udacity.project4.locationreminders.MainCoroutineRule
import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.getOrAwaitValue
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
>>>>>>> a2f1b96 (update project with testing)

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SaveReminderViewModelTest {


<<<<<<< HEAD
    //TODO: provide testing to the SaveReminderView and its live data objects

=======
    // provide testing to the SaveReminderView and its live data objects
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeDataSource: FakeDataSource
    private lateinit var saveReminderViewModel: SaveReminderViewModel
    private val list = listOf<ReminderDataItem>(ReminderDataItem("title", "description","location",(880.56),(120.45)))
    private val firstRm = list[0]

    @After
    fun turnDown() {
        stopKoin()
    }

    @Test
    fun save_LoadData_check(){
        fakeDataSource = FakeDataSource()
        saveReminderViewModel = SaveReminderViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)
        mainCoroutineRule.pauseDispatcher()
        saveReminderViewModel.validateAndSaveReminder(firstRm)
        Assert.assertThat(saveReminderViewModel.showLoading.getOrAwaitValue(), `is`(true))
    }

    @Test
    fun error_message_result(){
        fakeDataSource = FakeDataSource(null)
        saveReminderViewModel = SaveReminderViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)
        firstRm.title = null
        saveReminderViewModel.validateAndSaveReminder(firstRm)
        Assert.assertThat(saveReminderViewModel.showSnackBarInt.getOrAwaitValue(), `is`(
            R.string.err_enter_title))
    }
>>>>>>> a2f1b96 (update project with testing)

}