package com.udacity.project4.locationreminders.reminderslist

<<<<<<< HEAD
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
=======
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.locationreminders.MainCoroutineRule
import com.udacity.project4.locationreminders.data.FakeDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNot
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
>>>>>>> a2f1b96 (update project with testing)

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RemindersListViewModelTest {

<<<<<<< HEAD
    //TODO: provide testing to the RemindersListViewModel and its live data objects
=======
    // testing to the RemindersListViewModel and its live data objects
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakeDataSource: FakeDataSource
    private lateinit var reminderListViewModel: RemindersListViewModel

    val list = listOf<ReminderDTO>(
        ReminderDTO("title1", "description1", "location1", 1.0, 10.0),
        ReminderDTO("title2","description2","location2",2.0,20.0),
        ReminderDTO("title3","description3","location3", 3.0, 30.0),
        ReminderDTO("title4","description4","location4", 4.0, 40.0)
    )
    @Before
    fun viewModel_Setup(){
        val reminder1 = list[0]
        val reminder2 = list[1]
        val reminder3 = list[2]

        val remindersList = mutableListOf(reminder1, reminder2, reminder3)
        fakeDataSource = FakeDataSource(remindersList)

    }


    @After
    fun turnDown() {
        stopKoin()
    }

    @Test
    fun getRemindersList() {
        reminderListViewModel = RemindersListViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)
        reminderListViewModel.loadReminders()
        Assert.assertThat(reminderListViewModel.remindersList.getOrAwaitValue(), (IsNot.not(emptyList())))
        Assert.assertThat(reminderListViewModel.remindersList.getOrAwaitValue().size, `is`(3))
    }
    @Test
    fun check_loading() {
        fakeDataSource = FakeDataSource()
        reminderListViewModel = RemindersListViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)
        mainCoroutineRule.pauseDispatcher()
        reminderListViewModel.loadReminders()
        Assert.assertThat(reminderListViewModel.showLoading.getOrAwaitValue(), `is`(true)
        )
    }

    @Test
    fun error_message_result(){
        fakeDataSource = FakeDataSource(null)
        reminderListViewModel = RemindersListViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)
        reminderListViewModel.loadReminders()
        Assert.assertThat(reminderListViewModel.showSnackBar.getOrAwaitValue(),`is`("No reminders found."))
    }
>>>>>>> a2f1b96 (update project with testing)

}