package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Helper;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;
import com.facebook.CallbackManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class InfoKhachHangActivity extends AppCompatActivity {
    private static final String TAG = InfoKhachHangActivity.class.getSimpleName();
    ParseJson parseJson = new ParseJson();

    Spinner spinnerGT;
    private static final String[] gts = {"Chọn giới tính", "Nam", "Nữ"};
    String gioiTinh;
    EditText edtSDT, edtMK, edtReMK, edtEmail, edtHT, edtDiaChi;
    CallbackManager callbackManager;
    Button btn_User_Save;
    TaiKhoanKhachHang _taiKhoan;
    EditText edtNgaySinh;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_khach_hang);
        callbackManager = CallbackManager.Factory.create();
        _taiKhoan = new TaiKhoanKhachHang();
        spinnerGT = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(InfoKhachHangActivity.this,
                android.R.layout.simple_spinner_item, gts);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGT.setAdapter(adapter);
        spinnerGT.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                gioiTinh = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        edtDiaChi = findViewById(R.id.txt_address);
        edtEmail = findViewById(R.id.txt_email);
        edtHT = findViewById(R.id.txt_nameuser);
        edtMK = findViewById(R.id.txt_password);
        edtReMK = findViewById(R.id.txt_repass);
        edtSDT = findViewById(R.id.txt_phone);
        edtNgaySinh = findViewById(R.id.edtNgaySinh);
        btn_User_Save = findViewById(R.id.btn_User_Save);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtNgaySinh.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "YYYYMMDD";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8) {
                        clean = clean + ddmmyyyy.substring(clean.length());
                    } else {
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day = Integer.parseInt(clean.substring(6, 8));
                        int mon = Integer.parseInt(clean.substring(4, 6));
                        int year = Integer.parseInt(clean.substring(0, 4));

                        if (mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon - 1);

                        year = (year < 1900) ? 1900 : (year > 2100) ? 2100 : year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE)) ? cal.getActualMaximum(Calendar.DATE) : day;
                        clean = String.format("%02d%02d%02d", year, mon, day);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 4),
                            clean.substring(4, 6),
                            clean.substring(6, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edtNgaySinh.setText(current);
                    edtNgaySinh.setSelection(sel < current.length() ? sel : current.length());
                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btn_User_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdt = edtSDT.getText().toString();
                String mk = edtMK.getText().toString();
                String reMK = edtReMK.getText().toString();
                String email = edtEmail.getText().toString();
                String hoTen = edtHT.getText().toString();
                String diaChi = edtDiaChi.getText().toString();
                String gioiTinh = spinnerGT.getSelectedItem().toString();
                if (gioiTinh.equals("Chọn giới tính")) {
                    gioiTinh = "Nam";
                }
                String ngaySinh = edtNgaySinh.getText().toString();
                _taiKhoan.setNgaySinh(ngaySinh);

                _taiKhoan.setHoTen(hoTen);
                _taiKhoan.setDienThoai(sdt);
                _taiKhoan.setEmail(email);
                _taiKhoan.setMatKhau(mk);
                _taiKhoan.setDiaChi(diaChi);
                _taiKhoan.setGioiTinh(gioiTinh);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (checkHelper(hoTen, sdt, mk, reMK)) {
                            final Toast toast;
                            String url = String.format(Common.preUrl + "KhachHang/check/%s/%s", email, sdt);
                            _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                            String result = parseJson.readStringFileContent(url);
                            if (result.equals("true")) {
                                Log.d(TAG, "======Account information be exist======");
                                Log.e(TAG, "This information use email or phone number has been another user use. Please use other information.");

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(InfoKhachHangActivity.this, "This information use email or phone number has been another user use.", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                            } else {

                                //Get new KhachHangID
                                url = String.format(Common.preUrl + "KhachHang/get/%s", loadPreferences("my_email"));
                                String r = parseJson.readStringFileContent(url);
                                JSONObject response;
                                try {
                                    response = new JSONObject(r);
                                    _taiKhoan.setMaKH(response.getString("MaKhachHang").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                url = Common.preUrl + "KhachHang/update/";
                                String jsonQuery = String.format("{'MaKhachHang': %s , 'TenKhachHang': '%s', 'NgaySinh': '%s', 'GioiTinh' : '%s'," +
                                                " ,'Email': '%s', 'DienThoai': '%s', 'DiaChi':'%s', 'MatKhau': '%s' }"
                                        , _taiKhoan.getMaKH(), _taiKhoan.getTenTaiKhoan(), _taiKhoan.getNgaySinh(), _taiKhoan.getGioiTinh(),
                                        _taiKhoan.getEmail(), _taiKhoan.getDienThoai(), _taiKhoan.getDiaChi(), _taiKhoan.getMatKhau());
                                Boolean bb = Boolean.valueOf(parseJson.postObjectToDB(url, jsonQuery));
                                if (bb == true) {

                                    Log.d(TAG, "Congratulations on your successful account registration");
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast toast = Toast.makeText(InfoKhachHangActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    });
                                    Intent intent = new Intent(InfoKhachHangActivity.this, TrangChuActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }


    public String loadPreferences(String key) {
        SharedPreferences p = getSharedPreferences("data", Context.MODE_PRIVATE);
        return p.getString(key, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private boolean checkHelper(String fullName, String phoneNumber, String mk, String reMK) {
        if (fullName.equals("") || phoneNumber.equals("") || mk.equals("") || reMK.equals("")) {
            Log.d(TAG, "======Information be empty======");
            Log.e(TAG, "Information of user be not empty");

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "Information not enough.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        if (!Helper.isFullname(fullName)) {
            Log.d(TAG, "======Fullname invalid======");
            Log.e(TAG, String.format("User Name %s is not valid. User name contains only alphabetic characters a->z.", fullName));

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "Fullname invalid.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        if (fullName.length() > 50) {
            Log.d(TAG, "======Fullname Length over max======");
            Log.e(TAG, String.format("User Name %s exceed the maximum allowed character length. User name length maximum of only 50 characters.", fullName));

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "Fullname Length over max.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        if (!Helper.isPhoneNumber(phoneNumber)) {
            Log.d(TAG, "======PhoneNumber invalid======");
            Log.e(TAG, String.format("Phone number %s is not valid. Phone number should be phone number VietNam.", phoneNumber));
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "PhoneNumber invalid.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        if (!Helper.isPassword(mk)) {
            Log.d(TAG, "======Password invalid======");
            Log.e(TAG, String.format("Password %s is not valid. Password include characters: aA->zZ, special characters, number and minimum length is 8.", mk));
//                toast = Toast.makeText(InfoKhachHangActivity.this, "Password invalid.", Toast.LENGTH_SHORT);
//                toast.show();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "Password invalid.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        if (!mk.equals(reMK)) {
            Log.d(TAG, "======RePassword not match======");
            Log.e(TAG, "RePassword unlike does not match the password you just entered.");

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(InfoKhachHangActivity.this, "RePassword not match.", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return false;
        }
        return true;
    }
}