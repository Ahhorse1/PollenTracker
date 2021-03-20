package com.example.pollentracker.ui.home;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pollentracker.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private MapView mapView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Mapbox.getInstance(this.getContext(), getString(R.string.mapbox_access_token));
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // MapView large
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments
                        style.addImage("tree", BitmapFactory.decodeResource(
                                getResources(), R.drawable.tree));
                        style.addImage("bush", BitmapFactory.decodeResource(
                                getResources(), R.drawable.bush));
                        SymbolManager symbolManager = new SymbolManager(mapView, mapboxMap, style);
                        Symbol symbol = symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(40.000188,  -83.013459))
                                .withIconImage("tree")
                                .withIconSize(0.1f));
                        Symbol symbol1 = symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(40.000234,   -83.010887))
                                .withIconImage("bush")
                                .withIconSize(0.1f));
                        Symbol symbol2 = symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(40.000090,   -83.011637))
                                .withIconImage("tree")
                                .withIconSize(0.1f));
                        Symbol symbol3 = symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(40.000086,   -83.012440))
                                .withIconImage("tree")
                                .withIconSize(0.1f));
                        Symbol symbol4 = symbolManager.create(new SymbolOptions()
                                .withLatLng(new LatLng(39.999375,   -83.012124))
                                .withIconImage("tree")
                                .withIconSize(0.1f));
                    }
                });
            }
        });
    }
}