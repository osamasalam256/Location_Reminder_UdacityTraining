package com.udacity.project4.locationreminders.geofence

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.core.app.JobIntentService
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import com.udacity.project4.locationreminders.data.local.RemindersLocalRepository
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import com.udacity.project4.utils.sendNotification
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import kotlin.coroutines.CoroutineContext
<<<<<<< HEAD

class GeofenceTransitionsJobIntentService : JobIntentService(), CoroutineScope {
private val TAG = "Geof-TJIS"
=======
class GeofenceTransitionsJobIntentService : JobIntentService(), CoroutineScope {
    private val TAG = "Geof-TJIS"
>>>>>>> a2f1b96 (update project with testing)
    private var coroutineJob: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + coroutineJob

    companion object {
        private const val JOB_ID = 573

        // call this to start the JobIntentService to handle the geofencing transition events
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(
                context,
                GeofenceTransitionsJobIntentService::class.java, JOB_ID,
                intent
            )
        }
    }

    override fun onHandleWork(intent: Intent) {
        // handle the geofencing transition events and
        // send a notification to the user when he enters the geofence area
        // call @sendNotification
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        val geofenceList: List<Geofence> =
            geofencingEvent.triggeringGeofences
        sendNotification(geofenceList)
    }

    // get the request id of the current geofence
    private fun sendNotification(triggeringGeofences: List<Geofence>) {
        val requestId = when {
            triggeringGeofences.isNotEmpty() ->
            {
                Log.d(TAG, " sendNotification: " + triggeringGeofences[0].requestId)
<<<<<<< HEAD
                triggeringGeofences[0].requestId
            }

            else -> {
=======
                triggeringGeofences[0].requestId.toString()
            }else -> {
>>>>>>> a2f1b96 (update project with testing)
                Log.e(TAG, "No Geofence Trigger Found !")
                return
            }
        }
        if(TextUtils.isEmpty(requestId)) return

        //Get the local repository instance
        val remindersLocalRepository: ReminderDataSource by inject()
//        Interaction to the repository has to be through a coroutine scope
        CoroutineScope(coroutineContext).launch(SupervisorJob()) {
            //get the reminder with the request id
            val result = remindersLocalRepository.getReminder(requestId)
            if (result is Result.Success<ReminderDTO>) {
                val reminderDTO = result.data
                //send a notification to the user with the reminder details
                sendNotification(
                    this@GeofenceTransitionsJobIntentService, ReminderDataItem(
                        reminderDTO.title,
                        reminderDTO.description,
                        reminderDTO.location,
                        reminderDTO.latitude,
                        reminderDTO.longitude,
                        reminderDTO.id
                    )
                )
            }
        }
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> a2f1b96 (update project with testing)
