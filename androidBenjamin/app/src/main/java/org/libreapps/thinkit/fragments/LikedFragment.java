package org.libreapps.thinkit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.libreapps.thinkit.R;
import org.libreapps.thinkit.adapters.PostAdapter;
import org.libreapps.thinkit.data.sqlite.DbController;
import org.libreapps.thinkit.listeners.ItemClickListener;
import org.libreapps.thinkit.models.UserPostModel;

import java.util.ArrayList;

public class LikedFragment extends Fragment {

    private ArrayList<UserPostModel> dataList;

    private RecyclerView rvPost;
    private PostAdapter mAdapter;
    private DbController dbController;
    private boolean isFav = false;

    public LikedFragment() {
        // Required empty public constructor
    }

    public static LikedFragment newInstance(String param1, String param2) {
        LikedFragment fragment = new LikedFragment();
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
        return inflater.inflate(R.layout.fragment_liked, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable();
        initView(view);
        initListener();
    }

    private void initVariable() {
        dataList = new ArrayList<>();
        dbController = new DbController(getActivity());
    }

    private void initView(View view) {
        rvPost = view.findViewById(R.id.rvPost);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvPost.setLayoutManager(mLayoutManager);
        rvPost.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PostAdapter(getActivity(), dataList);
        rvPost.setAdapter(mAdapter);

        loadPostData();
    }

    private void initListener() {

        mAdapter.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                switch (v.getId()){
                    case R.id.ivFav:
                        if(isFav){
                            dbController.removeFromFavorite(dataList.get(position).getId());
                            loadPostData();
                            isFav = false;
                        }
                        else {
                            dbController.addToFavorite(dataList.get(position).getId());
                            loadPostData();
                            isFav = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void loadPostData(){
        dataList.clear();
        dataList.addAll(dbController.getAllLikedPost());
        mAdapter.notifyDataSetChanged();
    }
}