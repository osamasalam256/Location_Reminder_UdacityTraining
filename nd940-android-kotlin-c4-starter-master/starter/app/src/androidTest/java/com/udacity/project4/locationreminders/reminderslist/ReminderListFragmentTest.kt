package com.udacity.project4.locationreminders.reminderslist

<<<<<<< HEAD
import android.content.Context
=======
>>>>>>> a2f1b96 (update project with testing)
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
<<<<<<< HEAD
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
=======
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.FakeDataSource
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito
>>>>>>> a2f1b96 (update project with testing)

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
//UI Testing
@MediumTest
<<<<<<< HEAD
class ReminderListFragmentTest {

//    TODO: test the navigation of the fragments.
//    TODO: test the displayed data on the UI.
//    TODO: add testing for the error messages.
}
=======
class ReminderListFragmentTest :
    AutoCloseKoinTest(){

        private lateinit var fakeDataSource: FakeDataSource
        private lateinit var reminderListViewModel: RemindersListViewModel
    val list = listOf<ReminderDTO>(
        ReminderDTO("title1", "description1", "location1", 1.0, 10.0),
        ReminderDTO("title2", "description2", "location2", 2.0, 20.0),
        ReminderDTO("title3", "description3", "location3", 3.0, 30.0),
        ReminderDTO("title4", "description4", "location4", 4.0, 40.0)
    )

    @Before
    fun setup() = runBlockingTest {


        stopKoin()
        val myModule = module {
                single {
                    reminderListViewModel
                }
            }
            // new koin module
            startKoin {
                modules(listOf(myModule))
            }

            val reminder1 = list[0]
            val reminder2 = list[1]
            val reminder3 = list[2]

            val remindersList = mutableListOf(reminder1, reminder2, reminder3)
            fakeDataSource = FakeDataSource(remindersList)
            reminderListViewModel =
                RemindersListViewModel(ApplicationProvider.getApplicationContext(), fakeDataSource)

        }

        @After
        fun turnDownKoin() {
            stopKoin()
        }
    //     test the displayed data on the UI.
        @Test
        fun displayRemindersList() = runBlockingTest {


            // Given data of 4 reminders
            val reminders = (fakeDataSource.getReminders() as? Result.Success)?.data
            launchFragmentInContainer<ReminderListFragment>(Bundle(), R.style.AppTheme)
            val secondItem = reminders!![1]
            // then check is the reminder is displayed
            Espresso.onView(withText(secondItem.location)).check(matches(isDisplayed()))


        }
    //     test the navigation of the fragments.
        @Test
        fun navigateToAddReminder() = runBlockingTest {
            // WHEN - Details fragment launched to display task
            val scenario =
                launchFragmentInContainer<ReminderListFragment>(Bundle(), R.style.AppTheme)
            val navController = Mockito.mock(NavController::class.java)
            scenario.onFragment {
                Navigation.setViewNavController(it.view!!, navController)
            }

            Espresso.onView(ViewMatchers.withId(R.id.addReminderFAB)).perform(ViewActions.click())

            Mockito.verify(navController).navigate(ReminderListFragmentDirections.toSaveReminder())
        }
    //    add testing for the error messages.
        @Test
        fun errorSnackBackShown() = runBlockingTest {
            fakeDataSource.deleteAllReminders()
            // WHEN - Details fragment launched to display task
            launchFragmentInContainer<ReminderListFragment>(Bundle(), R.style.AppTheme)
            Thread.sleep(1000)
            Espresso.onView(withText("No Data"))
                .check(matches(isDisplayed()))
        }
    }
>>>>>>> a2f1b96 (update project with testing)
