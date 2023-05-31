package com.udacity.project4

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

//Use FakeDataSource that acts as a test double to the LocalDataSource
//Use FakeDataSource that acts as a test double to the LocalDataSource
@Suppress("UNREACHABLE_CODE")
class FakeDataSource (val reminders: MutableList<ReminderDTO>? = mutableListOf()):
    ReminderDataSource {

//    Create a fake data source to act as a double to the real data source

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        // Return the reminders
        reminders?.let {
            return Result.Success(it)
        }
        return Result.Error("No reminders found.")
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        //save the reminder
        reminders?.add(reminder)
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        //return the reminder with the id
        reminders?.firstOrNull(){it.id == id}?.let { return Result.Success(it) }
        return Result.Error("No reminder found with id: $id")
    }

    override suspend fun deleteAllReminders() {
        // delete all the reminders
        reminders?.clear()
    }


}