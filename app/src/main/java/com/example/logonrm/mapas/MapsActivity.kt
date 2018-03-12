package com.example.logonrm.mapas

import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btPesquisar.setOnClickListener {
            val geocoder = Geocoder(this)
            var address : List<Address>?

            address = geocoder
                    .getFromLocationName(
                            etEndereco.text.toString(),
                            1)
            val location = address[0]
            adicionarMarcador(location.latitude, location.longitude, "Endereço Pesquisar")

        }
    }

    fun adicionarMarcador(latitude: Double, longitude: Double, title: String) {
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title(title)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.ic_launcher)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16f))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



    }
}
