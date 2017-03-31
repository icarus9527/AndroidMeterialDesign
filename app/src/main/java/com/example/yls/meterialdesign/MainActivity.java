package com.example.yls.meterialdesign;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private MainRecyclerAdapter adapter;
    private Anime[] animes = {new Anime("萤火之森",R.drawable.a) ,new Anime("尸兄",R.drawable.b) ,
            new Anime("宗政君的复仇",R.drawable.c) , new Anime("精灵宝可梦",R.drawable.d) ,new Anime("One punch man",R.drawable.e) ,
            new Anime("从前有座剑灵山",R.drawable.f) , new Anime("海贼王",R.drawable.g) ,new Anime("中二病也要谈恋爱",R.drawable.h) ,
            new Anime("攻壳机动队",R.drawable.i) , new Anime("火影忍者",R.drawable.j) ,new Anime("灵域",R.drawable.k) ,};;

    private List<Anime> resource = new ArrayList<Anime>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SnackerBar需要一个View，作为它的父视图
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_container);

        //使toolBar在功能上与ActionBar一致，例如菜单
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        //修改ToolBar左边的HomeAsUp图标，并让它显示出来
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_View);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        //初始化Adapter的数据资源
        initResource();
        //方格布局，两列
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        adapter = new MainRecyclerAdapter(resource);
        recyclerView.setAdapter(adapter);

        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.main_swipeRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initResource();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });




        //浮动按钮的点击事件
        fab = (FloatingActionButton) findViewById(R.id.main_btn_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(coordinatorLayout,"SnackbarClicked",Snackbar.LENGTH_SHORT).setAction("Click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"You are a stupid",Toast.LENGTH_SHORT).show();
                    }
                }).setActionTextColor(Color.RED).show();
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this,"You are a stupid",Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        //侧滑菜单Item点击事件
        navigationView = (NavigationView) findViewById(R.id.main_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_item_home:
                        Toast.makeText(MainActivity.this,"这是首页",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_blog:
                        Toast.makeText(MainActivity.this,"这是博客",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_about:
                        Toast.makeText(MainActivity.this,"这是关于",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                //点击item后关闭侧滑菜单
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
    }

    private void initResource() {
        for(int i=0;i<30;i++){
            Random random = new Random();
            int index = random.nextInt(animes.length);
            resource.add(animes[index]);
        }
    }


    //绑定菜单的布局文件，创建菜单实例
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    //设置菜单的点击事件

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_toolbar_upload:
                Snackbar.make(coordinatorLayout,"You have clicked upload item",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_toolbar_add:
                Snackbar.make(coordinatorLayout,"You have clicked add item",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_toolbar_search:
                Snackbar.make(coordinatorLayout,"You have clicked search item",Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.menu_toolbar_setting:
                Snackbar.make(coordinatorLayout,"You have clicked setting item",Snackbar.LENGTH_SHORT).show();
                break;
            //修改toolbar上左边HomeAsUp按钮点击事件，原来是返回上一个活动，现修改为显示侧滑菜单
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }

        return true;
    }
}
