package org.libreapps.thinkit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.libreapps.thinkit.R;
import org.libreapps.thinkit.data.sqlite.DbController;
import org.libreapps.thinkit.utils.AppUtils;

public class AddPostFragment extends Fragment {

    private EditText etTitle, etContent;
    private Button btnSubmit;

    private DbController dbController;
    public AddPostFragment() {
        // Required empty public constructor
    }

    public static AddPostFragment newInstance(String param1, String param2) {
        AddPostFragment fragment = new AddPostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable();
        initView(view);
        initListener();
    }

    private void initVariable() {
        dbController = new DbController(getActivity());
    }

    private void initView(View view) {
        etTitle = view.findViewById(R.id.etTitle);
        etContent = view.findViewById(R.id.etContent);
        btnSubmit = view.findViewById(R.id.btnSubmit);
    }

    private void initListener() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                    dbController.insertData(title, content, AppUtils.getUniqueId(), 1); //0 = incomplete, 1 = complete
                    Toast.makeText(getActivity(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                    etTitle.getText().clear();
                    etContent.getText().clear();
                }
                else
                    Toast.makeText(getActivity(), "Please fillup all field!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}