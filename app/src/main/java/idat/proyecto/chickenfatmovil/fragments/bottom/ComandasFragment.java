package idat.proyecto.chickenfatmovil.fragments.bottom;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import idat.proyecto.chickenfatmovil.activities.generatecomands.GCDeliveryActivity;
import idat.proyecto.chickenfatmovil.activities.generatecomands.GCMesaActivity;
import idat.proyecto.chickenfatmovil.activities.generatecomands.GCTakeAwayActivity;
import idat.proyecto.chickenfatmovil.R;
import idat.proyecto.chickenfatmovil.fragments.toptab.DeliveryFragment;
import idat.proyecto.chickenfatmovil.fragments.toptab.RestauranteFragment;
import idat.proyecto.chickenfatmovil.fragments.toptab.ViewPagerAdapter;

public class ComandasFragment extends Fragment {

    ViewPagerAdapter mAdapter;
    ViewPager2 viewPager2;

    FloatingActionButton btnAdd, btnAddDelivery, btnAddTakeAway, btnAddRestaurante;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;

    boolean isOpen = false;

    public static ComandasFragment newInstance() {
        return new ComandasFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comandas, container, false);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAddDelivery = view.findViewById(R.id.btnAddDelivery);
        btnAddTakeAway = view.findViewById(R.id.btnAddTakeAway);
        btnAddRestaurante = view.findViewById(R.id.btnAddRestaurante);

        fabOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_backward);

        btnAdd.setOnClickListener(view1 -> animateFab());

        btnAddDelivery.setOnClickListener(view2 -> {
            animateFab();
            irGenerarComandaDelivery();
            Toast.makeText(getActivity(), "Delivery", Toast.LENGTH_SHORT).show();
        });
        btnAddTakeAway.setOnClickListener(view3 -> {
            animateFab();
            irGenerarComandaTakeAway();
            Toast.makeText(getActivity(), "TakeAway", Toast.LENGTH_SHORT).show();
        });
        btnAddRestaurante.setOnClickListener(view4 -> {
            animateFab();
            irGenerarComandaMesa();
            Toast.makeText(getActivity(), "Mesa", Toast.LENGTH_SHORT).show();
        });

        viewPager2 = view.findViewById(R.id.view_pager2);
        mAdapter = new ViewPagerAdapter(getParentFragmentManager(), getLifecycle());

        mAdapter.addFragment(new RestauranteFragment());  // 0
        mAdapter.addFragment(new DeliveryFragment());   //1

        viewPager2.setAdapter(mAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Restaurante");
                    break;

                case 1:
                    tab.setText("Delivery - Take Away");
                    break;
            }
        }).attach();

        return view;
    }

    public void irGenerarComandaDelivery(){
        Intent GenerarComandaDelivery = new Intent(getContext(), GCDeliveryActivity.class);
        startActivity(GenerarComandaDelivery);
    }

    public void irGenerarComandaTakeAway(){
        Intent GenerarComandaTakeAway = new Intent(getContext(), GCTakeAwayActivity.class);
        startActivity(GenerarComandaTakeAway);
    }

    public void irGenerarComandaMesa(){
        Intent GenerarComandaMesa = new Intent(getContext(), GCMesaActivity.class);
        startActivity(GenerarComandaMesa);
    }
    private void animateFab() {
        if (isOpen) {
            btnAdd.startAnimation(rotateBackward);
            btnAddDelivery.startAnimation(fabClose);
            btnAddTakeAway.startAnimation(fabClose);
            btnAddRestaurante.startAnimation(fabClose);
            btnAddDelivery.setClickable(false);
            btnAddTakeAway.setClickable(false);
            btnAddRestaurante.setClickable(false);
            isOpen = false;
        } else {
            btnAdd.startAnimation(rotateForward);
            btnAddDelivery.startAnimation(fabOpen);
            btnAddTakeAway.startAnimation(fabOpen);
            btnAddRestaurante.startAnimation(fabOpen);
            btnAddDelivery.setClickable(true);
            btnAddTakeAway.setClickable(true);
            btnAddRestaurante.setClickable(true);
            isOpen = true;
        }
    }
}
