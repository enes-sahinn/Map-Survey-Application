package com.enessahin.mapsurveyapplication

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.enessahin.mapsurveyapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.*
import com.google.android.material.snackbar.Snackbar

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var sharredPreferences: SharedPreferences
    private var trackBoolean: Boolean? = null

    private val st1 = LatLng(38.375802, 27.194204)
    private val st2 = LatLng(38.373337, 27.196641)
    private val st3 = LatLng(38.371905, 27.199729)
    private val st4 = LatLng(38.370978, 27.202790)
    private val st5 = LatLng(38.370128, 27.204924)
    private val st6 = LatLng(38.367881, 27.201790)
    private val st7 = LatLng(38.367186, 27.205592)
    private val st8 = LatLng(38.368283, 27.206734)
    private val st9 = LatLng(38.369273, 27.207971)
    private val st10 = LatLng(38.368447, 27.210525)

    private var markerSt1: Marker? = null
    private var markerSt2: Marker? = null
    private var markerSt3: Marker? = null
    private var markerSt4: Marker? = null
    private var markerSt5: Marker? = null
    private var markerSt6: Marker? = null
    private var markerSt7: Marker? = null
    private var markerSt8: Marker? = null
    private var markerSt9: Marker? = null
    private var markerSt10: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        registerLauncher()
        sharredPreferences = this.getSharedPreferences("com.enessahin.mapsurveyapplication", MODE_PRIVATE)
        trackBoolean = false
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Casting
        locationManager = this.getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(p0: Location) {
                trackBoolean = sharredPreferences.getBoolean("trackBoolean", false)
                if (trackBoolean == false) {
                    var userLocation = LatLng(p0.latitude, p0.longitude)
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 16f))
                    sharredPreferences.edit().putBoolean("trackBoolean", true).apply()
                }
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Snackbar.make(binding.root, "Permission needed for location!", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission") {
                    // request permission
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else {
                // request permission
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        } else {
            // permission granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null) {
                val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 16f))
            }
            mMap.isMyLocationEnabled = true
        }

        /*// lat->38.37041784153357, long->27.205561286585628
        // Add a marker in DEÜ and move the camera
        val deu = LatLng(38.37041784153357, 27.205561286585628)
        mMap.addMarker(MarkerOptions().position(deu).title("DEÜ"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(deu, 15f))*/

        markerSt1 = googleMap.addMarker(
            MarkerOptions()
                .position(st1)
                .title("Kampüs Giriş")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt2 = googleMap.addMarker(
            MarkerOptions()
                .position(st2)
                .title("Yetmiş Beşinci Yıl İlköğretim Okulu")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt3 = googleMap.addMarker(
             MarkerOptions()
                 .position(st3)
                 .title("Spor Salonu")
                 .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt4 = googleMap.addMarker(
            MarkerOptions()
                .position(st4)
                .title("Kutüphane")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt5 = googleMap.addMarker(
            MarkerOptions()
                .position(st5)
                .title("Sosyal Tesisler")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt6 = googleMap.addMarker(
            MarkerOptions()
                .position(st6)
                .title("Fen Edebiyat")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt7 = googleMap.addMarker(
            MarkerOptions()
                .position(st7)
                .title("Teknopark")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt8 = googleMap.addMarker(
            MarkerOptions()
                .position(st8)
                .title("Mimarlık Fakültesi")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt9 = googleMap.addMarker(
            MarkerOptions()
                .position(st9)
                .title("Mühendislik Fakültesi")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        markerSt10 = googleMap.addMarker(
            MarkerOptions()
                .position(st10)
                .title("Tınaztepe Yerleşke Son Durak")
                .snippet("Passing lines: 412, 290, 390, 690, 878 / Click for survey!")
        )
        // Set a listener for info window events.
        googleMap.setOnInfoWindowClickListener(this)
    }

    private fun registerLauncher() {
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if(result) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //permission granted
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if (lastLocation != null) {
                        val lastUserLocation = LatLng(lastLocation.latitude, lastLocation.longitude)
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 16f))
                    }
                    mMap.isMyLocationEnabled = true
                }
            }
            else {
                //permission denied
                Toast.makeText(this@MapsActivity, "Permission needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onInfoWindowClick(p0: Marker) {
        Toast.makeText(this, "Info window clicked", Toast.LENGTH_SHORT).show()
        var intent = Intent(applicationContext, SurveyActivity::class.java).also {
            startActivity(it)
        }
    }
}