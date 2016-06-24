package com.example.hanbyeol.capstone_ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

/*import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;*/

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    TabLayout tabLayout_bottom;
    String[][] parsedData;
    Fragment1 frag1;
    Fragment2 frag2;
    Fragment3 frag3;
    Fragment4 frag4;
    //String serverURL = "http://52.74.198.10/a.php";

    //List<NameValuePair> params = new ArrayList<NameValuePair>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setDisplayHomeAsUpEnabled(true);

        //Navigation Drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout_bottom = (TabLayout) findViewById(R.id.main_tabs_bottom);
        TabLayoutBottomEvent();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.nav_home) {
            //intent = new Intent(this, MainActivity.class);
            // startActivity(intent);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_url) {
            intent = new Intent(this, UrlActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_from_right, R.anim.anim_hold);
        } else if (id == R.id.nav_fragment_test) {
            intent = new Intent(this, FragTestActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_from_right, R.anim.anim_hold);
        }

        return true;
    }

    private void TabLayoutBottomEvent() {
        tabLayout_bottom.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        tabLayout_bottom.addTab(tabLayout_bottom.newTab().setText("tabone"), true);
        tabLayout_bottom.addTab(tabLayout_bottom.newTab().setText("tabtwo"));
        tabLayout_bottom.addTab(tabLayout_bottom.newTab().setText("tabthree"));
        tabLayout_bottom.addTab(tabLayout_bottom.newTab().setText("tabfour"));
    }

    private void setCurrentTabFragment(int tabPosition) {
        //frag1 = frag1.getInstance();

        if(frag1 == null)
            frag1 = new Fragment1();
        if(frag2 == null)
            frag2 = new Fragment2();
        if(frag3 == null)
            frag3 = new Fragment3();
        if(frag4 == null)
            frag4 = new Fragment4();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Bundle bundle = new Bundle();
        //JSONtabparser();

        ReadBandmenu jsonBandmenu = new ReadBandmenu();
        jsonBandmenu.execute("http://52.74.198.10/a.php");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bundle.putSerializable("parsedData", parsedData);
       // frag1.getInstance().setArguments(bundle);
       // frag1.setArguments(bundle);

        switch (tabPosition) {
            case 0:
                frag1.setArguments(bundle);
                ft.replace(R.id.fragment_part_test, frag1);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.fragment_part_test, frag2);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.fragment_part_test, frag3);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.fragment_part_test, frag4);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                break;
            default:
                Log.d("hi", "default");
                break;
        }
    }

    public void JSONtabparser(String jsonString) {
//        String jsonString = "{\"app_ver\":\"0.1.1\",\"band_menu\":\n" +
//                "\t[\n" +
//                "\t\t{\"title\":\"Home\",\"url\":\"http:\\/\\/www.cconma.com\\/mobile\"},\n" +
//                "\t\t{\"title\":\"\\uaf43\\ub9c8USA\",\"url\":\"http:\\/\\/www.cconmausa.com\"},\n" +
//                "\t\t{\"title\":\"\\ucd94\\ucc9c\\uc0c1\\ud488\",\"url\":\"http:\\/\\/us05.cconmausa.com\\/mobile\\/test\\/1.html\"},\n" +
//                "\t\t{\"title\":\"\\uc601\\uc591\\ubc14\",\"url\":\"http:\\/\\/www.cconma.com\\/m\\/store\\/1\\/nutritionalbar\"},\n" +
//                "\t\t{\"title\":\"\\uc774\\ubca4\\ud2b8\",\"url\":\"http:\\/\\/www.cconma.com\\/mobile\\/product\\/index.pmv?pcode=P001011000-001023\"},\n" +
//                "\t\t{\"title\":\"\\ub124\\uc774\\ubc84\",\"url\":\"http:\\/\\/m.naver.com\"}\n" +
//                "\t]\n" +
//                "}";

        try {
            JSONObject jObjectString = new JSONObject(jsonString);
            JSONArray jArray = new JSONArray(jObjectString.getString("band_menu"));

            String[] jsonName = {"title", "url"};
            parsedData = new String[jArray.length()][jsonName.length];

            for (int i = 0; i < jArray.length(); i++) {
                for (int j = 0; j < jsonName.length; j++) {
                    JSONObject jObject = jArray.getJSONObject(i);
                    parsedData[i][j] = jObject.getString(jsonName[j]);
                    Log.d("parsedData", parsedData[i][j]);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private class ReadBandmenu extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... urlString) {

            StringBuilder responseStringBuilder = new StringBuilder();
            try {
                URL url = new URL(urlString[0]);
                //trustAllHosts();

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                }

                Log.d("tag", "before making rescode !!!!");
//                httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
//                    @Override
//                    public boolean verify(String s, SSLSession sslSession) {
//                        return true;
//                    }
//                });

//                HttpURLConnection connection = httpsURLConnection;
//
//                connection.setRequestMethod("POST");
//                connection.setDoInput(true);
//                connection.setDoOutput(true);

//                List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(2);
//                nameValuePairs.add(new BasicNameValuePair("userId", "saltfactory"));
//                nameValuePairs.add(new BasicNameValuePair("password", "password"));

//                OutputStream outputStream = connection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                bufferedWriter.write(getURLQuery(nameValuePairs));
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();

                //connection.connect();
                int resCode = conn.getResponseCode();
                if(resCode == HttpURLConnection.HTTP_OK){
                    Log.d("tag", "resCode == HttpsURLConnection.HTTP_OK");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    for ( ; ; ) {
                        String stringLine = bufferedReader.readLine();
                        if (stringLine == null) break;
                        responseStringBuilder.append(stringLine + '\n');
                    }
                    bufferedReader.close();
                }

                conn.disconnect();

//                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //200
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    for ( ; ; ) {
//                        String stringLine = bufferedReader.readLine();
//                        if (stringLine == null) break;
//                        responseStringBuilder.append(stringLine + '\n');
//                    }
//                    bufferedReader.close();
//                }
//
//                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("return value", responseStringBuilder.toString());
            JSONtabparser(responseStringBuilder.toString());
            return responseStringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String jsonString) {
//            Log.d("test","hiHI????");
//
//            Log.d("whyyyy", jsonString);
//
//            try {
//                JSONObject jObjectString = new JSONObject(jsonString);
//                JSONArray jArray = new JSONArray(jObjectString.getString("band_menu"));
//
//                String[] jsonName = {"title", "url"};
//                parsedData = new String[jArray.length()][jsonName.length];
//
//                for (int i = 0; i < jArray.length(); i++) {
//                    for (int j = 0; j < jsonName.length; j++) {
//                        JSONObject jObject = jArray.getJSONObject(i);
//                        parsedData[i][j] = jObject.getString(jsonName[j]);
//                        Log.d("parsedData", parsedData[i][j]);
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }

    }

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType)
                    throws java.security.cert.CertificateException {
                // TODO Auto-generated method stub

            }
        }};

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getURLQuery(List<BasicNameValuePair> params) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;

        for (BasicNameValuePair pair : params) {
            if (first)
                first = false;
            else
                stringBuilder.append("&");

            try {
                stringBuilder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                stringBuilder.append("=");
                stringBuilder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }

}

