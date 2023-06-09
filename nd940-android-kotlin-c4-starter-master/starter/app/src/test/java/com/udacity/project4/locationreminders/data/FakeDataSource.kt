package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result

//Use FakeDataSource that acts as a test double to the LocalDataSource
<<<<<<< HEAD
class FakeDataSource : ReminderDataSource {

//    TODO: Create a fake data source to act as a double to the real data source

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        TODO("Return the reminders")
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        TODO("save the reminder")
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        TODO("return the reminder with the id")
    }

    override suspend fun deleteAllReminders() {
        TODO("delete all the reminders")
=======
//Use FakeDataSource that acts as a test double to the LocalDataSource
@Suppress("UNREACHABLE_CODE")
class FakeDataSource (val reminders: MutableList<ReminderDTO>? = mutableListOf()): ReminderDataSource {

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
>>>>>>> a2f1b96 (update project with testing)
    }


}