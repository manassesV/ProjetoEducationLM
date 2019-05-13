package com.manasses.manab.project.ui.userprofile

import android.Manifest
import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manasses.manab.project.R
import com.manasses.manab.project.ui.main.MainActivity
import dagger.android.support.AndroidSupportInjection
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.fragment_maps.*
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition






class MapsFragment : BaseFragment(){

    private val LOCATION_REQUEST_CODE = 101
    private var mMap: GoogleMap? = null
    protected lateinit var viewModel: MapsViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.frg)
                    as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng(37.4219999, -122.0862462))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.4219999, -122.0862462))
                    .title("Spider Man")
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.4629101, -122.2449094))
                    .title("Iron Man")
                    .snippet("His Talent : Plenty of money")
            )

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(37.3092293, -122.1136845))
                    .title("Captain America")
            )
        }


        return rootView
    }

    fun setUpDagger() {
        AndroidSupportInjection.inject(this)
    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpDagger()
        setUpViewModel()
        setUpVIew()

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            val activity = context as MainActivity?
            this.parentActivity = activity
        }
    }



    private fun setUpVIew() {

        voltar.setOnClickListener({
            nextFragment(this, MainFragment())
        })
    }

    fun setUpViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFastory)
            .get(MapsViewModel::class.java)
    }


}
