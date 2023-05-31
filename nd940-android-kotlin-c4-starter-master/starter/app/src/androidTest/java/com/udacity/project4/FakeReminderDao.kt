package com.udacity.project4

import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.local.RemindersDao


class FakeReminderDao : RemindersDao {



    private var returnError = false

    val remindersData: LinkedHashMap<String, ReminderDTO> = LinkedHashMap()

    override suspend fun getReminders(): List<ReminderDTO> {
        if (returnError) {
            throw (Exception("Test exception"))
        }

        val list = mutableListOf<ReminderDTO>()
        list.addAll(remindersData.values)
        return list
    }

    override suspend fun getReminderById(reminderId: String): ReminderDTO? {
        if (returnError) {
            throw Exception("No such reminder")
        }
        remindersData[reminderId]?.let {
            return it
        }
        return null
    }

    override suspend fun saveReminder(reminder: ReminderDTO) {
        remindersData[reminder.id] = reminder
    }

    override suspend fun deleteAllReminders() {
        remindersData.clear()
    }

}