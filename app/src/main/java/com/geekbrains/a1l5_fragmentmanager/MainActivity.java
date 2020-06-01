package com.geekbrains.a1l5_fragmentmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geekbrains.a1l5_fragmentmanager.fragments.Fragment1;
import com.geekbrains.a1l5_fragmentmanager.fragments.Fragment2;
import com.geekbrains.a1l5_fragmentmanager.fragments.Fragment3;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создадим фрагменты
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //обработка кнопок
        Button add1 = findViewById(R.id.add1);
        add1.setOnClickListener(new ListenerOnAdd(fragment1));
        Button add2 = findViewById(R.id.add2);
        add2.setOnClickListener(new ListenerOnAdd(fragment2));
        Button add3 = findViewById(R.id.add3);
        add3.setOnClickListener(new ListenerOnAdd(fragment3));

        Button remove1 = findViewById(R.id.remove1);
        remove1.setOnClickListener(new ListenerOnRemove(fragment1));
        Button remove2 = findViewById(R.id.remove2);
        remove2.setOnClickListener(new ListenerOnRemove(fragment2));
        Button remove3 = findViewById(R.id.remove3);
        remove3.setOnClickListener(new ListenerOnRemove(fragment3));

        Button replace1 = findViewById(R.id.replace1);
        replace1.setOnClickListener(new ListenerOnReplace(fragment1));
        Button replace2 = findViewById(R.id.replace2);
        replace2.setOnClickListener(new ListenerOnReplace(fragment2));
        Button replace3 = findViewById(R.id.replace3);
        replace3.setOnClickListener(new ListenerOnReplace(fragment3));
    }

    class ListenerOnAdd implements View.OnClickListener{
        Fragment fragment;

        ListenerOnAdd(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            addFragment();
        }

        // Добавить фрагмент
        private void addFragment(){
            // Открыть транзакцию
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // Добавить фрагмент
            fragmentTransaction.add(R.id.fragment_container, fragment);
            // Закрыть транзакцию
            fragmentTransaction.commit();
        }
    }

    class ListenerOnRemove implements View.OnClickListener{
        Fragment fragment;

        ListenerOnRemove(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            removeFragment();
        }

        // Удалить фрагмент
        private void removeFragment(){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }
    }

    class ListenerOnReplace implements View.OnClickListener{
        Fragment fragment;

        ListenerOnReplace(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            replaceFragment();
        }

        // Заменить фрагмент
        private void replaceFragment(){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack("");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
