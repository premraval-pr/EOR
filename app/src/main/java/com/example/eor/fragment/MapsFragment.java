package com.example.eor.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eor.R;
import com.example.eor.dao.ExplorePost_DAO;
import com.example.eor.model.ExplorePost_Model;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsFragment extends Fragment {

    List<ExplorePost_Model> modelList = ExplorePost_DAO.list;
    int firstVisibleItemPosition = 0;
    SupportMapFragment mapFragment;
    GoogleMap googleMapMain;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMapMain = googleMap;
            for(ExplorePost_Model model: modelList){
                LatLng temp = new LatLng(model.getLatitude(),model.getLongitude());
                googleMap.addMarker(new MarkerOptions().position(temp));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(temp,10));
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void animate(int firstVisibleItemPosition) {
        this.firstVisibleItemPosition = firstVisibleItemPosition;
        Log.d("MAP", "animate: current position" + modelList.get(firstVisibleItemPosition).toString());
        ExplorePost_Model currentPost = modelList.get(firstVisibleItemPosition);
        LatLng currentPostLatLng = new LatLng(currentPost.getLatitude(),currentPost.getLongitude());


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(currentPostLatLng)
                .zoom(17)
                .tilt(50)
                .build();

        googleMapMain.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMapMain.addMarker(new MarkerOptions().position(currentPostLatLng).title(currentPost.toString())).showInfoWindow();
    }
}