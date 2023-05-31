package com.udacity.project4.locationreminders.savereminder

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
<<<<<<< HEAD
import com.udacity.project4.BuildConfig
=======
>>>>>>> a2f1b96 (update project with testing)
import com.udacity.project4.R
import com.udacity.project4.base.BaseFragment
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.databinding.FragmentSaveReminderBinding
import com.udacity.project4.locationreminders.geofence.GeofenceBroadcastReceiver
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import com.udacity.project4.utils.setDisplayHomeAsUpEnabled
import org.koin.android.ext.android.inject
import java.util.UUID

@SuppressLint("UnspecifiedImmutableFlag")
class SaveReminderFragment : BaseFragment() {
    private val runningQOrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    //Get the view model this time as a single to be shared with the another fragment
    override val _viewModel: SaveReminderViewModel by inject()
    private lateinit var binding: FragmentSaveReminderBinding
    private lateinit var geofencingClient: GeofencingClient
    private val geofenceRadius = 500f
    private lateinit var reminderData: ReminderDataItem
    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(requireContext(), GeofenceBroadcastReceiver::class.java)
        intent.action = ACTION_GEOFENCE_EVENT
        PendingIntent.getBroadcast(requireContext(),2607,intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_save_reminder, container, false)

        setDisplayHomeAsUpEnabled(true)

        binding.viewModel = _viewModel

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.selectLocation.setOnClickListener {
            //            Navigate to another fragment to get the user location
            _viewModel.navigationCommand.value =
                NavigationCommand.To(SaveReminderFragmentDirections.actionSaveReminderFragmentToSelectLocationFragment())
        }

        binding.saveReminder.setOnClickListener {
            val title = _viewModel.reminderTitle.value
            val description = _viewModel.reminderDescription.value
            val location = _viewModel.reminderSelectedLocationStr.value
            val latitude = _viewModel.latitude.value
            val longitude = _viewModel.longitude.value
<<<<<<< HEAD
            val geofenceId = UUID.randomUUID()

//            use the user entered reminder details to:
            if (latitude != null && longitude != null && !TextUtils.isEmpty(title)){
                addGeofence(LatLng(latitude, longitude), geofenceRadius, geofenceId.toString())
=======
            val geofenceId = UUID.randomUUID().toString()

//            use the user entered reminder details to:
            if (latitude != null && longitude != null && !TextUtils.isEmpty(title)){
                addGeofence(LatLng(latitude, longitude), geofenceRadius, geofenceId)
>>>>>>> a2f1b96 (update project with testing)
            }

//             1) add a geofencing request
//             2) save the reminder to the local db
<<<<<<< HEAD
            _viewModel.validateAndSaveReminder(ReminderDataItem(title,description,location, latitude,longitude))
=======
            _viewModel.validateAndSaveReminder(ReminderDataItem(title,description,location, latitude,longitude, geofenceId))
>>>>>>> a2f1b96 (update project with testing)
        }
        _viewModel.completeSaveReminder.observe(viewLifecycleOwner, Observer {
            if(it){
                view.findNavController().navigate(R.id.action_saveReminderFragment_to_reminderListFragment)
                _viewModel.navigateToReminderList()
            }
        })

        geofencingClient = LocationServices.getGeofencingClient(requireContext())
    }

    @SuppressLint("MissingPermission", "VisibleForTests")
    private fun addGeofence(latLng: LatLng, radius: Float,geofenceId: String) {
        val geofence: Geofence = getGeofence(
            geofenceId,
            latLng,
            radius,
            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT
        )
        val geofencingRequest: GeofencingRequest = getGeofencingRequest(geofence)

        geofencingClient.addGeofences(geofencingRequest, geofencePendingIntent)
            .addOnSuccessListener(OnSuccessListener<Void?> {
<<<<<<< HEAD
                 Toast.makeText(context,"geofence added",Toast.LENGTH_LONG).show()
=======
                Toast.makeText(context,"geofence added",Toast.LENGTH_LONG).show()
>>>>>>> a2f1b96 (update project with testing)
                Log.d(TAG, "Geofence Added")
            })
            .addOnFailureListener(OnFailureListener { e ->
                val errorMessage: String = getErrorString(e)
                Toast.makeText(context, "Please give background location permission", Toast.LENGTH_LONG).show()
                Log.d(TAG, "fail in creating geofence: $errorMessage")
            })
    }


<<<<<<< HEAD
   private fun getGeofencingRequest(geofence: Geofence?): GeofencingRequest {
=======
    private fun getGeofencingRequest(geofence: Geofence?): GeofencingRequest {
>>>>>>> a2f1b96 (update project with testing)
        return GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .build()
    }
    @SuppressLint("VisibleForTests")
    private fun getGeofence(ID: String, latLng: LatLng, radius: Float, transitionTypes: Int): Geofence {
        return Geofence.Builder()
            .setCircularRegion(latLng.latitude, latLng.longitude, radius)
            .setRequestId(ID)
            .setTransitionTypes(transitionTypes)
            .setLoiteringDelay(1000)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()
    }


    private fun getErrorString(e: Exception): String {
        if (e is ApiException) {
            when (e.statusCode) {
                GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> return getString(
                    R.string.geofence_not_available)
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> return getString(
                    R.string.geofence_too_many_geofences)
                GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> return getString(
                    R.string.geofence_too_many_pending_intents)
            }
        }
        return e.localizedMessage!!
    }

    override fun onDestroy() {
        super.onDestroy()
        //make sure to clear the view model after destroy, as it's a single view model.
        _viewModel.onClear()
    }
    companion object {
        val TAG = "Add Geofence"
        internal const val ACTION_GEOFENCE_EVENT =
            "ReminderActivity.action.ACTION_GEOFENCE_EVENT"
    }
}
<<<<<<< HEAD
private const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
private const val REQUEST_TURN_DEVICE_LOCATION_ON = 29
private const val LOCATION_PERMISSION_INDEX = 0
private const val BACKGROUND_LOCATION_PERMISSION_INDEX = 1
=======
>>>>>>> a2f1b96 (update project with testing)
